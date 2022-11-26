package com.example.config.aop;

import com.example.model.vo.response.ResultVO;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

import static com.example.model.vo.response.ResultVO.FAIL;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@Aspect
@Component
public class ResultFailedCodeAspect {
    @Pointcut("execution(public com.example.model.vo.response.ResultVO com.example.controller..*.*(..))")
    public void controllerResult() {
    }

    @AfterReturning(value = "controllerResult()", returning = "result")
    public Object afterReturning(ResultVO result) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return result;
        }
        HttpServletResponse response = requestAttributes.getResponse();
        if (response == null) {
            return result;
        }

        if (result.getCode() == FAIL) {
            response.setStatus(NOT_ACCEPTABLE.value());
        }

        return result;
    }
}
