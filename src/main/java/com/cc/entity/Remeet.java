package com.cc.entity;



import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Remeet implements Serializable {

    private Integer id;                     //会议ID
    private String  meetName;               //会议名称
    private Integer uri;                    //会议号
    private String requireCallId;           //会议密码
    private String defaultLayout;           //调用api所需参数
    private Integer callId;                 //调用api所需参数
    private String nonMemberAccess ;        //调用api所需参数
    private String callProfile;             //调用api所需参数
    private String callLegProfile;          //调用api所需参数
    private String meetDate;                //会议开始日期
    private String meetTime;                //会议时间
    private String meetLaber;               //会议标签
    private String meetDescription;         //会议描述
    private String meetRoomId;              //会议类型
    private String meetType;                //会议室id
    private Integer state;                  //会议状态
    private String meetRoomName;            //会议室名称
    private String userId;                  //用户Id
    private String repeatType;              //重复类型
    private Integer rid;                    //重复会议id

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
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

    public String getMeetName() {
        return meetName;
    }

    public void setMeetName(String meetName) {
        this.meetName = meetName;
    }

    public Integer getUri() {
        return uri;
    }

    public void setUri(Integer uri) {
        this.uri = uri;
    }

    public String getRequireCallId() {
        return requireCallId;
    }

    public void setRequireCallId(String requireCallId) {
        this.requireCallId = requireCallId;
    }

    public String getDefaultLayout() {
        return defaultLayout;
    }

    public void setDefaultLayout(String defaultLayout) {
        this.defaultLayout = defaultLayout;
    }

    public Integer getCallId() {
        return callId;
    }

    public void setCallId(Integer callId) {
        this.callId = callId;
    }

    public String getNonMemberAccess() {
        return nonMemberAccess;
    }

    public void setNonMemberAccess(String nonMemberAccess) {
        this.nonMemberAccess = nonMemberAccess;
    }

    public String getCallProfile() {
        return callProfile;
    }

    public void setCallProfile(String callProfile) {
        this.callProfile = callProfile;
    }

    public String getCallLegProfile() {
        return callLegProfile;
    }

    public void setCallLegProfile(String callLegProfile) {
        this.callLegProfile = callLegProfile;
    }

    public String getMeetDate() {
        return meetDate;
    }

    public void setMeetDate(String meetDate) {
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
