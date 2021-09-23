package com.test.api;

import com.test.model.TTResponseHeader;

/**base response model*/
public class TTBaseResponse  {
    private TTResponseHeader headerHolder;



    public void setHeader(TTResponseHeader header) {
        this.headerHolder = header;
    }

    public TTResponseHeader getHeader() {
        return headerHolder;
    }

}