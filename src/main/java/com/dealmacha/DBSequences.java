package com.dealmacha;

public enum DBSequences {

    ADMINUSER("AUSER"),
    USERS("USER"),
    RECEIPT("RCPT"),
    CUSTOMER("CUST"),
    TRANSACTION("TRANS"),
    MERCHANTCATEGORYCODE("MERCAT");
    private String sequenceName;

    DBSequences(final String sequenceCode) {
        sequenceName = sequenceCode;
    }

    public String getSequenceName() {
        return sequenceName;
    }

}
