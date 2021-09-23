package com.test.api;

import com.test.XUser;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**use list response*/
public class TTUserListResponse extends TTBaseResponse {
    private List<XUser> usersHolder = new ArrayList<>();
    int lastIndex;



    public void setUsers(List<XUser> users) {
        this.usersHolder = (users);
    }

    public List<XUser> getUsers() {
        return this.usersHolder;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    public int getLastIndex() {
        return this.lastIndex;
    }

}