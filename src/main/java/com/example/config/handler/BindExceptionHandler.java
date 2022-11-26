package com.example.config.handler;

import com.example.model.vo.response.ParameterErrorVO;
import com.example.model.vo.response.ResultVO;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestControllerAdvice
public class BindExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultVO handleBindException(MethodArgumentNotValidException ex, HttpServletResponse response) {
        List<ParameterErrorVO> errorVOList = new ArrayList<>();

        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
        for (ObjectError error : errorList) {
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errorVOList.add(new ParameterErrorVO(fieldName, message));
        }

        errorVOList.sort(Comparator.comparing(ParameterErrorVO::getFieldName));
        String message = "请求参数无效";
        if (errorVOList.size() > 0) {
            ParameterErrorVO firstError = errorVOList.get(0);
            message += ": " + firstError.getFieldName() + "-" + firstError.getMessage();
        }

        response.setStatus(BAD_REQUEST.value());
        return new ResultVO(ResultVO.INVALID_PARAMETER, message, errorVOList);
    }
}
