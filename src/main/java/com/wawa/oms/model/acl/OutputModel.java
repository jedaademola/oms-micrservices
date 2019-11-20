package com.wawa.oms.model.acl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author Lukman Arogundade on 11/19/2019
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OutputModel {
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;
    private String field7;
    private String field8;
    private String field9;
    private String field10;
}
