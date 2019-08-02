<!DOCTYPE html>
<html>
<#include "../common/headschedule.ftl"/>

<body>
<div class="layui-custom-container">
<#-- 头部区域 Start -->
<#include "../common/header.ftl"/>
<#-- 头部区域 End -->
<#-- 主体部分 Start -->
<div id="main" class="main">
    <div class="layui-row">
        <div class="layui-col-md1 mainNavCell">
        <#-- 导航开始 Start -->
        <#include "../common/nav.ftl"/>
        <#-- 导航开始 End -->
        </div>
        <div class="layui-col-md11 mainCentreCell">
        <#-- 内容区域 Start -->
            <div class="homeAll" style="background: #EAEAEA; padding: 15px;">
                <div calss="ownQueryPanel"
                     style="margin-bottom: 15px;background: #FFF;padding: -25px 25px 15px 25px;">
                <#-- 查询表单 Start -->
                    <form class="layui-form" action="">
                        <div class="layui-form-item">
                        <#-- 会议名称 Start -->
                            <div class="layui-inline" style="margin-top: 10px;margin-left: 25px">
                                <div class="layui-input-inline" style="margin-right: 2px;">
                                    <input class="layui-input" id="home_date" type="text" lay-filter="home_date"
                                           lay-verify="required" style="width: 200px">
                                    </input>
                                </div>
                                <div class="layui-input-inline" style="margin-right: 2px;margin-left: 20px">
                                    <input id="queryName" name="queryName" type="text" placeholder="会议名称"
                                           autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-input-inline" style="width: 20px">
                                    <button lay-submit lay-filter="querySubmit"
                                            class="layui-btn layui-btn-primary layui-input-but">
                                        <i class="layui-icon">&#xe615;</i>
                                    </button>
                                </div>
                                <div class="layui-input-inline" style="text-align: right;width: 300px">
                                    <a class="layui-btn layui-btn-sm layui-btn-pink"
                                       data-type="getInfo" href="${__rootpath__! }/own">普通会议
                                    </a>
                                    <a class="layui-btn layui-btn-sm layui-btn-pink"
                                       data-type="getInfo" href="${__rootpath__! }/repeat">循环会议
                                    </a>
                                    <a class="layui-btn layui-btn-sm layui-btn-pink"
                                       href="${__rootpath__! }/room/userSchedule" style="text-align: right">
                                        <i class="layui-icon layui-icon-date"></i> 日程
                                    </a>
                                </div>
                            </div>
                        <#-- 会议名称 End -->
                        </div>
                    </form>
                <#-- 查询表单 End -->
                </div>
                <div>
                    <div class="calendar" id='calendar'></div>
                </div>
            </div>
        </div>
    <#-- 内容区域 End -->
    </div>
</div>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
            font-size: 14px;
        }

        #calendar {
            width: 93%;
            height: 510px;
            margin: 10px;
        }
    </style>
<#-- 主体部分 End -->
<script>
    layui.use(['form', 'laydate', 'layer'], function () {
        var laydate = layui.laydate;
        $("#home_tab_content_container").height(
                $(window).height() - 115);

        var currentHeight = $("#main").height();
        $("#main").height((currentHeight - 1) + 'px');
        $("#main").css('overflow', 'hidden');
        // <#-- 初始化时间 -->
        laydate.render({
            elem: '#home_date',
            format: 'yyyy-MM-dd',
            value: new Date(),
            max: maxDate3(),
            min: minDate(),
            btns: ['confirm'],
            done: function (value, date) {
                var date = value;
                var areaId = $("#home_area").val();
                var building = $("#home_building").val();
                var floor = $("#home_floor").val();
            }
        });
        var calendar = new FullCalendar.Calendar(
                document.getElementById('calendar'), {
                    plugins: ['interaction', 'dayGrid', 'timeGrid'],
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'timeGridDay,timeGridWeek,dayGridMonth'
                    },
                    locale: "zh",
                    slotLabelFormat:[{hour:"numeric",minute: '2-digit'}],
                    height: 550,
                    scrollTime: '08:00',
                    minTime: '01:00',
                    maxTime: '24:00',
                    //editable: true,
                    defaultView: 'timeGridWeek',
                    buttonText: {
                        today: '今天'
                    },
                    views:{
                        timeGridDay: {
                            type: 'timeGridDay',
                            duration: {
                                days: 1
                            },
                            buttonText: 'Day'
                        },
                        timeGridWeek: {
                            type: 'timeGridWeek',
                            duration: {
                                weeks: 1
                            },
                            buttonText: 'Week'
                        },
                        dayGridMonth: {
                            type: 'dayGridMonth',
                            duration: {
                                months: 1
                            },
                            buttonText: 'Month'
                        }
                    },
                    droppable: true,
                    drop: function (info) {
                        if (checkbox.checked) {
                            info.draggedEl.parentNode.removeChild(info.draggedEl);
                        }
                    },
                    events: '${__rootpath__! }/meeting/meetFullEvents',
                    eventMouseEnter: function (mouseEnterInfo) {
                        var userId = mouseEnterInfo.event._def.extendedProps.userId;
                        var title = mouseEnterInfo.event._def.title;
                        var start = getFormatDate(mouseEnterInfo.event.start);
                        var end = getFormatDate(mouseEnterInfo.event.end);
                        var content = '<div style="width: 350px;height: 40px">' + "时间 : " + start + "--" + end + '</div>' +
                                '<div style="width: 350px;height: 40px">' + "主题 : " + title + '</div>' +
                                '<div style="width: 350px;height: 40px">' + "预定人 : " + '<a style="color: #00FF00" ' +
                                'target="_blank" " href="http://portal.ymtc.com/SitePages/PersonInfo.aspx/?userLoginAccount=' + userId + '">' + userId + '</a></div>'
                        layer.tips(content, $(mouseEnterInfo.el), {
                            tips: [1, '#3595CC']
                        });
                    },
                    eventMouseLeave: function (mouseLeaveInfo) {
                        layer.close(layer.index);
                    }
                });
        /* 立即渲染日历或者调整它的大小 */
        calendar.render();
    });

    function getFormatDate(date) {
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var hour;
        var minutes;
        var currentDate;
        //currentDate = date.getFullYear() + "-" + month + "-" + strDate+ " ";
        if (date.getHours() == 0 || date.getHours() < 10) {
            hour = '0' + date.getHours();
        } else {
            hour = date.getHours();
        }
        currentDate = hour + ":";
        if (date.getMinutes() == 0 || date.getMinutes() < 10) {
            minutes = '0' + date.getMinutes();
        } else {
            minutes = date.getMinutes();
        }
        currentDate += minutes;
        return currentDate;
    }
</script>
</body>
</html>