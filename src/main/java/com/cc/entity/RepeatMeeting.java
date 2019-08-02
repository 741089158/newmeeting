package com.cc.entity;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RepeatMeeting {


    private int id;
    private String createTime;
    private String endTime;
    private String meetName;
    private String lastMeetingTime;
    private String nextMeetingTime;
    private String meetRoomName;
    private String type;
    private String meetTime;
    private String description;
    private String repeatType;
    private int status;
    private int isDefault;
    private String userId;
    private String roomId;
    private String title;
    private String weeks;
    private String defaultlayout;

    public String getDefaultlayout() {
        return defaultlayout;
    }

    public void setDefaultlayout(String defaultlayout) {
        this.defaultlayout = defaultlayout;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMeetName() {
        return meetName;
    }

    public void setMeetName(String meetName) {
        this.meetName = meetName;
    }

    public String getLastMeetingTime() {
        return lastMeetingTime;
    }

    public void setLastMeetingTime(String lastMeetingTime) {
        this.lastMeetingTime = lastMeetingTime;
    }

    public String getNextMeetingTime() {
        return nextMeetingTime;
    }

    public void setNextMeetingTime(String nextMeetingTime) {
        this.nextMeetingTime = nextMeetingTime;
    }

    public String getMeetRoomName() {
        return meetRoomName;
    }

    public void setMeetRoomName(String meetRoomName) {
        this.meetRoomName = meetRoomName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getMeetTime() {
        return meetTime;
    }

    public void setMeetTime(String meetTime) {
        this.meetTime = meetTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(String repeatType) {
        this.repeatType = repeatType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
