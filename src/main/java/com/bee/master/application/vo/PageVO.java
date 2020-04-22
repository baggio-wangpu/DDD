package com.bee.master.application.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageVO<T> {

    public static final String META_KEY_PAGE_INDEX = "pageIndex";
    public static final String META_KEY_PAGE_SIZE = "pageSize";
    public static final String META_KEY_TOTAL_PAGE = "totalPage";
    public static final String META_KEY_TOTAL_SIZE = "totalElements";

    private Map<String, Object> meta;

    private List<T> data;

}
