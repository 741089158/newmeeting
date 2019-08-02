<!DOCTYPE html>
<html>
<#include "../common/head.ftl"/>
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
                <div class="ownAll" style="background: #EAEAEA; padding: 15px;">
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
                                    <#-- 日程按钮 Start -->
                                        <a class="layui-btn layui-btn-sm layui-btn-pink"
                                           href="${__rootpath__! }/room/userSchedule" style="text-align: right">
                                            <i class="layui-icon layui-icon-date"></i> 日程
                                        </a>
                                    <#-- 日程按钮 End -->
                                    </div>
                                </div>
                            <#-- 会议名称 End -->

                            </div>
                        </form>
                    <#-- 查询表单 End -->
                    </div>
                    <div calss="ownTablePanel" style="background: #FFF;padding: 5px 25px 5px 25px;">
                        <table id="publicMeeting"></table>
                    </div>
                </div>
            <#-- 内容区域 End -->
            </div>
        </div>
    </div>
<#-- 主体部分 End -->
</div>
<script type="text/javascript">
    var publicMeetingTable;
    layui.use(['form', 'laydate', 'element', 'table'], function () {
        var laydate = layui.laydate;
        var form = layui.form;
        var element = layui.element;
        var table = layui.table;

        var winH = $(window).height();

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

        // <#-- 格式化操作 -->
        function _fromatOption(row) {
            var tId = row.id;
            var tName = row.meetName;
            var tUser = row.defaultLayout;//创建人
            var optionArray = [];
            optionArray.push('<button onClick="doDelete(\'' + tId + '\', \'' + tName + '\', \'' + tUser + '\')" type="button" class="layui-btn layui-btn-xs layui-btn-warm">删除</button>');
            optionArray.push('<button onClick="_meetingSummary(\'' + tId + '\', \'' + tName + '\', \''+ tUser + '\')" type="button" class="layui-btn layui-btn-xs layui-btn-normal">会议纪要</button>');
            return optionArray.join("&nbsp;");
        }

        //修改会议状态
        function _updateStatus(row) {
            var now = new Date().getTime(); // 当前时间毫秒值
            var target = new Date(row.meetDate).getTime();//会议开始时间毫秒值
            var duration = function (durationStr) {
                var hhmm = durationStr.split(":");
                return (+hhmm[0]) * (1000 * 60 * 60) + (+hhmm[1]) * (1000 * 60); // 会议预计持续时间毫秒值
            }(row.meetTime);
            if (now > (target + duration)){
                setTimeout(function () {
                    $.post("${__rootpath__! }/room/updateState", {id: row.id}, function (resp) {
                    })
                }, 1000 * 5);
            }
            return row.meetDate;
        }

        // <#-- 格式化距离时间 -->
        function _formatHowLong(row) {
            var now = new Date().getTime(); // 当前时间毫秒值
            var target = new Date(row.meetDate).getTime();//会议开始时间毫秒值
            var duration = function (durationStr) {
                var hhmm = durationStr.split(":");
                return (+hhmm[0]) * (1000 * 60 * 60) + (+hhmm[1]) * (1000 * 60); // 会议预计持续时间毫秒值
            }(row.meetTime);
            if (now < target) {
                // 会议未开始
                var howLong = "";
                var lag = Math.floor((target - now) / 1000);
                var day = Math.floor(lag / (60 * 60 * 24));
                howLong += day + '天';
                var hour = Math.floor(lag / 3600 % 24);
                howLong += hour + '小时';
                var minutes = Math.floor(lag / 60 % 60);
                howLong += minutes + '分钟';
                return howLong;
            } else if (now < target + duration) {
                return "进行中";
            } else if (now > target + duration) {
                //一个小时后将结束会议删除
                setTimeout(function () {
                    $.post("${__rootpath__! }/room/updateState", {id: row.id}, function (resp) {
                    })
                }, 1000 * 5);
                return "会议已结束"
            }
        }

        // <#-- 渲染Table -->
        var publicMeetingConfig = {
            elem: '#publicMeeting',
            method: 'post',
            url: '${__rootpath__! }/meeting/myPublicMeeting',
            height: winH - 290,
            page: true,
            limit: 10,
            limits: [10, 20, 30],
            cols: [[
                {type: 'numbers', width: 60, align: 'center', title: '序号'}
                , {field: 'meetRoomName', width: 100, align: 'center', title: '会议室'}
                , {field: 'meetName', minWidth: 230, align: 'center', title: '会议名称'}
                , {field: 'uri', minWidth: 70, align: 'center', title: '会议号'}
                , {field: 'meetDate', width: 180, align: 'center', title: '开始时间', templet:_updateStatus}
                , {field: 'meetTime', width: 120, align: 'center', title: '时长'}
                , {field: 'meetType', width: 120, align: 'center', title: '会议类型'}
                , {field: 'defaultLayout', minWidth: 70, align: 'center', title: '发起人', templet: '#defaultLayout'}
                /*, {field: 'time', width: 180, align: 'center', title: '距离开会时间', templet: _formatHowLong}*/
                , {field: 'id', width: 140, align: 'center', title: '操作', templet: _fromatOption}
            ]]
        };
        publicMeetingTable = table.render(publicMeetingConfig);

        // <#-- 条件查询 -->
        form.on('submit(querySubmit)', function (data) {
            var qmn = $("#queryName").val() || "";
            var home_date = $("#home_date").val();
            publicMeetingTable.reload({
                where: {
                    queryName: qmn,
                    date:home_date
                },
                page: {
                    curr: 1
                }
            });
            return false;
        });

    });
    // <#-- 会议纪要 -->
    function _meetingSummary(tId, tName, tUser) {
        console.log(tId + "-" + tName+ "-" + tUser);
        var paramArray = [];
        paramArray.push("?tId=" + tId);
        paramArray.push("&tName=" + tName);
        paramArray.push("&tUser=" + tUser);
        layer.open({
            type: 2,
            area: ['850px', '700px'],
            offset: 'auto',
            title: '会议纪要',
            content: "${__rootpath__! }/room/meetingSummary"+paramArray.join("")
        });
    }

    function doDelete(tId, tName, tUser) {
        layer.confirm('确定删除[' + tName + ']会议？', {
            btn: ['删除', '取消']
        }, function () {
            //登陆人工号
            var loginUser = '${loginUserNum! }';
            if (tUser != loginUser) {
                layer.msg("您不是会议创建人,无法删除!");
                return false;
            }
            //return false;
            $.get("${__rootpath__! }/meeting/delete?pId=" + tId, {}, function (data) {
               // console.log(tId);
                if (200 == data.state) {
                    layer.msg(data.msg);
                    publicMeetingTable.reload({});
                } else {
                    layer.msg(data.msg);
                }
            }, "JSON");
        });
    }
</script>
<script type="text/html" id="defaultLayout">
    <a href="http://portal.ymtc.com/SitePages/PersonInfo.aspx/?userLoginAccount={{d.defaultLayout}}"
       class="layui-table-link" target="_blank">{{ d.defaultLayout }}</a>
</script>
</body>
</html>