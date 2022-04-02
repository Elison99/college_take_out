package org.example.college.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */
//@ControllerAdvice(annotations = {RestController.class, Controller.class})
//@ResponseBody
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<String> exceptionHandler(SQLIntegrityConstraintViolationException exception){
        log.info(exception.getMessage());

        if (exception.getMessage().contains("Duplicate entry")){
            String[] strings = exception.getMessage().split(" ");
            String message = strings[2]+"已存在";
            return Result.error(message);

        }

        return Result.error("未知错误");
    }

    /**
     * 异常处理方法
     * @param exception
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public Result<String> exceptionHandler(CustomException exception){
        log.error(exception.getMessage());


        return Result.error(exception.getMessage());
    }
}
