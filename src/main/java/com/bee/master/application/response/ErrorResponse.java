package com.bee.master.application.response;

import com.bee.master.common.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String errorCode;

    private String message;

    private int status;

    public static ErrorResponse from(BaseException exception) {
        return new ErrorResponse(exception.getErrorCode(), exception.getMessage(), exception.getStatus().value());
    }
}
