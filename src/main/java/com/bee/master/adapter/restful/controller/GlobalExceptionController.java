package com.bee.master.adapter.restful.controller;

import com.bee.master.common.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("${server.error.path:/error}")
public class GlobalExceptionController extends AbstractErrorController {

    public static final String ERROR_MESSAGE = "message";
    private final ErrorProperties errorProperties;

    @Autowired
    public GlobalExceptionController(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
        super(errorAttributes);
        this.errorProperties = serverProperties.getError();
    }

    @RequestMapping
    public ResponseEntity<BaseException> handleError(HttpServletRequest request) {
        Map<String, Object> errorAttributes = getErrorAttributes(request, false);
        String errorMessage = errorAttributes.get(ERROR_MESSAGE).toString();
        HttpStatus status = getStatus(request);
        return ResponseEntity.status(status).body(BaseException.fromHttpError(errorMessage, status.value()));
    }

    @Override
    public String getErrorPath() {
        return errorProperties.getPath();
    }
}
