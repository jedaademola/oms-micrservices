package com.wawa.oms.util;

import com.wawa.oms.model.acl.InputModel;
import com.wawa.oms.model.acl.OutputModel;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Lukman Arogundade on 11/19/2019
 */
public class ACLUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ACLUtil.class);
    @Autowired
    private static ModelMapper modelMapper;

    public static OutputModel transform(InputModel inputData) {
        OutputModel outputData = null;
        try {
            outputData = modelMapper.map(inputData, OutputModel.class);
        } catch (Exception e) {
            LOG.error("Error in transform {} :", "cannot get transform data", e);
        }
        return outputData;
    }
}
