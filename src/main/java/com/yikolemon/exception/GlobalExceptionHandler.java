package com.yikolemon.exception;

import com.yikolemon.vo.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(PageException.class)
    public AjaxResult globalException(HttpServletResponse response, NullPointerException ex) {
        log.info("GlobalExceptionHandler...");
        log.info("错误代码：" + response.getStatus());
        return AjaxResult.addError("分页数据错误");
    }
}