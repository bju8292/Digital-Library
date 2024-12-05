package com.gfg.JBDL_76_MinorP1.model;

import com.gfg.JBDL_76_MinorP1.enums.UserStatus;
import com.gfg.JBDL_76_MinorP1.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "\"USER\"")
public class User extends TimeStamps{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String name;

    @Column(nullable = true, unique=true, length = 15)
    private String phoneNo;

    @Column(nullable = true, unique=true, length = 50)
    private String email;

    private String address;

    @Enumerated
    private UserStatus userstatus;

    private UserType usertype;

    @Transient
    private String temp;

    @OneToMany(mappedBy = "user")
    private List<Book> bookList;

    @OneToMany(mappedBy = "user")
    private List<Txn> txnList;
}
