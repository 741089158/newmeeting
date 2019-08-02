<!DOCTYPE html>
<html>
<#include "../common/head.ftl"/>
<link rel="stylesheet" href="${__rootpath__! }/layui/formSelects/formSelects-v4.css"></link>
<body style="overflow: hidden;">
<div class="layui-collapse" lay-accordion>
    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
        <ul class="layui-tab-title">
            <li class="layui-this">会议室预订</li>
            <li>本会议室相关会议</li>
        </ul>
        <div class="layui-tab-content" style="height: 100px;">
        <#--会议室预订-->
            <div class="layui-tab-item layui-show" id="meet">
                <div id="formBox">
                    <form class="layui-form layui-form-pane" action="">
                        <div class="layui-form-item">
                            <label class="layui-form-label">会议名称</label>
                            <div class="layui-input-block">
                                <input type="hidden" name="meetRoomId" id="meetRoomId"  value="${roomId! }" />
                                <input type="text" name="meetName" required lay-verify="required" value="${meetName! }" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    <#--隐藏项-->
                        <div class="layui-form-item" style="display:none;">
                            <div class="layui-inline">
                                <div class="layui-input-block">
                                    <input type="text" class="layui-input" disabled="disabled"
                                           name="meetRoomName"
                                           value="${meetRoom.roomName! }"/>
                                </div>
                            </div>
                            <div class="layui-inline" style="display:none;">
                            <#--会议室类型-->
                                <div class="layui-input-block">
                                    <input type="text" name="meetType" class="layui-input" disabled="disabled"
                                           value="${meetRoom.roomType! }"/>
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">会议时间</label>
                                <div class="layui-input-block">
                                    <input id="home_date" name="date" type="text" class="layui-input" autocomplete="off" lay-verify="required"/>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <input id="home_time" name="time" type="text" class="layui-input" autocomplete="off" lay-verify="required"/>
                            </div>
                            <div class="layui-inline">
                                <input type="hidden" class="layui-input" name="meetTime" id="duration"/>
                                <input id="home_duration" name="home_duration" value="${meetTimes! }" type="text" class="layui-input" autocomplete="off" lay-verify="required"/>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">参会人</label>
                            <div class="layui-input-block">
                                <select name="userId" id="userId"  xm-select="userId" xm-select-search="" onautocomplete="false">
                                </select>
                               <#-- <select  name="userId" xm-select="example4" xm-select-search="" xm-select-max="20" xm-select-show-count="10">
							    <#list usersAndOrgs as uao>
                                    <option value="${uao.id! }">${uao.title! }</option>
                                </#list>
                                </select>-->
                            </div>
                        </div>
                        <#--<div class="layui-form-item">
                            <label class="layui-form-label">参会人(可选)</label>
                            <div class="layui-input-block">
                                <select  name="userId2" xm-select="example5" xm-select-search="">
							    <#list usersAndOrgs as uao>
                                    <option value="${uao.id! }">${uao.title! }</option>
                                </#list>
                                </select>
                            </div>
                        </div>-->
                        <div class="layui-form-item">
                            <label class="layui-form-label">会议类型</label>
                            <div class="layui-input-block">
                                <input type="radio" name="meetLaber" value="普通会议" title="普通会议" checked="checked"
                                       lay-filter="meetLaber"/>
                                <input type="radio" name="meetLaber" value="视频会议" title="视频会议"
                                       lay-filter="meetLaber"/>
                            </div>
                        </div>
                        <div class="layui-form-item video-item" style="display: none;">
                            <label class="layui-form-label">会议密码<span style="color: red;">*</span></label>
                            <div class="layui-input-block">
                                <input id="password" name="password" type="text" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item local-item">
                            <label class="layui-form-label">重复周期</label>
                            <div class="layui-input-block">
                                <input type="radio" name="day" value="no" title="无重复" lay-filter="day" checked="checked"/>
                                <input type="radio" name="day" value="everydays" title="每日" lay-filter="day"/>
                                <input type="radio" name="day" value="everyworks" title="工作日" lay-filter="day"/>
                                <input type="radio" name="day" value="everyweeks" title="每周" lay-filter="day"/>
                                <input type="radio" name="day" value="everymonths" title="每月" lay-filter="day"/>
                            </div>
                        </div>
                        <div class="layui-form-item local-item" id="cycleTime" style="display: none;">
                            <label class="layui-form-label">起始日期</label>
                            <div class="layui-input-inline">
                                <input id="createTime" name="createTime" type="text" class="layui-input" readonly="readonly">
                            </div>
                            <label class="layui-form-label">截止日期<span style="color: red;">*</span></label>
                            <div class="layui-input-inline">
                                <input id="endTime" name="endTime" type="text" class="layui-input" readonly="readonly">
                            </div>
                        </div>
                        <div class="layui-form-item local-item" id="weeks_extra" style="display: none;">
                            <label class="layui-form-label">重复日期</label>
                            <div class="layui-input-block">
                                <input type="hidden" name="week" id="week">
                                <input type="checkbox" name="weeks" title="周一" value="周一" lay-filter="weeks">
                                <input type="checkbox" name="weeks" title="周二" value="周二" lay-filter="weeks">
                                <input type="checkbox" name="weeks" title="周三" value="周三" lay-filter="weeks">
                                <input type="checkbox" name="weeks" title="周四" value="周四" lay-filter="weeks">
                                <input type="checkbox" name="weeks" title="周五" value="周五" lay-filter="weeks">
                                <input type="checkbox" name="weeks" title="周六" value="周六" lay-filter="weeks">
                                <input type="checkbox" name="weeks" title="周日" value="周日" lay-filter="weeks">
                            </div>
                        </div>
                        <div class="layui-form-item local-item" id="months_extra" style="display: none;">
                            <label class="layui-form-label">重复日期</label>
                            <div class="layui-input-block">
                                <input type="hidden" name="months" id="months">
							<#list 1..31 as i>
								<input type="checkbox" name="selectDay" title="${i?string("00") }号" value="${i }" lay-filter="selectDay">
                            </#list>
                            </div>
                        </div>
                        <div class="layui-form-item" style="text-align: center;padding-top: 20px;">
                            <button class="layui-btn" lay-submit lay-filter="formSubmit" id="submit">立即提交</button>
                        </div>
                    </form>
                </div>
            </div>
        <#--会议展示-->
            <div class="layui-tab-item" id="meeting">
                <table id="meetingTable"></table>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript">
