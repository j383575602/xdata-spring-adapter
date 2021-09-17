package com.test.api;

import top.xcore.xdata.IntMapHolder;
import java.util.Set;
import top.xcore.xdata.XDataWrapper;
import java.util.ArrayList;
import com.test.model.TTResponseHeaderWrapper;
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

/**base response model*/
public class TTBaseResponseWrapper extends XBaseRecordWrapper {
    private ObjectHolder<TTResponseHeaderWrapper> headerHolder;

    @Override
    protected void initFieldValueHolder() {
        super.initFieldValueHolder();
        this.headerHolder = new ObjectHolder(this,10158085,new TTResponseHeaderWrapper.Factory());
    }

    public TTBaseResponseWrapper(XData _data) {
        super(_data);
    }

    public TTBaseResponseWrapper() {
        super(10092544);
    }

    protected TTBaseResponseWrapper(int type) {
        super(type);
    }

    public void setHeader(TTResponseHeaderWrapper header) {
        headerHolder.set(header);
    }

    public TTResponseHeaderWrapper getHeader() {
        return headerHolder.get();
    }

    public void setMessage(String message) {
        super.set(2054,message);
    }

    public String getMessage() {
        return super.getString(2054);
    }

    public static class Factory implements Converter<TTBaseResponseWrapper> {
        @Override 
        public TTBaseResponseWrapper convert(XData xdata) {
           return new TTBaseResponseWrapper(xdata);
        }
    }

}