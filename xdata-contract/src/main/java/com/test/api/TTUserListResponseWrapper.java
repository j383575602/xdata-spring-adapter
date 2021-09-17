package com.test.api;

import top.xcore.xdata.IntMapHolder;
import java.util.Set;
import top.xcore.xdata.XDataWrapper;
import java.util.ArrayList;
import top.xcore.xdata.XData;
import top.xcore.xdata.ListObjectHolder;
import java.util.List;
import top.xcore.xdata.ObjectHolder;
import com.test.XUserWrapper;
import top.xcore.xdata.SetObjectHolder;
import java.util.Map;
import top.xcore.xdata.FloatMapHolder;
import top.xcore.xdata.DoubleMapHolder;
import top.xcore.xdata.StringMapHolder;
import top.xcore.xdata.LongMapHolder;
import top.xcore.xdata.Converter;

/**use list response*/
public class TTUserListResponseWrapper extends TTBaseResponseWrapper {
    private ListObjectHolder<XUserWrapper> usersHolder;

    @Override
    protected void initFieldValueHolder() {
        super.initFieldValueHolder();
        this.usersHolder = new ListObjectHolder(this,9965575,new XUserWrapper.Factory());
    }

    public TTUserListResponseWrapper(XData _data) {
        super(_data);
    }

    public TTUserListResponseWrapper() {
        super(10125312);
    }

    protected TTUserListResponseWrapper(int type) {
        super(type);
    }

    public void setUsers(List<XUserWrapper> users) {
        this.usersHolder.set(users);
    }

    public List<XUserWrapper> getUsers() {
        return this.usersHolder.get();
    }

    public void setLastIndex(int lastIndex) {
        super.set(1032,lastIndex);
    }

    public int getLastIndex() {
        return super.getInteger(1032);
    }

    public static class Factory implements Converter<TTUserListResponseWrapper> {
        @Override 
        public TTUserListResponseWrapper convert(XData xdata) {
           return new TTUserListResponseWrapper(xdata);
        }
    }

}