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
                <div class="roomAll" style="background: #EAEAEA; padding: 15px;">
                    <div calss="roomQueryPanel"
                         style="margin-bottom: 15px;background: #FFF;padding: 25px 25px 10px 25px;">
                    <#-- 查询表单 Start -->
                        <form class="layui-form" action="">
                            <div class="layui-form-item">
                            <#-- 会议名称 Start -->
                                <div class="layui-inline">
                                    <div class="layui-input-inline" style="margin-right: 2px;">
                                        <input id="queryName" name="queryName" type="text" placeholder="会议名称"
                                               autocomplete="off" class="layui-input">
                                    </div>
                                    <div class="layui-input-inline">
                                        <button lay-submit lay-filter="querySubmit"
                                                class="layui-btn layui-btn-primary layui-input-but">
                                            <i class="layui-icon">&#xe615;</i>
                                        </button>
                                    </div>
                                </div>
                            <#-- 会议名称 End -->
                            <#-- 添加按钮 Start -->
                                <div class="layui-inline" style="float: right; margin-right: 100px; padding-top: 6px;">
                                    <a id="saveBut" class="layui-btn layui-btn-sm layui-btn-pink">
                                        <i class="layui-icon">&#xe654;</i>添加会议室
                                    </a>
                                </div>
                            <#-- 添加按钮 End -->
                            </div>
                        </form>
                    <#-- 查询表单 End -->
                    </div>
                    <div calss="roomTablePanel" style="background: #FFF;padding: 25px 25px 5px 25px;">
                        <table id="roomManage"></table>
                    </div>
                </div>
            <#-- 内容区域 End -->
            </div>
        </div>
    </div>
<#-- 主体部分 End -->
</div>
<script type="text/javascript">
    var roomManageTable;
    layui.use(['form', 'laydate', 'element', 'table'], function () {
        var laydate = layui.laydate;
        var form = layui.form;
        var element = layui.element;
        var table = layui.table;

        var winH = $(window).height();

        var currentHeight = $("#main").height();
        $("#main").height((currentHeight - 1) + 'px');
        $("#main").css('overflow', 'hidden');


        // <#-- 渲染Table -->
        function _formatState(row) {
            return row.isStart == 1 ? "启用" : "禁用";
        }

        function _formatOption(row) {
            var tId = row.roomId;
            var tName = row.roomName;
            var optionArray = [];
            optionArray.push('<button onClick="openUpdateWin(\'' + tId + '\', \'' + tName + '\')" type="button" class="layui-btn layui-btn-xs layui-btn-normal">修改</button>');
            optionArray.push('<button onClick="doDelete(\'' + tId + '\', \'' + tName + '\')" type="button" class="layui-btn layui-btn-xs layui-btn-warm">删除</button>');
            return optionArray.join("&nbsp;");
        }

        var roomManageConfig = {
            elem: '#roomManage',
            method: "post",
            url: '${__rootpath__! }/room/roomTableData',
            height: winH - 290,
            page: true,
            limit: 10,
            limits: [10, 20, 30],
            cols: [[
                {type: 'numbers', width: 60, align: 'center', title: '序号'}
                , {field: 'roomName', minWidth: 100, align: 'center', title: '名称'}
                , {field: 'manager', width: 150, align: 'center', title: '管理人',templet:function (e) {
                        if(e.manager=='请选择'||e.manager=='null'){
                            return "";
                        }else {
                            return e.manager;
                        }
                    }}
                , {field: 'roomAreaName', width: 150, align: 'center', title: '所属地区'}
                , {field: 'roomBuilding', width: 150, align: 'center', title: '所属区域'}
                , {field: 'roomFloor', width: 150, align: 'center', title: '楼层'}
                , {field: 'personCount', width: 100, align: 'center', title: '容纳人数'}
                , {field: 'isStart', width: 100, align: 'center', title: '状态', templet: _formatState}
                , {field: 'roomId', width: 200, align: 'center', title: '操作', templet: _formatOption}
            ]]
        }
        roomManageTable = table.render(roomManageConfig);

        // <#-- 条件查询 -->
        form.on('submit(querySubmit)', function (data) {
            var qmn = $("#queryName").val() || "";
            roomManageTable.reload({
                where: {
                    queryName: qmn
                },
                page: {
                    curr: 1
                }
            });
            return false;
        });

    <#-- 打开添加窗口 -->
        $("#saveBut").click(function () {
            layer.open({
                type: 2,
                area: ['700px', '500px'],
                offset: 'auto',
                title: '添加会议室',
                content: "${__rootpath__! }/room/openCreateWin",
                end: function () {
                }
            });
        });

    })
    // <#-- 操作 -->
    function openUpdateWin(tId, tName) {
        layer.open({
            type: 2,
            area: ['700px', '500px'],
            offset: 'auto',
            title: '会议室[' + tName + ']修改',
            content: "${__rootpath__! }/room/openUpdateWin?roomId=" + tId,
            end: function () {
            }
        });
    }

    function doDelete(tId, tName) {
        layer.confirm('确定删除[' + tName + ']会议室？', {
            btn: ['删除', '取消']
        }, function () {
            $.get("${__rootpath__! }/room/delete?pId=" + tId, {}, function (data) {
                if (1 == data.state) {
                    layer.msg("删除成功");
                    roomManageTable.reload({});
                } else {
                    layer.msg("删除失败");
                }
            }, "JSON");
        });
    }
</script>
</body>
</html>