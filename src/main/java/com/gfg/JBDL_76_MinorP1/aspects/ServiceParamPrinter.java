package com.gfg.JBDL_76_MinorP1.aspects;

import com.gfg.JBDL_76_MinorP1.dto.request.TxnRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceParamPrinter {

    private static final Logger log = LoggerFactory.getLogger(ServiceParamPrinter.class);

    @Pointcut("execution(* com.gfg.JBDL_76_MinorP1.service.impl.TxnService.create(com.gfg.JBDL_76_MinorP1.dto.request.TxnRequest)")
    public void txnServiceCreateMethod() {
    }

    @Before(value = "txnServiceCreationMethod() && args(txnRequest)")
    public void txnServiceAdvice(TxnRequest txnRequest) {
        log.info("txnRequest is :" + txnRequest);
    }
}
