package com.test.api;


import java.util.Set;
import java.util.ArrayList;

import java.util.List;

import java.util.Map;


/**request to list user*/
public class TTUserListRequest extends TTBaseRequest{
   private int startIndex;
   private int count;

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}