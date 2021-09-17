package com.test;

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

/**user model*/
public class XUserWrapper extends XDataWrapper {
    private ListObjectHolder<XCarWrapper> carsHolder;
    private StringMapHolder<XCarWrapper> taggedCarsHolder;

    @Override
    protected void initFieldValueHolder() {
        super.initFieldValueHolder();
        this.carsHolder = new ListObjectHolder(this,9932802,new XCarWrapper.Factory());
        this.taggedCarsHolder = new StringMapHolder(this,9945091,new XCarWrapper.Factory());
    }

    public XUserWrapper(XData _data) {
        super(_data);
    }

    public XUserWrapper() {
        super(9961472);
    }

    protected XUserWrapper(int type) {
        super(type);
    }

    public void setName(String name) {
        super.set(2049,name);
    }

    public String getName() {
        return super.getString(2049);
    }

    public void setCars(List<XCarWrapper> cars) {
        this.carsHolder.set(cars);
    }

    public List<XCarWrapper> getCars() {
        return this.carsHolder.get();
    }

    public void setTaggedCars(Map<String,XCarWrapper> taggedCars) {
        this.taggedCarsHolder.set(taggedCars);
    }

    public Map<String,XCarWrapper> getTaggedCars() {
        return this.taggedCarsHolder.get();
    }

    public static class Factory implements Converter<XUserWrapper> {
        @Override 
        public XUserWrapper convert(XData xdata) {
           return new XUserWrapper(xdata);
        }
    }

}