layui.use([ 'element', 'table', 'form', 'laydate' ], function() {
	var element = layui.element;
	var table = layui.table;
	var form = layui.form;
	var laydate = layui.laydate;

	var winH = $(window).height();
	$("#formBox").height(winH - 108);
	$("#formBox").css("overflow", "auto");
    function _meetingTable(roomId,date) {
        table.destroy;
        // <#-- 渲染Table -->
        table.render({
            elem : '#meetingTable',
            url : '${__rootpath__! }/meeting/meetingData?roomId='+roomId+'&date='+date,
            method:'post',
            height : winH - 128,
            cols : [ [
                {type:'numbers', width: 60, align: 'center', title: '序号'}

                ,{field:'meetDate', width: 233, align: 'center', title: '会议时间',templet: function (e) {
                        var time = e.meetDate.replace(new RegExp("-", "gm"), "/");//开始时间
                        var meetTime = e.meetTime;//时长
                        var split = e.meetTime.split(":");
                        var second = parseInt(split[0]) * 60 * 60 * 1000 + parseInt(split[1]) * 60 * 1000;//时长 秒
                        var endTime = ((new Date(time)).getTime()) + second;//结束时间毫秒值
                        var newTime = new Date(endTime);
                        var hour = newTime.getHours();
                        var minutes = newTime.getMinutes();
                        if (hour < 10) {
                            hour = '0' + hour;  //补齐
                        }
                        if (minutes < 10) {
                            minutes = '0' + minutes;  //补齐
                        }
                        return time + "-" + hour + ":" + minutes;
                    }}
                ,{field:'meetName', width: 320, align: 'center', title: '会议主题'}
                ,{field:'defaultLayout', width: 100, align: 'center', title: '发起人',templet: '#defaultLayout'}
                , {field: 'id', width: 100, align: 'center', title: '操作', templet: _fromatOption}
            ] ]
        });
    }
    // <#-- 格式化操作 -->
    function _fromatOption(row) {
        var tId = row.id;
        var tName = row.meetName;
        var tUser = row.defaultLayout;//创建人
        var optionArray = [];
        optionArray.push('<button onClick="highLight(\'' + tId + '\', \'' + tName + '\', \''+ tUser + '\')" type="button" class="layui-btn layui-btn-xs layui-btn-warm">HighLight</button>');
        return optionArray.join("&nbsp;");
    }
// <#-- 初始化时间 -->
    laydate.render({
        elem : '#home_date',
        format : 'yyyy-MM-dd',
        value : '${date! }',
        max:maxDate(),
        min:minDate(),
        btns: ['now','confirm'],
        done : function(value, date) {
            //console.log(value);
            var meetRoomId = $("#meetRoomId").val();
            var home_date =value;
            var home_time =  $("#home_time").val();
            var home_duration = $("#home_duration").val();
            var duration = calaDuration(home_time, home_duration);
            param={
                "meetRoomId":meetRoomId,
                "date":home_date,
                "time":home_time,
                "duration":duration
            };
            _meetingTable(meetRoomId,home_date);
        }
    });

    _meetingTable("${roomId! }","${date! }");


    // 提交表单
    form.on('submit(formSubmit)', function (data) {
        var option = $("input[name='meetLaber']:checked").val();
        var home_time = $("#home_time").val();
        var home_duration = $("#home_duration").val();
        var duration = calaDuration(home_time, home_duration);
        $("#duration").val(duration);
        data.field.meetTime=duration;
        if ($(data.elem).hasClass("layui-btn-disabled")) {
            return false; // 阻止重复提交
        }
        $(data.elem).addClass("layui-btn-disabled");
        if(option=="视频会议"){
           // console.log("预订视频会议");
            $.get("${__rootpath__! }/reserve/videoMeet",data.field,function (resp) {
                if (resp.status==200){
                    layer.msg("预定成功"+resp.data[1]);
                    setTimeout(function () {
                        window.parent.layer.closeAll();
                    },2000);
                }
                if(resp.status==10020){
                    layer.msg(resp.message);
                    $(data.elem).removeClass("layui-btn-disabled");
                }
                if (resp.status!=200&&resp.status!=10020){
                    layer.msg(resp.message);
                    $(data.elem).removeClass("layui-btn-disabled");
                }
            });
            return false;
        }

        if (option=="普通会议"){
            // 普通会议提交
            if (data.field.day == "no") {
                $.post("${__rootpath__! }/reserve/localMeeting", data.field, function (resp) {
                    layer.msg(resp.message);
                    setTimeout(function () {
                        window.parent.location.reload();
                    }, 2000)
                });
            } else {// 循环会议提交
                $.post("${__rootpath__! }/reserve/repeat", data.field, function (resp) {
                    layer.msg(resp.message);
                    setTimeout(function () {
                        window.parent.location.reload();
                    }, 2000)
                });
            }
        }
        return false;
    });


    // <#-- 初始化周期开始结束时间 -->
    laydate.render({
        elem : '#createTime',
        format : 'yyyy-MM-dd',
        value : new Date(),
        max:maxDate(),
        done : function(value, date) {
        }
    });
    laydate.render({
        elem : '#endTime',
        format : 'yyyy-MM-dd HH:mm',
        max:maxDate3(),
        min:minDate(),
        done : function(value, date) {
        }
    });


	// <#-- 初始化开始时间 -->
	laydate.render({
		elem : '#home_time',
		type : 'time',
		format : 'HH:mm',
		value : '${time! }',
		ready : _formatMinutes,
		btns: ['confirm'],
		done : function(value, date) {
            $("#home_duration").val(nextEndStr(value)); // 刷新结束时间
            var meetRoomId = $("#meetRoomId").val();
            var home_date =$("#home_date").val();
            var home_time = value;
            var home_duration = $("#home_duration").val();;
            var duration = calaDuration(home_time, home_duration);
            param={
                "meetRoomId":meetRoomId,
                "date":home_date,
                "time":home_time,
                "duration":duration
            };
            checkRoom(param);
		}
	});
	// <#-- 初始化结束时间 -->
	laydate.render({
		elem : '#home_duration',
		type : 'time',
		format : 'HH:mm',
		ready : _formatMinutes,
		btns: ['confirm'],
		done : function(value, date) {
            var meetRoomId = $("#meetRoomId").val();
            var home_date = $("#home_date").val();
            var home_time =value;
            var home_duration = $("#home_duration").val();
            var duration = calaDuration(home_time, home_duration);
            param={
                "meetRoomId":meetRoomId,
                "date":home_date,
                "time":home_time,
                "duration":duration
            };
            checkRoom(param);
		}
	});

    // <#-- 本地会议重复周期类型转换 -->
    form.on('checkbox(weeks)', function(data) {
        var week = [];
        var month = [];
        $("input[name='weeks']:checked").each(function() {
            week.push(this.value);
        });
        $("#week").val(week)
    });
    form.on('checkbox(selectDay)', function(data) {
        var week = [];
        var month = [];
        $("input[name='selectDay']:checked").each(function() {
            month.push(this.value);
        });
        $("#months").val(month)
    });

    // 重复周期类型改变事件
    form.on('radio(day)', function(data) {
        if (data.value == "everyweeks") {
            $("#weeks_extra").show();
            $("#months_extra").hide();
        } else if (data.value == "everymonths") {
            $("#weeks_extra").hide();
            $("#months_extra").show();
        } else {
            $("#weeks_extra").hide();
            $("#months_extra").hide();
        }
        if (data.value == 'no') {
            $("#endTime").removeAttr("lay-verify","required");
            $("#cycleTime").hide();
        } else {
            $("#endTime").attr("lay-verify","required");
            $("#cycleTime").show();
        }
    });

    var meetRoomId = $("#meetRoomId").val();
    var home_date = $("#home_date").val();
    var home_time = $("#home_time").val();
    var home_duration = $("#home_duration").val();
    var duration = calaDuration(home_time, home_duration);
     var param={
        "meetRoomId":meetRoomId,
        "date":home_date,
        "time":home_time,
        "duration":duration
    };
    checkRoom(param);


	form.on('radio(meetLaber)', function(data) {
		_replaceMeetingType(data.value);
	});

	var initForm = function() {
		_replaceMeetingType("普通会议");
	}();
});

