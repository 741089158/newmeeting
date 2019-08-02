<!DOCTYPE html>
<html>
<#include "./common/head.ftl"/>
<body>
<div class="layui-custom-container">
<#-- 头部区域 Start -->
<#include "./common/header.ftl"/>
<#-- 头部区域 End -->
<#-- 主体部分 Start -->
<div id="main" class="main">
	<div class="layui-row">
		<div class="layui-col-md1 mainNavCell">
			<#-- 导航开始 Start -->
			<#include "./common/nav.ftl"/>
			<#-- 导航开始 End -->
		</div>
		<div class="layui-col-md11 mainCentreCell">
			<#-- 内容区域 Start -->
			<div class="homeAll" style="background: #EAEAEA; padding: 15px;">
				<div calss="homeQueryPanel" style="margin-bottom: 15px;background: #FFF;padding: 25px 25px 10px 25px;">
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
							<#-- QUERY_DATE时间插件 Satrt -->
							<div class="layui-inline">
								<div class="layui-input-inline">
									<input type="text" class="layui-input" id="query_date" readonly="readonly">
								</div>
							</div>
							<#-- QUERY_DATE时间插件 End -->
							<#-- QUERY_START_TIME时间插件 Satrt -->
							<div class="layui-inline">
								<div class="layui-input-inline">
									<input type="text" class="layui-input" id="query_start_time" readonly="readonly">
								</div>
							</div>
							<#-- QUERY_START_TIME时间插件 End -->
							<#-- QUERY_END_TIME时间插件 Satrt -->
							<div class="layui-inline">
								<div class="layui-input-inline">
									<input type="text" class="layui-input" id="query_end_time" readonly="readonly">
								</div>
							</div>
							<#-- QUERY_END_TIME时间插件 End -->
							<#-- 日程按钮 Start -->
							<div class="layui-inline" style="float: right; margin-right: 100px; padding-top: 6px;">
								<a class="layui-btn layui-btn-sm layui-btn-pink" href="${__rootpath__! }/room/schedule"> <i class="layui-icon layui-icon-date"></i> 日程
								</a>
							</div>
							<#-- 日程按钮 End -->
						</div>
					</form>
					<#-- 查询表单 End -->
				</div>
				<div calss="homeTablePanel" style="background: #FFF;padding: 25px;">
					<div class="layui-tab layui-tab-brief" lay-filter="home_floor" style="margin-top: 0px">
						<ul class="layui-tab-title" id="home_floor">
							<li class="layui-this">全部</li>
						</ul>
						<div class="layui-tab-content" style="min-height: 100px;overflow-y: scroll;" id="home_tab_content_container">
							<div class="layui-tab-item layui-show" id="home_card_container_parent">
								<div class="layui-row layui-col-space20" id="home_card_container">
									<div class="layui-col-md2">
										<div class="cardBox" style="border: 1px solid #EFEFEF;">
											<div class="cardTop" style="background: #F7F7F7;padding-top: 10px;">
												<div class="numberDiv" style="height: 32px; line-height: 32px;background: url('${__rootpath__! }/image/label.png') no-repeat;">&nbsp;&nbsp;10P</div>
												<div class="imageDiv" style="text-align: center;height: 60px;">
													<img style="top: -20px;position: relative;" src="${__rootpath__! }/image/space_large_blue.png">
												</div>
											</div>
											<div class="cardBottom" style="height: 50px; line-height: 50px; color: #9F388D;text-align: center;border-top: 1px solid #EFEFEF;">2B2_08</div>
										</div>
									</div>
									<div class="layui-col-md2">
										<div class="cardBox" style="border: 1px solid #EFEFEF;">
											<div class="cardTop" style="background: #F7F7F7;padding-top: 10px;">
												<div class="numberDiv" style="height: 32px; line-height: 32px;background: url('${__rootpath__! }/image/label.png') no-repeat;">&nbsp;&nbsp;10P</div>
												<div class="imageDiv" style="text-align: center;height: 60px;">
													<img style="top: -20px;position: relative;" src="${__rootpath__! }/image/space_large_blue.png">
												</div>
											</div>
											<div class="cardBottom" style="height: 50px; line-height: 50px; color: #9F388D;text-align: center;border-top: 1px solid #EFEFEF;">2B2_08</div>
										</div>
									</div>
									<div class="layui-col-md2">
										<div class="cardBox" style="border: 1px solid #EFEFEF;">
											<div class="cardTop" style="background: #F7F7F7;padding-top: 10px;">
												<div class="numberDiv" style="height: 32px; line-height: 32px;background: url('${__rootpath__! }/image/label.png') no-repeat;">&nbsp;&nbsp;10P</div>
												<div class="imageDiv" style="text-align: center;height: 60px;">
													<img style="top: -20px;position: relative;" src="${__rootpath__! }/image/space_large_blue.png">
												</div>
											</div>
											<div class="cardBottom" style="height: 50px; line-height: 50px; color: #9F388D;text-align: center;border-top: 1px solid #EFEFEF;">2B2_08</div>
										</div>
									</div>
									<div class="layui-col-md2">
										<div class="cardBox" style="border: 1px solid #EFEFEF;">
											<div class="cardTop" style="background: #F7F7F7;padding-top: 10px;">
												<div class="numberDiv" style="height: 32px; line-height: 32px;background: url('${__rootpath__! }/image/label.png') no-repeat;">&nbsp;&nbsp;10P</div>
												<div class="imageDiv" style="text-align: center;height: 60px;">
													<img style="top: -20px;position: relative;" src="${__rootpath__! }/image/space_large_blue.png">
												</div>
											</div>
											<div class="cardBottom" style="height: 50px; line-height: 50px; color: #9F388D;text-align: center;border-top: 1px solid #EFEFEF;">2B2_08</div>
										</div>
									</div>
									<div class="layui-col-md2">
										<div class="cardBox" style="border: 1px solid #EFEFEF;">
											<div class="cardTop" style="background: #F7F7F7;padding-top: 10px;">
												<div class="numberDiv" style="height: 32px; line-height: 32px;background: url('${__rootpath__! }/image/label.png') no-repeat;">&nbsp;&nbsp;10P</div>
												<div class="imageDiv" style="text-align: center;height: 60px;">
													<img style="top: -20px;position: relative;" src="${__rootpath__! }/image/space_large_blue.png">
												</div>
											</div>
											<div class="cardBottom" style="height: 50px; line-height: 50px; color: #9F388D;text-align: center;border-top: 1px solid #EFEFEF;">2B2_08</div>
										</div>
									</div>
									<div class="layui-col-md2">
										<div class="cardBox" style="border: 1px solid #EFEFEF;">
											<div class="cardTop" style="background: #F7F7F7;padding-top: 10px;">
												<div class="numberDiv" style="height: 32px; line-height: 32px;background: url('${__rootpath__! }/image/label.png') no-repeat;">&nbsp;&nbsp;10P</div>
												<div class="imageDiv" style="text-align: center;height: 60px;">
													<img style="top: -20px;position: relative;" src="${__rootpath__! }/image/space_large_blue.png">
												</div>
											</div>
											<div class="cardBottom" style="height: 50px; line-height: 50px; color: #9F388D;text-align: center;border-top: 1px solid #EFEFEF;">2B2_08</div>
										</div>
									</div>
								</div>
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
layui.use([ 'form', 'laydate', 'element' ], function() {
	var laydate = layui.laydate;
	var form = layui.form;
	var element = layui.element;

    var currentHeight = $("#main").height();
    $("#main").height((currentHeight - 1) + 'px');
    $("#main").css('overflow', 'hidden');


    // <#-- 初始化会议室展示容器的高度 -->
	var contentHeight = function() {
		var winH = $(window).height();
		var cardH = $("#home_card_container").offset().top;
		var contentH = winH - cardH - 70;
		$("#home_tab_content_container").height(contentH); // 设置会议室展示容器的高度
		return contentH;
	}();
	// <#-- 删除时间控件秒 分钟间隔显示 -->
    function _formatMinutes(date) {
		var aa = $(".laydate-time-list li ol")[1];
		var showtime = $($(".laydate-time-list li ol")[1]).find("li");
		for (var i = 0; i < showtime.length; i++) {
			var t00 = showtime[i].innerText;
			if (t00 != "00" && t00 != "15" && t00 != "30" && t00 != "45") {
				$(showtime[i]).remove();
			}
		}
		$($(".laydate-time-list li ol")[2]).find("li").remove(); // 清空秒
	}
	
	// <#-- 获取下一个时间节点 -->
	var nextTimeStr = function() {
		var unit = 30 * 60 * 1000; // 30MIN
		var now = new Date();
		var nextTime = now.getTime() + unit;
		var next = new Date(nextTime);
		var nextHours = next.getHours();
		var nextMinutes = next.getMinutes();
		nextMinutes = nextMinutes >= 30 ? "30" : "00";
		return "" + nextHours + ":" + nextMinutes;
	}();

	// <#-- 初始化时间下拉框 -->
	laydate.render({
		elem : '#query_date',
		format : 'yyyy-MM-dd',
		value : new Date(),
        max:maxDate(),
		min:minDate(),
		btns: ['now','confirm'],
		done : function(value, date) {
			var theDay = value;
			var startStr = $("#query_start_time").val();
			var endStr = $("#query_end_time").val();
			var duration = calaDuration(startStr, endStr);
			var data = {
				"date" : theDay,
				"time" : startStr,
				"duration" : duration
			};
			judgeRoomState(data);
		}
	});
	// <#-- 初始化开始时间下拉框 -->
	laydate.render({
		elem : '#query_start_time',
		type : 'time',
		format : 'HH:mm',
		value : nextTimeStr,
		ready : _formatMinutes,
		btns: ['confirm'],
		done : function(value, date) {
			$("#query_end_time").val(nextEndStr(value)); // 刷新结束时间
			var theDay = $("#query_date").val();
			var startStr = value;
			var endStr = $("#query_end_time").val();
			var duration = calaDuration(startStr, endStr);
			var data = {
				"date" : theDay,
				"time" : startStr,
				"duration" : duration
			};
			judgeRoomState(data);
		}
	});
	// <#-- 初始化结束时间下拉框 -->
	laydate.render({
		elem : '#query_end_time',
		type : 'time',
		format : 'HH:mm',
		value : nextEndStr(nextTimeStr),
		ready : _formatMinutes,
		btns: ['confirm'],
		done : function(value, date) {
			var theDay = $("#query_date").val();
			var startStr = $("#query_start_time").val();
			var endStr = value;
			var duration = calaDuration(startStr, endStr);
			var data = {
				"date" : theDay,
				"time" : startStr,
				"duration" : duration
			};
			judgeRoomState(data);
		}
	});
	// <#-- 初始化会议地区，楼号 -->
	function _renderRoomArea() {
		$.post("${__rootpath__! }/select/roomArea", {}, function(result) {
			$(result).each(function() {
				$("#home_area").append("<option value='" + this.roomAreaName + "'>" + this.roomAreaName + "</option>");
			});
			// 渲染区域
			_renderRoomBuilding($("#home_area").val());
		}, "JSON");
	}
	// <#-- 根据地区更新楼号楼号 -->
	function _renderRoomBuilding(area) {
		$("#home_building").empty(); // 清空区域
		var param = {
			"roomareaname" : area
		};
		$.post("${__rootpath__! }/select/roomBuilding", param, function(result) {
			$(result).each(function() {
				$("#home_building").append("<option value='" + this.roomBuilding + "'>" + this.roomBuilding + "</option>");
			});
			form.render('select'); // 重新渲染
	
			// 重新渲染会议室区域
			_renderRoom($("#home_area").val(), $("#home_building").val());
		}, "JSON");
	}
	// <#-- 更新会议室 -->
	function _renderRoom(area, building) {
		var param = {
            "roomareaname" : area,
			building : building
		};
		$.post("${__rootpath__! }/room/roomData", param, function(result) {
			$("#home_floor").empty(); // 清空tab书签
			$("#home_floor").append("<li data-floor='all' class='layui-this'>全部</li>");
			$("#home_card_container_parent").siblings().remove(); // 清空其他tab面板
			$("#home_card_container").empty(); // 清空全部下面的会议室
	
			$.each(result, function(k, v) {
				$("#home_floor").append("<li data-floor='" + k + "'>" + k + "F</li>");
				var floorItem = $('<div class="layui-tab-item"></div>'); // 楼层对应面板
				var floorRow = $('<div class="layui-row layui-col-space20"></div>'); // 楼层对应ROW
				floorItem.append(floorRow);
				$("#home_tab_content_container").append(floorItem);
				$.each(v, function(i, n) {
					// <%-- 会议室卡片 重构 Start --%>
	
					var tNumber = this.personCount || 0; // 人数
					var tRoomName = this.roomName || ""; // 房间名称
					var tRoomId = this.roomId || ""; // 房间ID
					var tIsPublic = this.isPublic || 1;
					var tNumberImage = "space_small_blue";
					if (tNumber < 10) {
						tNumberImage = "space_small_blue";
					} else if (tNumber < 30) {
						tNumberImage = "space_middle_blue";
					} else {
						tNumberImage = "space_large_blue";
					}
					var cardItem = '';
					cardItem = cardItem + '<div class="layui-col-md2">';
					cardItem = cardItem + '<div class="cardBox" style="cursor: pointer;border: 1px solid #EFEFEF;" onClick="doReserve(this)">';
					cardItem = cardItem + '<div class="cardTop" style="background: #F7F7F7;padding-top: 10px;">';
					cardItem = cardItem + '<div class="numberDiv" style="height: 32px; line-height: 32px;background:url(\'${__rootpath__! }/image/label.png\') no-repeat;">';
					cardItem = cardItem + '&nbsp;&nbsp;' + tNumber + 'P</div>';
					cardItem = cardItem + '<div class="imageDiv" style="text-align: center;height: 60px;">';
					cardItem = cardItem + '<img class="personImage" style="top: -20px;position: relative;" src="${__rootpath__!}/image/' + tNumberImage + '.png">';
					if (tIsPublic == 0) {
						cardItem = cardItem + '<img style="top: -10px;left: -140px;position: relative;" src="${__rootpath__!}/image/lock.png">';
					}
					cardItem = cardItem + '</div>';
					cardItem = cardItem + '<div roomId="' + tRoomId + '" class="cardBottom" style="height: 40px; line-height: 40px; color:#9F388D;text-align: center;border-top: 1px solid #EFEFEF;background: #FFF;">' + tRoomName + '</div>';
					cardItem = cardItem + '</div>';
					cardItem = cardItem + '</div>';
					$("#home_card_container").append(cardItem);
					floorRow.append(cardItem);
					// <%-- 会议室卡片 重构 End --%>
				});
			});
			_doFirstCheck();
		}, "JSON");
	}
	
	_renderRoomArea(); // <#-- 初始化 -->
	form.on('select(home_area)', function(data) {
		_renderRoomBuilding(data.value); // 刷新楼号
	});
	form.on('select(home_building)', function(data) {
		_renderRoom($("#home_area").val(), data.value); // 刷新房间
	});
	// <#-- 初次执行冲突检查 -->
	var _doFirstCheck = function() {
		var theDay = $("#query_date").val();
		var startStr = $("#query_start_time").val();
		var endStr = $("#query_end_time").val();
		var ckListener = window.setInterval(function() {
			var theDay = $("#query_date").val();
			var startStr = $("#query_start_time").val();
			var endStr = $("#query_end_time").val();
			if (theDay && startStr && endStr) {
				var duration = calaDuration(startStr, endStr);
				var data = {
					"date" : theDay,
					"time" : startStr,
					"duration" : duration
				};
				judgeRoomState(data);
				window.clearInterval(ckListener); // 监听成功，关闭重复
			}
		}, 500);
		return "OK";
	};
	// <#-- 冲突检查 -->
	function judgeRoomState(data) {
		$.post("${__rootpath__!}/room/checkTime", data, function(resp) {
			// Step1: 重置已被预约的项
			$(".cardBox").each(function() {
				$(this).find(".cardBottom").css("color", "#9F388D");
				var $img = $(this).find(".personImage");
				var oldSrc = $img.attr("src");
				if (oldSrc.indexOf("_blue") < 0) {
					var newSrc = oldSrc.replace(".png", "_blue.png");
					$img.attr("src", newSrc);
				}
			});
			// Step2: 根据返回数据隐藏匹配的项
			$.each(resp, function() {
				var tRoom = this.meetRoomId;
				var $cb = $(".cardBottom[roomId='" + tRoom + "']");
				if ($cb) {
					$cb.css("color", "#CBC9C9");
					var $img = $cb.parent().find(".personImage");
					var oldSrc = $img.attr("src");
					var newSrc = oldSrc.replace("_blue", "");
					$img.attr("src", newSrc);
				}
			})
		}, "JSON");
	}
});
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
// <#-- 预约事件 -->
function doReserve(doc) {
	var date = $("#query_date").val();
	var time = $("#query_start_time").val();
	var meetTime = $("#query_end_time").val();
	//var duration = calaDuration(startTime, endTime);
	var roomName = $(doc).find(".cardBottom").html();
	var roomId = $(doc).find(".cardBottom").attr("roomId");
	var paramArray = [];
	paramArray.push("?roomId=" + roomId);
	paramArray.push("&date=" + date);
	paramArray.push("&time=" + time);
	paramArray.push("&meetTimes=" + meetTime);
	layer.open({
		type : 2,
		title : roomName,
		area : [ '850px', '600px' ],
		content : "${__rootpath__!}/room/openReserveWin" + paramArray.join("")
	});
}

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