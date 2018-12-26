package com.tnicy.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Period {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int pid;//时段号，标示所有场地的各个时段
    private Integer sid;//表示属于哪一个场地的时段
    private Integer isOccupied;
    private Integer date;
    private Integer startTime;
    private Integer endTime;
    private Integer cost;
    public Period(){

    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(Integer isOccupied) {
        this.isOccupied = isOccupied;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
