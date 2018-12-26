package com.tnicy.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Site {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int sid;
    private String  sName;
    private String sDesc;//场地介绍

    public Site() {
    }
    public Site(int sid,String sDesc,String sName){
        this.sid=sid;
        this.sDesc=sDesc;
        this.sName=sName;
    }
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsDesc() {
        return sDesc;
    }

    public void setsDesc(String sDesc) {
        this.sDesc = sDesc;
    }
}
