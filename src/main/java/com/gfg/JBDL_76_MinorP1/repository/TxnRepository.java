package com.gfg.JBDL_76_MinorP1.repository;

import com.gfg.JBDL_76_MinorP1.model.Txn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxnRepository extends JpaRepository<Txn, Integer> {

    Txn findByTxnId(String txnId);
}


