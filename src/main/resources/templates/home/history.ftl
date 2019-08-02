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
				<div calss="historyTablePanel" style="background: #FFF;padding: 10px 25px 10px 25px;">
					<table id="historyMeeting"></table>
				</div>
			</div>
			<#-- 内容区域 End -->
		</div>
	</div>
</div>
<#-- 主体部分 End -->
</div>
<script type="text/javascript">
layui.use([ 'form', 'laydate', 'element', 'table' ], function() {
	var laydate = layui.laydate;
	var form = layui.form;
	var element = layui.element;
	var table = layui.table;
	
	var winH = $(window).height();

    var currentHeight = $("#main").height();
    $("#main").height((currentHeight - 1) + 'px');
    $("#main").css('overflow', 'hidden');
	
	// <#-- 渲染Table -->
	var historyMeetingConfig = {
		elem : '#historyMeeting',
        method: 'post',
		url : '${__rootpath__! }/meeting/historyMeeting',
		height : winH - 170,
		page : true,
		limit : 10,
		limits : [10, 20, 30],
		cols : [ [
			{type:'numbers', width: 60, align: 'center', title: '序号'}
            ,{field:'meetRoomName', width: 100, align: 'center', title: '会议室'}
			,{field:'meetName', minWidth: 300, align: 'center', title: '会议名称'}
            ,{field:'meetDate', width: 150, align: 'center', title: '开始时间'}
            ,{field:'meetTime', width: 120, align: 'center', title: '时长'}
			,{field:'meetType', width: 120, align: 'center', title: '会议类型'}
			,{field:'userId', width: 300, align: 'center', title: '参会人员'}
            , {field: 'defaultLayout', minWidth: 70, align: 'center', title: '发起人',templet: '#defaultLayout'}
			<#-- 
			,{field:'id', width: 200, align: 'center', title: '操作'}
			 -->
		] ]
	}
	var historyMeetingTable = table.render(historyMeetingConfig);
	
})
</script>
<script type="text/html" id="defaultLayout">
    <a href="http://portal.ymtc.com/SitePages/PersonInfo.aspx/?userLoginAccount={{d.defaultLayout}}" class="layui-table-link" target="_blank">{{ d.defaultLayout }}</a>
</script>
</body>
</html>