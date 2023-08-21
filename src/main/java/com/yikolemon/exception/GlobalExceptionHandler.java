package com.yikolemon.exception;

import com.yikolemon.vo.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxResult validationBodyException(MethodArgumentNotValidException exception){

        StringBuffer buffer = new StringBuffer();

        BindingResult result = exception.getBindingResult();
        if (result.hasErrors()) {

            List<ObjectError> errors = result.getAllErrors();

            errors.forEach(p ->{

                FieldError fieldError = (FieldError) p;
                log.error("Data check failure : object{"+fieldError.getObjectName()+"},field{"+fieldError.getField()+
                        "},errorMessage{"+fieldError.getDefaultMessage()+"}");
                buffer.append(fieldError.getDefaultMessage()).append(",");
            });
        }
        return AjaxResult.addError(buffer.toString().substring(0, buffer.toString().length() - 1));

    }
}