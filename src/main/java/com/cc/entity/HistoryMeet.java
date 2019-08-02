package com.cc.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class HistoryMeet implements Serializable {
    private Integer id;
    private String meetName;
    private String originator;
    private String meetType;
    private String starttime;
    private String duration;
    private String roomId;
    private List<MeetUser> meetperson;
    private String meetTitle;
    private String meetDescribe;
    private String meetRoom;
    private String param;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeetName() {
        return meetName;
    }

    public void setMeetName(String meetName) {
        this.meetName = meetName;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getMeetType() {
        return meetType;
    }

    public void setMeetType(String meetType) {
        this.meetType = meetType;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public List<MeetUser> getMeetperson() {
        return meetperson;
    }

    public void setMeetperson(List<MeetUser> meetperson) {
        this.meetperson = meetperson;
    }

    public String getMeetTitle() {
        return meetTitle;
    }

    public void setMeetTitle(String meetTitle) {
        this.meetTitle = meetTitle;
    }

    public String getMeetDescribe() {
        return meetDescribe;
    }

    public void setMeetDescribe(String meetDescribe) {
        this.meetDescribe = meetDescribe;
    }

    public String getMeetRoom() {
        return meetRoom;
    }

    public void setMeetRoom(String meetRoom) {
        this.meetRoom = meetRoom;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