/*一键highLight*/
function highLight(tId, tName,tUser) {
    layer.confirm('确定HighLight: [' + tUser + ']？', {
        btn: ['确定', '取消']
    }, function () {
        //登陆人工号
        var loginUser ='${loginUserNum! }';
        if (tUser == loginUser){
            layer.msg("您是会议创建人,无法HighLight!");
            return false;
        }
        var param = {
            "uid":loginUser,
            "mid":tId,
            "describe":"",
            "hid":tUser
        };
        $.post("${__rootpath__! }/high/highLight",param,function (resp) {
            layer.msg(resp.message);
        }, "JSON")
    });
}


//配置 layui 第三方扩展组件存放的基础目录
function getSelect() {
    layui.config({
        base: '${__rootpath__! }/layui/formSelects/' //此处路径请自行处理, 可以使用绝对路径
    }).extend({
        formSelects: 'formSelects-v4'
    }).use(['form', 'layer', 'laydate', 'jquery', 'formSelects'], function () {
        var layer = layui.layer
                , form = layui.form
                , laydate = layui.laydate
                , formSelects = layui.formSelects;
        formSelects.config('userId', {
        type: 'post',
        searchUrl: "${__rootpath__! }/select/internal",
        keyName: 'name',            //自定义返回数据中name的key, 默认 name
        keyVal: 'name',    //自定义返回数据中value的key, 默认 value
        searchName: 'username',      //自定义搜索内容的key值
        searchVal: '',              //自定义搜索内容, 搜素一次后失效, 优先级高于搜索框中的值
        keySel: 'selected',         //自定义返回数据中selected的key, 默认 selected
        keyDis: 'disabled',         //自定义返回数据中disabled的key, 默认 disabled
        keyChildren: 'children',    //联动多选自定义children
        delay: 500,                 //搜索延迟时间, 默认停止输入500ms后开始搜索
        direction: 'auto',          //多选下拉方向, auto|up|down
        response: {
            statusCode: 0,          //成功状态码
            statusName: 'code',     //code key
            msgName: 'msg',         //msg key
            dataName: 'data'        //data key
        },
        error: function(id, url, searchVal, err){           //使用远程方式的error回调
        },
        beforeSuccess: function(id, url, searchVal, result){        //success之前的回调, 干嘛呢? 处理数据的, 如果后台不想修改数据, 你也不想修改源码, 那就用这种方式处理下数据结构吧
            return result;  //必须return一个结果, 这个结果要符合对应的数据结构
        },
        beforeSearch: function(id, url, searchVal){         //搜索前调用此方法, return true将触发搜索, 否则不触发
            if(!searchVal){//如果搜索内容为空,就不触发搜索
                return false;
            }
            return true;
        },
        clearInput: false,          //当有搜索内容时, 点击选项是否清空搜索内容, 默认不清空
        success: function () {
            var array = new Array( ($("#userId").val()||"").split(','));
            var userId = array.join(',').split(',');
            formSelects.value('username', userId);
        }
    }, true);
        form.on('input(name)', function () {
            var name = $("input[name='name']").val;
        });
    });
}
getSelect();
// 冲突检查
function checkRoom(data) {
    // 预定会议冲突检查 根据地区,建筑,日期,时间,时长
    $.post("${__rootpath__! }/room/checkRoom", data, function(resp) {
        // Step1: 重置已被预约的项
        if (resp.length==0){
            $("#submit").removeClass("layui-btn-disabled");
            $("#submit").addClass("layui-btn");
            $("#meet").addClass("layui-show");
            $("#meeting").removeClass("layui-show");
        }
        // Step2: 根据返回数据隐藏匹配的项
        if (resp.length!=0){
            $("#submit").addClass("layui-btn-disabled");
            $("#meeting").addClass("layui-show");
            $("#meet").removeClass("layui-show");
        }
    });
}

