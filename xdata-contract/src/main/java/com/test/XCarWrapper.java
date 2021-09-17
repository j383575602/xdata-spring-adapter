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

/**car model*/
public class XCarWrapper extends XDataWrapper {
    public XCarWrapper(XData _data) {
        super(_data);
    }

    public XCarWrapper() {
        super(9928704);
    }

    protected XCarWrapper(int type) {
        super(type);
    }

    public void setBrand(String brand) {
        super.set(2049,brand);
    }

    public String getBrand() {
        return super.getString(2049);
    }

    public static class Factory implements Converter<XCarWrapper> {
        @Override 
        public XCarWrapper convert(XData xdata) {
           return new XCarWrapper(xdata);
        }
    }

}