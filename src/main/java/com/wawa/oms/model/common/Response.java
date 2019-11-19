package com.wawa.oms.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    @ApiModelProperty(value = "", required = true)
    private String code;
    @ApiModelProperty(value = "", required = true)
    private String description;
    private List<ErrorDetails> errors;

}

