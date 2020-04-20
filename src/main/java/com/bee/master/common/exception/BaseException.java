package com.bee.master.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException {

    private ErrorCode errorCode;

    private String message;
}
