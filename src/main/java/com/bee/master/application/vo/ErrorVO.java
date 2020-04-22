package com.bee.master.application.vo;

import com.bee.master.common.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorVO {
    private String errorCode;

    private String message;

    private int status;

    public static ErrorVO from(BaseException exception) {
        return new ErrorVO(exception.getErrorCode(), exception.getMessage(), exception.getStatus().value());
    }
}