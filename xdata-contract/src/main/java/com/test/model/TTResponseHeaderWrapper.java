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

/**response header*/
public class TTResponseHeaderWrapper extends XBaseRecordWrapper {
    public TTResponseHeaderWrapper(XData _data) {
        super(_data);
    }

    public TTResponseHeaderWrapper() {
        super(10158080);
    }

    protected TTResponseHeaderWrapper(int type) {
        super(type);
    }

    public void setCode(short code) {
        super.set(773,code);
    }

    public short getCode() {
        return super.getShort(773);
    }

    public void setMessage(String message) {
        super.set(2054,message);
    }

    public String getMessage() {
        return super.getString(2054);
    }

    public static class Factory implements Converter<TTResponseHeaderWrapper> {
        @Override 
        public TTResponseHeaderWrapper convert(XData xdata) {
           return new TTResponseHeaderWrapper(xdata);
        }
    }

}