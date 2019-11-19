package com.wawa.oms.model.common;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {
    private Long count;
    private List<T> content;
    private String pageNumber;
    private String pageSize;
}
