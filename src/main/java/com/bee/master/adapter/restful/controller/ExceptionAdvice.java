package com.bee.master.adapter.restful.controller;

import com.bee.master.application.vo.ErrorVO;
import com.bee.master.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorVO> handle(BaseException exception) {
        log.error(exception.getLocalizedMessage(), exception);
        return ResponseEntity.status(exception.getStatus()).body(ErrorVO.from(exception));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorVO handle(Throwable throwable) {
        log.error(throwable.getMessage());
        return ErrorVO.from(BaseException.unknown(throwable));
    }

}
