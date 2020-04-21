package com.bee.master.common.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import static com.bee.master.common.exception.ErrorCode.BAD_REQUEST;
import static com.bee.master.common.exception.ErrorCode.FORBIDDEN;
import static com.bee.master.common.exception.ErrorCode.SERVER_ERROR;
import static com.bee.master.common.exception.ErrorCode.UNAUTHORIZED;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties( { "stackTrace", "cause", "suppressed", "localizedMessage" })
public class BaseException extends RuntimeException {

    protected ErrorCode errorCode;

    protected String message;

    protected int status;

    public static BaseException unknown(Throwable throwable) {
        return new BaseException(SERVER_ERROR, StringUtils.defaultIfEmpty(throwable.getMessage(), throwable.toString()),
                HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    public static BaseException forbidden(String message) {
        return new BaseException(FORBIDDEN, message, HttpStatus.FORBIDDEN.value());
    }

    public static BaseException unauthorized(String message) {
        return new BaseException(UNAUTHORIZED, message, HttpStatus.UNAUTHORIZED.value());
    }

    public static BaseException badRequest(String message) {
        return new BaseException(BAD_REQUEST, message, HttpStatus.BAD_REQUEST.value());
    }

    public static BaseException fromHttpError(String errorMessage, int status) {
        return new BaseException(SERVER_ERROR, errorMessage, status);
    }
}
