package com.cc.entity;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Appointment_Meeting implements Serializable {
    private Integer id;
    private String name;
    private Integer url;
    private String requirecallid;
    private String defaultlayout;
    private Integer callid;
    private String nonmemberaccess;
    private String callProfile;
    private String callLegprofile;
    private Timestamp meetDate;
    private String  meetTime;
    private String meetLaber;
    private String meetDescription;
    private String meetRoomId;
    private String meetType;
    private Integer state;
    private String meetRoomName;
    private String userId;
    private String repeatType;


    @Override
    public String toString() {
        return "Appointment_Meeting{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url=" + url +
                ", requirecallid='" + requirecallid + '\'' +
                ", defaultlayout='" + defaultlayout + '\'' +
                ", callid=" + callid +
                ", nonmemberaccess='" + nonmemberaccess + '\'' +
                ", callProfile='" + callProfile + '\'' +
                ", callLegprofile='" + callLegprofile + '\'' +
                ", meetDate=" + meetDate +
                ", meetTime='" + meetTime + '\'' +
                ", meetLaber='" + meetLaber + '\'' +
                ", meetDescription='" + meetDescription + '\'' +
                ", meetRoomId='" + meetRoomId + '\'' +
                ", meetType='" + meetType + '\'' +
                ", state=" + state +
                ", meetRoomName='" + meetRoomName + '\'' +
                ", userId='" + userId + '\'' +
                ", repeatType='" + repeatType + '\'' +
                '}';
    }

    public String getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(String repeatType) {
        this.repeatType = repeatType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUrl() {
        return url;
    }

    public void setUrl(Integer url) {
        this.url = url;
    }

    public String getRequirecallid() {
        return requirecallid;
    }

    public void setRequirecallid(String requirecallid) {
        this.requirecallid = requirecallid;
    }

    public String getDefaultlayout() {
        return defaultlayout;
    }

    public void setDefaultlayout(String defaultlayout) {
        this.defaultlayout = defaultlayout;
    }

    public Integer getCallid() {
        return callid;
    }

    public void setCallid(Integer callid) {
        this.callid = callid;
    }

    public String getNonmemberaccess() {
        return nonmemberaccess;
    }

    public void setNonmemberaccess(String nonmemberaccess) {
        this.nonmemberaccess = nonmemberaccess;
    }

    public String getCallProfile() {
        return callProfile;
    }

    public void setCallProfile(String callProfile) {
        this.callProfile = callProfile;
    }

    public String getCallLegprofile() {
        return callLegprofile;
    }

    public void setCallLegprofile(String callLegprofile) {
        this.callLegprofile = callLegprofile;
    }

    public Timestamp getMeetDate() {
        return meetDate;
    }

    public void setMeetDate(Timestamp meetDate) {
        this.meetDate = meetDate;
    }

    public String getMeetTime() {
        return meetTime;
    }

    public void setMeetTime(String meetTime) {
        this.meetTime = meetTime;
    }

    public String getMeetLaber() {
        return meetLaber;
    }

    public void setMeetLaber(String meetLaber) {
        this.meetLaber = meetLaber;
    }

    public String getMeetDescription() {
        return meetDescription;
    }

    public void setMeetDescription(String meetDescription) {
        this.meetDescription = meetDescription;
    }

    public String getMeetRoomId() {
        return meetRoomId;
    }

    public void setMeetRoomId(String meetRoomId) {
        this.meetRoomId = meetRoomId;
    }

    public String getMeetType() {
        return meetType;
    }

    public void setMeetType(String meetType) {
        this.meetType = meetType;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMeetRoomName() {
        return meetRoomName;
    }

    public void setMeetRoomName(String meetRoomName) {
        this.meetRoomName = meetRoomName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
