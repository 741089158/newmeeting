package com.cc.entity;

import java.io.Serializable;



public class MeetRoom implements Serializable {

    private String roomId;//会议室号
    private String roomCode;
    private String areaId;//区域id
    private String roomArea;//会议室区域
    private String roomFloor;//会议室楼层
    private String roomBuilding;//创建者
    private String roomName;//会议室名称
    private String personCount;//容纳人数
    private String roomStatus;//状态
    private String createDate;//创建时间
    private String createUser;//创建人
    private String lastUpdateDate;//上次修改时间
    private String lastUpdateUser;//上次修改人
    private String managerUid;//管理者id
    private String manager;//管理者
    private String isExamine;//
    private String roomEmail;//会议室邮件
    private String meetingSubDeptId;//会议室分部id
    private String meetingSubdept;//会议室分部
    private String roomAreaName;//会议室地区名
    private String roomRegion;//
    private String isPublic;//是否公共
    private String callIp;//呼叫ip
    private String isStart;//是否开启
    private String roomType;//会议室类型
    private String subOrg; // 所属
    
    public MeetRoom(String roomId, String roomCode, String areaId, String roomArea, String roomFloor, String roomBuilding, String roomName, String personCount, String roomStatus, String createDate, String createUser, String lastUpdateDate, String lastUpdateUser, String managerUid, String manager, String isExamine, String roomEmail, String meetingSubDeptId, String meetingSubdept, String roomAreaName, String roomRegion, String isPublic, String callIp, String isStart, String roomType) {
        this.roomId = roomId;
        this.roomCode = roomCode;
        this.areaId = areaId;
        this.roomArea = roomArea;
        this.roomFloor = roomFloor;
        this.roomBuilding = roomBuilding;
        this.roomName = roomName;
        this.personCount = personCount;
        this.roomStatus = roomStatus;
        this.createDate = createDate;
        this.createUser = createUser;
        this.lastUpdateDate = lastUpdateDate;
        this.lastUpdateUser = lastUpdateUser;
        this.managerUid = managerUid;
        this.manager = manager;
        this.isExamine = isExamine;
        this.roomEmail = roomEmail;
        this.meetingSubDeptId = meetingSubDeptId;
        this.meetingSubdept = meetingSubdept;
        this.roomAreaName = roomAreaName;
        this.roomRegion = roomRegion;
        this.isPublic = isPublic;
        this.callIp = callIp;
        this.isStart = isStart;
        this.roomType = roomType;
    }

    public MeetRoom() {
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(String roomArea) {
        this.roomArea = roomArea;
    }

    public String getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(String roomFloor) {
        this.roomFloor = roomFloor;
    }

    public String getRoomBuilding() {
        return roomBuilding;
    }

    public void setRoomBuilding(String roomBuilding) {
        this.roomBuilding = roomBuilding;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getPersonCount() {
        return personCount;
    }

    public void setPersonCount(String personCount) {
        this.personCount = personCount;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public String getManagerUid() {
        return managerUid;
    }

    public void setManagerUid(String managerUid) {
        this.managerUid = managerUid;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getIsExamine() {
        return isExamine;
    }

    public void setIsExamine(String isExamine) {
        this.isExamine = isExamine;
    }

    public String getRoomEmail() {
        return roomEmail;
    }

    public void setRoomEmail(String roomEmail) {
        this.roomEmail = roomEmail;
    }

    public String getMeetingSubDeptId() {
        return meetingSubDeptId;
    }


    public void setMeetingSubDeptId(String meetingSubDeptId) {
        this.meetingSubDeptId = meetingSubDeptId;
    }

    public String getMeetingSubdept() {
        return meetingSubdept;
    }

    public void setMeetingSubdept(String meetingSubdept) {
        this.meetingSubdept = meetingSubdept;
    }

    public String getRoomAreaName() {
        return roomAreaName;
    }

    public void setRoomAreaName(String roomAreaName) {
        this.roomAreaName = roomAreaName;
    }

    public String getRoomRegion() {
        return roomRegion;
    }

    public void setRoomRegion(String roomRegion) {
        this.roomRegion = roomRegion;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public String getCallIp() {
        return callIp;
    }

    public void setCallIp(String callIp) {
        this.callIp = callIp;
    }

    public String getIsStart() {
        return isStart;
    }

    public void setIsStart(String isStart) {
        this.isStart = isStart;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getSubOrg() {
		return subOrg;
	}

	public void setSubOrg(String subOrg) {
		this.subOrg = subOrg;
	}

	@Override
    public String toString() {
        return "MeetRoom{" +
                "roomId='" + roomId + '\'' +
                ", roomCode='" + roomCode + '\'' +
                ", areaId='" + areaId + '\'' +
                ", roomArea='" + roomArea + '\'' +
                ", roomFloor='" + roomFloor + '\'' +
                ", roomBuilding='" + roomBuilding + '\'' +
                ", roomName='" + roomName + '\'' +
                ", personCount='" + personCount + '\'' +
                ", roomStatus='" + roomStatus + '\'' +
                ", createDate='" + createDate + '\'' +
                ", createUser='" + createUser + '\'' +
                ", lastUpdateDate='" + lastUpdateDate + '\'' +
                ", lastUpdateUser='" + lastUpdateUser + '\'' +
                ", managerUid='" + managerUid + '\'' +
                ", manager='" + manager + '\'' +
                ", isExamine='" + isExamine + '\'' +
                ", roomEmail='" + roomEmail + '\'' +
                ", meetingSubDeptId='" + meetingSubDeptId + '\'' +
                ", meetingSubdept='" + meetingSubdept + '\'' +
                ", roomAreaName='" + roomAreaName + '\'' +
                ", roomRegion='" + roomRegion + '\'' +
                ", isPublic='" + isPublic + '\'' +
                ", callIp='" + callIp + '\'' +
                ", isStart='" + isStart + '\'' +
                ", roomType='" + roomType + '\'' +
                '}';
    }
}
