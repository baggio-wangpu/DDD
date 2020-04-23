package com.bee.master.application.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Getter
@Setter
@NoArgsConstructor
public class PaginationRequest {

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
