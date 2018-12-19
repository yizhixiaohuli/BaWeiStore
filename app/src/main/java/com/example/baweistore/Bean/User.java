package com.example.baweistore.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * date:2018/12/15
 * author:别的小朋友(别的小朋友)
 * function:
 */
@Entity
public class User {
    @Id(autoincrement = true)
    private Long id;
    private int cid;
    private int count;
    @Generated(hash = 1797819045)
    public User(Long id, int cid, int count) {
        this.id = id;
        this.cid = cid;
        this.count = count;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getCid() {
        return this.cid;
    }
    public void setCid(int cid) {
        this.cid = cid;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }

}
