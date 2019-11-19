package com.wawa.oms.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wawa.oms.config.RestTemplateConfig;
import com.wawa.oms.model.common.ErrorDetails;
import com.wawa.oms.model.common.Response;
import com.wawa.oms.model.document.Sample;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResourceAccessException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestService {

    private static final Logger LOG = LoggerFactory.getLogger(RestService.class);
    protected HttpHeaders httpHeaders;
    @Autowired
    private RestTemplateConfig restTemplateFactory;

    @PostConstruct
    public void init() {
        this.httpHeaders = new HttpHeaders();
        this.httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        this.httpHeaders.set("Accept", "application/json");
        this.setErrorHandler();
    }

    private void setErrorHandler() {
        this.restTemplateFactory.restTemplate().setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                LOG.error("Response Status Code: " + response.getRawStatusCode());
                LOG.error("Response Status Text: " + response.getStatusText());
                if (response.getRawStatusCode() == 404) {//usually there is no response to parse in this case
                    LOG.error("Response Body: " + new String(getResponseBody(response)));
                    return true;
                }
                return false; //we want to be able to parse the error response
            }
        });
    }

    public <T> ResponseEntity<T> invokeClient(String url, HttpMethod method, HttpEntity<?> httpRequest,
                                              Class<T> object) {
        return this.restTemplateFactory.restTemplate().exchange(url, method, httpRequest, object);
    }

    //Sample: Consume REST API
    public List<Sample> sampleList() {
        HttpEntity<?> httpRequest = new HttpEntity<>(null, this.httpHeaders);
        ResponseEntity<?> responseTemp = null;
        List<Sample> userList = new ArrayList<>();
        String url = ""; //TODO thirty party URL from properties
        Sample response = null;

        try {
            LOG.info("Server Result calling  URL: {}", url);
            responseTemp = invokeClient(url, HttpMethod.GET, httpRequest, String.class);

            if (responseTemp.getStatusCode().is2xxSuccessful()) {
                ObjectMapper mapper = new ObjectMapper();
                response = mapper.readValue(responseTemp.getBody().toString(), Sample.class);
                userList.add(response);
            } else {
                //Handle other Response Codes
                // handleOtherResponseCodes(responseTemp);
            }
        } catch (ResourceAccessException e) {
            String msg = e.getMessage();
            LOG.error("Exception calling  URL: {}", url, e);
            //throw new RestException(HBEConstants.HPF_MS_BS_003, e.getMessage());
        } catch (Exception e) {
            LOG.error("General Exception calling  URL: {}", url, e);
        }
        return userList;
    }

    private Response handleOtherResponseCodes(ResponseEntity<?> responseTemp) {
        Response errorResp = null;
        try {
            LOG.info("Handle Other ResponseCodes");

            if (responseTemp.getStatusCode() == HttpStatus.BAD_REQUEST) {// handle 400
                errorResp = formatBadRequestCodes(responseTemp.getBody().toString());
            } else if (responseTemp.getStatusCode() == HttpStatus.UNAUTHORIZED) {// handle 401
                ObjectMapper mapper = new ObjectMapper();
                errorResp = mapper.readValue(responseTemp.getBody().toString(), Response.class);

            } else { //handle others basically 500 and 404

                ObjectMapper mapper = new ObjectMapper();
                errorResp = mapper.readValue(responseTemp.getBody().toString(), Response.class);
            }

        } catch (IOException e) {
            String msg = "Exception in Handle Other ResponseCodes";
            LOG.error(msg, e);
        }
        return errorResp;
    }

    private Response formatBadRequestCodes(String responseString) {

        LOG.error("In formatOtherResponseCodes");

        Response response = new Response();
        JSONObject jsonObj;
        List<ErrorDetails> errorList = new ArrayList<>();

        try {

            jsonObj = new JSONObject(responseString);
            response.setCode(jsonObj.getString("code"));
            response.setDescription(jsonObj.getString("description"));
            JSONArray errors = jsonObj.getJSONArray("errors");

            if (errors != null) {
                for (int i = 0; i < errors.length(); i++) {

                    JSONObject eachError = errors.getJSONObject(i);

                    LOG.info("In formatOtherResponseCodes  FieldName: {} and Message: {}",
                            eachError.getString("fieldName"), eachError.getString("message"));

                    ErrorDetails e = new ErrorDetails(eachError.getString("fieldName"), eachError.getString("message"));
                    errorList.add(e);

                    LOG.info("In formatOtherResponseCodes  FieldName: {} and Message: {}", e.getFieldName(),
                            e.getMessage());
                }
            }

            response.setErrors(errorList);

        } catch (JSONException e) {
            String msg = "Json Exception in formatOtherResponseCodes";
            LOG.error(msg, e);
        }

        return response;
    }
}
