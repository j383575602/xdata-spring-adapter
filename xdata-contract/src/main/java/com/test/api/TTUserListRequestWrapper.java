package com.test.api;

import top.xcore.xdata.IntMapHolder;
import java.util.Set;
import top.xcore.xdata.XDataWrapper;
import java.util.ArrayList;
import top.xcore.xdata.XData;
import top.xcore.xdata.ListObjectHolder;
import java.util.List;
import top.xcore.xdata.ObjectHolder;
import top.xcore.xdata.SetObjectHolder;
import java.util.Map;
import top.xcore.xdata.FloatMapHolder;
import top.xcore.xdata.DoubleMapHolder;
import top.xcore.xdata.StringMapHolder;
import top.xcore.xdata.LongMapHolder;
import top.xcore.xdata.Converter;

/**request to list user*/
public class TTUserListRequestWrapper extends TTBaseRequestWrapper {
    public TTUserListRequestWrapper(XData _data) {
        super(_data);
    }

    public TTUserListRequestWrapper() {
        super(10059776);
    }

    protected TTUserListRequestWrapper(int type) {
        super(type);
    }

    public void setStartIndex(int startIndex) {
        super.set(1030,startIndex);
    }

    public int getStartIndex() {
        return super.getInteger(1030);
    }

    public void setCount(int count) {
        super.set(1031,count);
    }

    public int getCount() {
        return super.getInteger(1031);
    }

    public static class Factory implements Converter<TTUserListRequestWrapper> {
        @Override 
        public TTUserListRequestWrapper convert(XData xdata) {
           return new TTUserListRequestWrapper(xdata);
        }
    }

}