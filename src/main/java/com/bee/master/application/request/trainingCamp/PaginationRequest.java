package com.bee.master.application.request.trainingCamp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Getter
@Setter
@NoArgsConstructor
public class PaginationRequest {

    public static final String DESC_FLAG = "-";
    public static final String ASC_FLAG = "+";
    @ApiModelProperty(required = false, example = "0")
    private Integer pageIndex;

    @ApiModelProperty(required = false, example = "10")
    private Integer pageSize;

    private String sort;

    public Pageable toPageable() {
        if (pageIndex == null || pageSize == null) {
            return Pageable.unpaged();
        }

        if (isBlank(sort)) {
            return PageRequest.of(pageIndex, pageSize);
        }
        return PageRequest.of(pageIndex, pageSize, getSortDirection(sort), getSortColumns(sort));
    }

    private String[] getSortColumns(String sort) {
        String[] sorts = sort.split(",");
        return Arrays.stream(sorts).map(column -> {
            if (column.startsWith(DESC_FLAG) || column.startsWith(ASC_FLAG)) {
                return column.substring(1);
            }
            return column;
        }).toArray(String[]::new);
    }

    private Sort.Direction getSortDirection(String sort) {
        return sort.startsWith(DESC_FLAG) ? Sort.Direction.DESC : Sort.Direction.ASC;
    }

}
