package com.gfg.JBDL_76_MinorP1.controller;

import com.gfg.JBDL_76_MinorP1.dto.request.TxnRequest;
import com.gfg.JBDL_76_MinorP1.dto.request.TxnreturnRequest;
import com.gfg.JBDL_76_MinorP1.exception.BookException;
import com.gfg.JBDL_76_MinorP1.exception.UserException;
import com.gfg.JBDL_76_MinorP1.service.impl.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/txn")
//@Validated
public class TxnController {

    @Autowired
    private TxnService txnService;

    @PostMapping("/issue")
    public ResponseEntity<String> create(@RequestBody @Validated TxnRequest txnRequest) throws UserException, BookException {
         String txnId =  txnService.create(txnRequest);
         if(txnId != null || !txnId.isEmpty()) {
             return new ResponseEntity<>(txnId, HttpStatus.OK);
         }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/return")
    public Double returnTxn(@RequestBody TxnreturnRequest txnreturnRequest) throws BookException, UserException {
        return txnService.returnTxn(txnreturnRequest);
    }
}
