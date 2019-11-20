package com.wawa.oms.service;

import com.wawa.oms.model.common.Response;
import com.wawa.oms.model.document.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author Lukman Arogundade on 11/20/2019
 */
@Service
public class AsyncCommonService {
    private static final Logger LOG = LoggerFactory.getLogger(AsyncCommonService.class);

    @Async
    public CompletableFuture<Response> logResult(Sample sample) {
        LOG.info("in AsyncCommonService.logResult");
        // Start the clock
        long start = System.currentTimeMillis();
        Response response = new Response();
        try {
            //TODO: process something
        } catch (Exception e) {
            String msg = "Exception in AsyncCommonService.logResult";
            LOG.error(msg, e);
        }
        LOG.info("Elapsed time for in AsyncCommonService.logResult:" + (System.currentTimeMillis() - start));
        return CompletableFuture.completedFuture(response);
    }

}
