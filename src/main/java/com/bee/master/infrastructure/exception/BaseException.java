package com.bee.master.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException {

    protected String errorCode;

    protected String message;

    protected HttpStatus status;

    public static BaseException unknown(Throwable throwable) {
        return new BaseException(INTERNAL_SERVER_ERROR.name(), StringUtils.defaultIfEmpty(throwable.getMessage(),
                throwable.toString()),
                INTERNAL_SERVER_ERROR);
    }

    public static BaseException forbidden(String message) {
        return new BaseException(FORBIDDEN.name(), message, FORBIDDEN);
    }

    public static BaseException unauthorized(String message) {
        return new BaseException(UNAUTHORIZED.name(), message, UNAUTHORIZED);
    }

    public static BaseException badRequest(String message) {
        return new BaseException(BAD_REQUEST.name(), message, BAD_REQUEST);
    }

    public static BaseException notFound(String resourceType, String id) {
        return new BaseException(NOT_FOUND.name(), String.format("%s %s is not found", resourceType, id), NOT_FOUND);
    }

    public static BaseException fromHttpError(String errorMessage, HttpStatus status) {
        return new BaseException(status.name(), errorMessage, status);
    }
}
