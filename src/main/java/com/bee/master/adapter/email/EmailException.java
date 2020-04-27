package com.bee.master.adapter.email;

import com.bee.master.common.exception.BaseException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class EmailException extends BaseException {

    public EmailException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage, INTERNAL_SERVER_ERROR);
    }

    public static EmailException sendFailed(String errorCode, String errorMessage) {
        return new EmailException(errorCode, errorMessage);
    }
}