// <#-- 普通视屏会议切换 -->
function _replaceMeetingType(type) {
    if (type == "普通会议") {
        $(".video-item").hide();
        $(".local-item").show();
		$("#password").removeAttr("lay-verify","required");
        $("input[name='day']").each(function() {
            if ("no" == this.value) {
                this.checked = true;
            } else {
                this.checked = false;
            }
        });
       /* form.render();*/
        $("#weeks_extra").hide();
        $("#months_extra").hide();
        $("#cycleTime").hide();
    }
    if (type == "视频会议") {
        $(".video-item").show();
        $(".local-item").hide();//password
        $("#endTime").removeAttr("lay-verify","required");
		$("#password").attr("lay-verify","required");
    }
}

// <#-- 删除时间控件秒 分钟间隔显示 -->
function _formatMinutes(date) {
    var aa = $(".laydate-time-list li ol")[1];
    var showtime = $($(".laydate-time-list li ol")[1]).find("li");
    for (var i = 0; i < showtime.length; i++) {
        var t00 = showtime[i].innerText;
        if (t00 != "00" && t00 != "15" && t00 != "30" && t00 != "45") {
            showtime[i].remove()
        }
    }
    $($(".laydate-time-list li ol")[2]).find("li").remove();  // 清空秒
}

// <#-- 计算会议持续时间 -->
var calaDuration = function(startStr, endStr) {
	var _toTime = function(str) {
		var theArray = str.split(":");
		var the = new Date();
		the.setHours(theArray[0]);
		the.setMinutes(theArray[1]);
		return the.getTime();
	}
	var startTime = _toTime(startStr);
	var endTime = _toTime(endStr);
	var diffVal = (endTime - startTime) / (60 * 1000);
	if (diffVal < 0) {
		diffVal = (24 * 60) + diffVal;
	}
	var hl = parseInt(diffVal / 60); // 小时
	var ml = parseInt(diffVal % 60); // 分钟
	hl = hl < 10 ? "0" + hl : "" + hl;
	ml = ml < 10 ? "0" + ml : "" + ml;
	return hl + ":" + ml;
}

