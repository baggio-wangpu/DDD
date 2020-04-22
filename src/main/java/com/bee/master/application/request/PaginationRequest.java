package com.bee.master.application.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PaginationRequest {

    @NotNull
    @ApiModelProperty(required = true, example = "0")
    private Integer pageIndex;

    @NotNull
    @ApiModelProperty(required = true, example = "10")
    private Integer pageSize;

    private String sort;
}
