package com.test.model;



/**response header*/
public class TTResponseHeader {

    private short code;
    private String message;

    public void setCode(short code) {
        this.code = code;
    }

    public short getCode() {
        return this.code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }


}