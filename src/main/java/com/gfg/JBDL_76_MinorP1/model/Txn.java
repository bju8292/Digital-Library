package com.gfg.JBDL_76_MinorP1.model;

import com.gfg.JBDL_76_MinorP1.enums.TxnStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class Txn extends TimeStamps{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "txn_id") // Specify the correct column name in the database
    private String txnId;

    private TxnStatus txnStatus;

    private Double SettlementAmount; // depending upon when you are returning the book

    private Date issuedDate;

    private Date submittedDate;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Book book;
}
