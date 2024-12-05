package com.gfg.JBDL_76_MinorP1.aspects;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class RequestResponseLoggingAspect {

    //private static final Logger LOGGER = LoggerFactory.getLogger(RequestResponseLoggingAspect.class);
    private static final Logger log = LoggerFactory.getLogger(RequestResponseLoggingAspect.class);

    @Pointcut("execute(* com.gfg.JBDL_76_MinorP1.controller..*(..))")
    public void controllerMethods(){} // declaration, marker, use this for multiple advices

    //@Before("controllerMethods()") // or
    @Before("execution(* com.gfg.JBDL_76_MinorP1.controller..*(..))")
    public void logRequest() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("Request: " +request.getMethod() +" "+ request.getRequestURI()); //  log.info==System.out.println
        log.info("Request Headers");
        request.getHeaderNames().asIterator().forEachRemaining(header-> log.info(header+":" + request.getHeader(header)));
    }

    //@AfterReturning(pointcut = "execute(* com.gfg.JBDL_76_MinorP1.controller..*(..))", returning = "response")// or
    @AfterReturning(value = "controllerMethods()", returning = "response")
    public void logResponse(Object response) {
        if(response instanceof HttpServletResponse) {
            HttpServletResponse response1 = (HttpServletResponse) response;
            log.info("response status" + response1.getStatus());
        }else {
            log.info("Response :" + response.toString());
        }
    }
}
