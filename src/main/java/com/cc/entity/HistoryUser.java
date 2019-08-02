package com.cc.entity;

import java.io.Serializable;

public class HistoryUser implements Serializable {

    private Integer id;             //id
    private Integer mid;            //历史会议id
    private Integer uid;            //用户id

    private User user;
    private HistoryMeet historyMeet;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HistoryMeet getHistoryMeet() {
        return historyMeet;
    }

    public void setHistoryMeet(HistoryMeet historyMeet) {
        this.historyMeet = historyMeet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "HistoryUser{" +
                "id=" + id +
                ", mid=" + mid +
                ", uid=" + uid +
                ", user=" + user +
                ", historyMeet=" + historyMeet +
                '}';
    }
}
