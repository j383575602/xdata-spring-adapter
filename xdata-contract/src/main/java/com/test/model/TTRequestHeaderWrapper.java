package com.test.model;

import top.xcore.xdata.IntMapHolder;
import java.util.Set;
import top.xcore.xdata.XDataWrapper;
import java.util.ArrayList;
import top.xcore.xdata.XData;
import top.xcore.xdata.ListObjectHolder;
import java.util.List;
import top.xcore.xdata.ObjectHolder;
import top.xcore.xdata.XBaseRecordWrapper;
import top.xcore.xdata.SetObjectHolder;
import java.util.Map;
import top.xcore.xdata.FloatMapHolder;
import top.xcore.xdata.DoubleMapHolder;
import top.xcore.xdata.StringMapHolder;
import top.xcore.xdata.LongMapHolder;
import top.xcore.xdata.Converter;

/**header*/
public class TTRequestHeaderWrapper extends XBaseRecordWrapper {
    public TTRequestHeaderWrapper(XData _data) {
        super(_data);
    }

    public TTRequestHeaderWrapper() {
        super(9994240);
    }

    protected TTRequestHeaderWrapper(int type) {
        super(type);
    }

    public void setToken(String token) {
        super.set(2053,token);
    }

    public String getToken() {
        return super.getString(2053);
    }

    public void setRequestId(String requestId) {
        super.set(2054,requestId);
    }

    public String getRequestId() {
        return super.getString(2054);
    }

    public void setSessionId(String sessionId) {
        super.set(2055,sessionId);
    }

    public String getSessionId() {
        return super.getString(2055);
    }

    public static class Factory implements Converter<TTRequestHeaderWrapper> {
        @Override 
        public TTRequestHeaderWrapper convert(XData xdata) {
           return new TTRequestHeaderWrapper(xdata);
        }
    }

}