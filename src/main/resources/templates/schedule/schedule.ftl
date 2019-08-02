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
                    <div calss="homeQueryPanel"
                         style="margin-bottom: 15px;background: #FFF;padding: 25px 15px 10px 25px;">
                    <#-- 查询表单 Start -->
                        <form class="layui-form" action="">
                            <div class="layui-form-item">
                            <#-- AREA下拉框 Start -->
                                <div class="layui-inline">
                                    <div class="layui-input-inline ">
                                        <select id="home_area" lay-filter="home_area" lay-verify="required">
                                        </select>
                                    </div>
                                </div>
                            <#-- AREA下拉框 End -->
                            <#-- BUILDING下拉框 Start -->
                                <div class="layui-inline">
                                    <div class="layui-input-inline">
                                        <select id="home_building" lay-filter="home_building" lay-verify="required">
                                        </select>
                                    </div>
                                </div>
                            <#-- BUILDING下拉框 End -->
                                <div class="layui-inline">
                                    <div class="layui-input-inline">
                                        <select id="home_floor" lay-filter="home_floor" lay-verify="required">
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-inline">
                                    <div class="layui-input-inline">
                                        <input class="layui-input" id="home_date" type="text" lay-filter="home_date">
                                        </input>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div>
                        <div class="calendar" id='calendar'></div>
                    </div>
                </div>
            </div>
        <#-- 内容区域 End -->
        </div>
    </div>
