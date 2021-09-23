package com.test.api;

import com.test.model.TTRequestHeader;

/**base request model*/
public class TTBaseRequest {
    private TTRequestHeader headerHolder;

    public void setHeader(TTRequestHeader header) {

        this.headerHolder = header;
    }

    public TTRequestHeader getHeader() {
        return headerHolder;
    }
}