// <#-- 获取下一个结束时间节点 -->
var nextEndStr = function(_HHmm) {
    var hoursAndMinutes = _HHmm.split(":");
    var the = new Date();
    the.setHours(hoursAndMinutes[0]);
    the.setMinutes(hoursAndMinutes[1]);
    var unit = 30 * 60 * 1000; // 30MIN
    var nextTime = the.getTime() + unit;
    var next = new Date(nextTime);
    var nextHours = next.getHours();
    var nextMinutes = next.getMinutes();
    nextMinutes = nextMinutes < 10 ? "0" + nextMinutes : "" + nextMinutes;
    return "" + nextHours + ":" + nextMinutes; // End
};


</script>
<script type="text/html" id="defaultLayout">
    <a href="http://portal.ymtc.com/SitePages/PersonInfo.aspx/?userLoginAccount={{d.defaultLayout}}" class="layui-table-link" target="_blank">{{ d.defaultLayout }}</a>
</script>
<style type="text/css">
	.layui-laydate-content>.layui-laydate-list {
		padding-bottom: 0px;
		overflow: hidden;
	}

	.layui-laydate-content>.layui-laydate-list>li {
		width: 50%
	}

	.merge-box .scrollbox .merge-list {
		padding-bottom: 5px;
	}
</style>
</body>
</html>