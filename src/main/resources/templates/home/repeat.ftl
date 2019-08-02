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
            var tUser = row.defaultlayout;//创建人
            var optionArray = [];
           /* optionArray.push('<button onClick="openUpdateWin(\'' + tId + '\', \'' + tName + '\')" type="button" class="layui-btn layui-btn-xs layui-btn-normal">修改</button>');*/
            optionArray.push('<button onClick="doDelete(\'' + tId + '\', \'' + tName + '\', \''+ tUser + '\')" type="button" class="layui-btn layui-btn-xs layui-btn-warm">删除</button>');
            return optionArray.join("&nbsp;");
        }


        // <#-- 渲染Table -->
        var publicMeetingConfig = {
            elem: '#publicMeeting',
            method: 'post',
            url: '${__rootpath__! }/meeting/myRepeatMeeting',
            height: winH - 290,
            page: true,
            limit: 10,
            limits: [10, 20, 30],
            cols: [[
                {type: 'numbers', width: 60, align: 'center', title: '序号'}
                , {field: 'meetRoomName', width: 100, align: 'center', title: '会议室'}
                , {field: 'meetName', minWidth: 230, align: 'center', title: '会议名称'}
                , {field: 'createTime', width: 180, align: 'center', title: '创建时间'}
                , {field: 'endTime', width: 180, align: 'center', title: '结束时间'}
                , {field: 'meetTime', width: 120, align: 'center', title: '时长'}
                , {field: 'repeatType', width: 120, align: 'center', title: '重复类型'}
                , {field: 'defaultlayout', minWidth: 70, align: 'center', title: '发起人',templet: '#defaultLayout'}
                , {field: 'userId', minWidth: 70, align: 'center', title: '参会人'}
                , {field: 'id', width: 140, align: 'center', title: '操作', templet: _fromatOption}
            ]]
        }
        publicMeetingTable = table.render(publicMeetingConfig);

        // <#-- 条件查询 -->
        form.on('submit(querySubmit)', function (data) {
            var qmn = $("#queryName").val() || "";
            publicMeetingTable.reload({
                where: {
                    queryName: qmn
                },
                page: {
                    curr: 1
                }
            });
            return false;
        });
    })
    // <#-- 操作 -->
    function openUpdateWin(tId, tName,tUser) {
        alert(tId + "-" + tName);
    }

    function doDelete(tId, tName,tUser) {
        layer.confirm('确定删除[' + tName + ']会议？', {
            btn: ['删除', '取消']
        }, function () {

            //登陆人工号
            var loginUser ='${loginUserNum! }';
            // console.log(tUser+"-----"+loginUser);
            // return false;
            if (tUser != loginUser){
                layer.msg("您不是会议创建人,无法删除!");
                return false;
            }
            $.get("${__rootpath__! }/meeting/deleteRepeat?pId=" + tId, {}, function (data) {
                console.log(tId);
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
    <a href="http://portal.ymtc.com/SitePages/PersonInfo.aspx/?userLoginAccount={{d.defaultlayout}}" class="layui-table-link" target="_blank">{{ d.defaultlayout }}</a>
</script>
</body>
</html>