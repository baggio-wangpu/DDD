package com.bee.master.application.component;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Component
public class PageHelper {

    public Pageable getPageable(Integer pageIndex, Integer pageSize, String sort) {
        if (isBlank(sort)) {
            return PageRequest.of(pageIndex, pageSize);
        }
        return PageRequest.of(pageIndex, pageSize, getSortDirection(sort), getSortColumns(sort));
    }

    private String[] getSortColumns(String sort) {
        String[] sorts = sort.split(",");
        return Arrays.stream(sorts).map(column -> {
            if (column.startsWith("-") || column.startsWith("+")) {
                return column.substring(1);
            }
            return column;
        }).toArray(String[]::new);
    }

    private Sort.Direction getSortDirection(String sort) {
        return sort.startsWith("-") ? DESC : ASC;
    }

}
