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
                <div class="historyAll" style="background: #EAEAEA; padding: 15px;">
                    <div calss="historyTablePanel" style="background: #FFF; padding: 10px 25px 10px 25px;">
                        <div class="layui-row layui-col-space10">
                            <div class="layui-col-md9">
                                <table id="historyMeeting" lay-filter="historyMeeting"></table>
                            </div>
                            <div class="layui-col-md3">
                                <div style="text-align: center; background: #f2f2f2; height: 40px; line-height: 40px; margin-top: 10px;">
                                    添加新管理员
                                </div>
                                <div id="formWap" style="border: 1px solid #e6e6e6; padding: 10px;">
                                    <form class="layui-form layui-form-pane" action="">
                                        <div class="layui-form-item">
                                            <label class="layui-form-label">姓名</label>
                                            <div class="layui-input-block">
                                                <select id="adminName" name="adminName" lay-filter="adminName"
                                                        lay-search>
                                                    <option value="">请选择</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="layui-form-item">
                                            <label class="layui-form-label">工号</label>
                                            <div class="layui-input-block">
                                                <input name="adminNo" type="text" class="layui-input"
                                                       readonly="readonly">
                                            </div>
                                        </div>
                                        <div class="layui-form-item">
                                            <button lay-filter="saveSubmit" lay-submit
                                                    class="layui-btn layui-btn-fluid">提&nbsp;&nbsp;&nbsp;交
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            <#-- 内容区域 End -->
            </div>
        </div>
    </div>
<#-- 主体部分 End -->
</div>
<script type="text/javascript">
    var tTable;
    layui.use(['form', 'element', 'table'], function () {
        var form = layui.form;
        var element = layui.element;
        var table = layui.table;

        var currentHeight = $("#main").height();
        $("#main").height((currentHeight - 1) + 'px');
        $("#main").css('overflow', 'hidden');

        function _renderSysAdmin() {

            $.post("${__rootpath__! }/select/person", {}, function (result) {
                $("#adminName").empty();
                $("#adminName").append("<option value=''>请选择</option>");
                $(result).each(function () {
                    $("#adminName").append("<option value='" + this.id + "'>" + this.title + "</option>");
                });
                form.render('select');
            }, "JSON");
        }

        _renderSysAdmin(); // <#-- 初始化下拉框 -->

        var winH = $(window).height();

        // <#-- 格式化操作 -->
        function _fromatOption(row) {
            var tId = row.adminNo;
            var tName = row.adminName;
            return '<button onClick="doDelete(\'' + tId + '\', \'' + tName + '\')" type="button" class="layui-btn layui-btn-xs layui-btn-warm">删除</button>';
        }

        var tableConfig = {
            elem: '#historyMeeting',
            method: 'post',
            url: '${__rootpath__! }/sys/listData',
            height: winH - 170,
            cols: [[
                {type: 'numbers', width: 60, align: 'center', title: '序号'}
                , {field: 'adminNo', width: 300, align: 'center', title: '工号'}
                , {field: 'adminName', width: 300, align: 'center', title: '姓名'}
                , {field: 'adminNo', minWidth: 200, align: 'center', title: '操作', templet: _fromatOption}
            ]]
        }
        tTable = table.render(tableConfig);

        form.on('select(adminName)', function (data) {
            var adminNo = data.value;
            $("input[name='adminNo']").val(adminNo);
        });

        form.on('submit(saveSubmit)', function (data) {
            var param = data.field;
            if (!param.adminName) {
                layer.msg("请选中需要添加的人员！");
                return false;
            }
            param.adminName = $("option[value='" + param.adminName + "']").html() || "";
            $.post("${__rootpath__! }/sys/savaAdmin", param, function (data) {
                layer.msg(data.msg);
                if (data.state == 1) {
                    tTable.reload({});
                }
            }, "JSON");
            return false;
        });

    })

    function doDelete(tId, tName) {
        layer.confirm('确定删除[' + tName + ']的管理员权限？', {
            btn: ['删除', '取消']
        }, function () {
            $.get("${__rootpath__! }/sys/deleteAdmin?pId=" + tId, {}, function (data) {
                if (1 == data.state) {
                    layer.msg(data.msg);
                    tTable.reload({});
                } else {
                    layer.msg(data.msg);
                }
            }, "JSON");
        });
    }
</script>
</body>
</html>