package com.test.api;

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
import com.test.model.TTRequestHeaderWrapper;

/**base request model*/
public class TTBaseRequestWrapper extends XBaseRecordWrapper {
    private ObjectHolder<TTRequestHeaderWrapper> headerHolder;

    @Override
    protected void initFieldValueHolder() {
        super.initFieldValueHolder();
        this.headerHolder = new ObjectHolder(this,9994245,new TTRequestHeaderWrapper.Factory());
    }

    public TTBaseRequestWrapper(XData _data) {
        super(_data);
    }

    public TTBaseRequestWrapper() {
        super(10027008);
    }

    protected TTBaseRequestWrapper(int type) {
        super(type);
    }

    public void setHeader(TTRequestHeaderWrapper header) {
        headerHolder.set(header);
    }

    public TTRequestHeaderWrapper getHeader() {
        return headerHolder.get();
    }

    public static class Factory implements Converter<TTBaseRequestWrapper> {
        @Override 
        public TTBaseRequestWrapper convert(XData xdata) {
           return new TTBaseRequestWrapper(xdata);
        }
    }

}