</div>
<#-- 主体部分 End -->
<script>

    layui.use(['form', 'laydate', 'layer'], function () {
        var laydate = layui.laydate;
        var form = layui.form;
        var layer = layui.layer;

        var currentHeight = $("#main").height();
        $("#main").height((currentHeight - 1) + 'px');
        $("#main").css('overflow', 'hidden');

        $("#home_tab_content_container").height(
                $(window).height() - 210);


        var calendar;
        calendar = new FullCalendar.Calendar(document.getElementById('calendar'),
                {
                    plugins: ['interaction', 'dayGrid',
                        'timeGrid', 'resourceTimeline'],
                    schedulerLicenseKey: 'CC-Attribution-NonCommercial-NoDerivatives',
                   /* /!* 设置事件,不设置默认为当前时间 *!/
                    /!* now: '2019-04-07', *!/
                    /!* 设置为中文*!/*/
                    locale: "ch",
                    slotDuration: "00:15:00",   //显示时间间隔,默认30
                    //editable: true,//是否允许事件可以被编辑
                    selectable: true,   //允许用户单击或者拖拽日历中的天和时间隙
                    aspectRatio: 2,//设置高度
                    scrollTime: new Date().Format("yyyy-MM-dd hh:mm").split(" ")[1], // 设置默认滚动到的时间点,默认是'06:00:00'(早上6点)
                    minTime: '01:00',
                    maxTime: '24:00',
                    height: 450,
                    header: {
                        left: 'today',
                        center: 'title',
                        right: 'resourceTimelineDay'
                    },
                    defaultView: 'resourceTimelineDay',
                    buttonText: {
                        today: '今天'
                    },
                    views: {
                        resourceTimelineDay: {
                            type: 'resourceTimeline',
                            duration: {
                                days: 1
                            },
                            buttonText: '天'
                        },
                        timeGridWeek: {
                            type: 'timeGridWeek',
                            duration: {
                                weeks: 1
                            },
                            buttonText: '周'
                        },
                        dayGridMonth: {
                            type: 'dayGridMonth',
                            duration: {
                                months: 1
                            },
                            buttonText: '月'
                        }
                    },
                    resourceLabelText: '会议室',
                    resourceAreaWidth: '100px',
                    method: 'post',
                    resources: '${__rootpath__! }/meeting/fullCalendar',
                    events: '${__rootpath__! }/meeting/fullEvents',
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
                            tips: [1, '#3595CC'],
                        });
                    },
                    eventMouseLeave: function (mouseLeaveInfo) {
                        layer.close(layer.index);
                    },
                    select: function (selectInfo) {
                        meeting(selectInfo);
                    }
                });
        /* 立即渲染日历或者调整它的大小 */
        calendar.render();


        /*渲染方法*/
        function renderCalendar(areaId, building, floor, date) {
            var paramData = {
                "roomareaname": areaId,
                "building": building,
                "floor": floor,
                "date": date
            };
            $.post("${__rootpath__! }/meeting/fullCalendar", paramData, function (resp) {
                calendar.destroy(); // 摧毁当前日历组件
                var calendarDoc = document.getElementById('calendar'); // 日历DOC
                calendar = new FullCalendar.Calendar(calendarDoc, {
                    plugins: ['interaction', 'dayGrid', 'timeGrid', 'resourceTimeline'],
                    schedulerLicenseKey: 'CC-Attribution-NonCommercial-NoDerivatives',
                    now: date,
                    locale: "zh",
                    slotLabelFormat:[{hour:"numeric",minute: '2-digit'}],
                    slotDuration: "00:15:00",   //显示时间间隔,默认30
                    //slotLabelFormat: '',
                    //editable: true,  //编辑
                    selectable: true,   //允许用户单击或者拖拽日历中的天和时间隙
                    slotMinutes: 15,// 两个时间之间的间隔
                    aspectRatio: 2,
                    scrollTime: new Date().Format("yyyy-MM-dd hh:mm").split(" ")[1], //起始时间 '08:00'   new Date().Format("yyyy-MM-dd hh:mm").split(" ")[1]
                    minTime: '01:00',
                    maxTime: '24:00',
                    height: 450,
                    header: {
                        /*left: 'today prev,next',*/
                        left: 'today',
                        center: 'title',
                        right: 'resourceTimelineDay'
                    },
                    defaultView: 'resourceTimelineDay',
                    buttonText: {
                        today: '今天'
                    },
                    views: {
                        resourceTimelineDay: {
                            type: 'resourceTimeline',
                            duration: {
                                days: 1
                            },
                            buttonText: '天'
                        },
                        timeGridWeek: {
                            type: 'timeGridWeek',
                            duration: {
                                weeks: 1
                            },
                            buttonText: '周'
                        },
                        dayGridMonth: {
                            type: 'dayGridMonth',
                            duration: {
                                months: 1
                            },
                            buttonText: '月'
                        }
                    },
                    resourceLabelText: '会议室',
                    resourceAreaWidth: '100px',
                    method: 'post',
                    resources: resp,
                    events: '${__rootpath__! }/meeting/fullEvents',
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
                            tips: [1, '#3595CC'],
                        });
                    },
                    eventMouseLeave: function (mouseLeaveInfo) {
                        layer.close(layer.index);
                    },
                    select: function (selectInfo) {
                        meeting(selectInfo);
                    }
                });
                calendar.render();
            });
        }

        // <#-- 初始化时间 -->
        laydate.render({
            elem: '#home_date',
            format: 'yyyy-MM-dd',
            value: new Date(),
            max: maxDate(),
            min: minDate(),
            btns: ['confirm'],
            done: function (value, date) {
                var date = value;
                var areaId = $("#home_area").val();
                var building = $("#home_building").val();
                var floor = $("#home_floor").val();
                renderCalendar(areaId, building, floor, date);
            }
        });

        //查询楼层
        function renderFloor(roomareaname, building) {
            var paramData = {
                "roomareaname": roomareaname,
                "building": building
            };
            $.post("${__rootpath__! }/select/floor", paramData, function (result) {
                $.each(JSON.parse(result), function (k, v) {
                    $("#home_floor").append("<option id='" + this.roomFloor + "' value='" + this.roomFloor + "'>" + this.roomFloor + "</option>");
                });
                form.render();
                // 渲染Floor后，刷型日历
                var areaId = $("#home_area").val();
                var building = $("#home_building").val();
                var floor = $("#home_floor").val();
                var date = $("#home_date").val();
                renderCalendar(areaId, building, floor, date);
            });
        }

        //查询建筑
        function renderBuilding(key) {
            var paramData = {
                "roomareaname": key
            };
            $("#home_building").empty();
            $("#home_floor").empty();
            $.post("${__rootpath__! }/select/roomBuilding", paramData, function (result) {
                $.each(JSON.parse(result), function (k, v) {
                    $("#home_building").append("<option id='" + this.roomBuilding + "' value='" + this.roomBuilding + "'>" + this.roomBuilding + "</option>");
                });
                form.render();
                // 渲染之后，继续渲染楼层
                var areaId = $("#home_area").val();
                var building = $("#home_building").val();
                renderFloor(areaId, building);
            });
        }

        //查询地区
        $.post("${__rootpath__! }/select/roomArea", {}, function (result) {
            $.each(JSON.parse(result), function (k, v) {
                $("#home_area").append("<option id='" + this.areaId + "' value='" + this.roomAreaName + "'>" + this.roomAreaName + "</option>");
            });
            form.render();
            renderBuilding($("#home_area").val());
        });
        //监听区域
        form.on('select(home_area)', function (data) {
            console.log(data.value);
            $("#home_building").empty();
            $("#home_floor").empty();
            renderBuilding(data.value);
        });

        //监听建筑
        form.on('select(home_building)', function (data) {
            $("#home_floor").empty();
            var areaId = $("#home_area option:selected").val();
            renderFloor(areaId, data.value);
        });
        //监听楼层
        form.on('select(home_floor)', function (data) {
            var areaId = $("#home_area option:selected").val();
            var building = $("#home_building option:selected").val();
            var date = $("#home_date").val();
            renderCalendar(areaId, building, data.value, date);
        });

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

    /*格式化时间*/
    Date.prototype.Format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }

    /*拖动时间弹出预订会议*/
    function meeting(selectInfo) {
        //console.log(selectInfo);
        // console.log("会议室-------------"+selectInfo.resource.title);
        //console.log("开始时间-------------" + selectInfo.startStr);
        // console.log("结束时间-------------"+selectInfo.endStr);
        var roomId = selectInfo.resource.id; //会议室id
        var roomName = selectInfo.resource.title;//会议室名称
        var startTime = new Date(selectInfo.startStr).Format("yyyy-MM-dd hh:mm");//开始时间
        var endTime = new Date(selectInfo.endStr).Format("yyyy-MM-dd hh:mm");    //结束时间
        var nowTime = new Date().Format("yyyy-MM-dd hh:mm");
        //console.log(startTime>nowTime);
        if (startTime < nowTime) {
            layer.msg("选择时间已过期!");
            return false;
        }
        var date = startTime.split(" ")[0];  //日期 格式  yyyy-MM-dd
        var time = startTime.split(" ")[1]; //时间  格式  HH:mm
        var meetTime = endTime.split(" ")[1]; //结束时间  格式  HH:mm
        // console.log(startTime);
        //console.log("时长---------"+(new Date(selectInfo.endStr)-new Date(selectInfo.startStr))); //毫秒值
        // console.log("roomId---------" + roomId);
        // console.log("roomName---------" + roomName);
        // console.log("date---------" + date);
        // console.log("time---------" + time);
        var paramArray = [];
        paramArray.push("?roomId=" + roomId);
        paramArray.push("&date=" + date);
        paramArray.push("&time=" + time);
        paramArray.push("&meetTimes=" + meetTime);
        layer.open({
            type: 2,
            title: roomName,
            area: ['850px', '600px'],
            content: "${__rootpath__!}/room/openReserveWin" + paramArray.join("")
        });
    }
</script>
</body>
</html>