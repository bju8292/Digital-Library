package com.gfg.JBDL_76_MinorP1.enums;

import lombok.Getter;

@Getter
public enum Operator {
    EQUALS("="),
    LESS_THAN("<"),
    LESS_THAN_EQUAL("<="),
    IN("IN"),
    LIKE("LIKE");

    private String value;

    Operator(String value) {
        this.value = value;
    }

}
