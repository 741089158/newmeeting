<!DOCTYPE html>
<html>
<#include "../common/head.ftl"/>

<body>
<div id="formBox" style="padding: 25px;">
	<form class="layui-form layui-form-pane" action="">
	<input type="hidden" name="roomId" value="${room.roomId! }">
		<div class="layui-form-item">
			<label class="layui-form-label">会议室名称</label>
			<div class="layui-input-block">
				<input id="roomName" name="roomName" type="text" autocomplete="off" class="layui-input" value="${room.roomName! }">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">所属地区</label>
			<div class="layui-input-block">
				<select id="roomAddr1" name="roomAddr1" lay-filter="roomAddr1"></select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">所属区域</label>
			<div class="layui-input-block">
				<select id="roomAddr2" name="roomAddr2"></select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">子区域</label>
			<div class="layui-input-block">
				<input id="roomAddr3" name="roomAddr3" type="text" autocomplete="off" class="layui-input"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">楼&nbsp;&nbsp;层</label>
			<div class="layui-input-block">
				<select id="roomAddr4" name="roomAddr4">
					<#list 1..9 as i>
						<#if room.roomFloor! == i?c>
						<option value="${i! }" selected="selected	">${i! }楼</option>
						<#else>
						<option value="${i! }">${i! }楼</option>
						</#if> 
					</#list>
				</select>	
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">容纳人数</label>
			<div class="layui-input-block">
				<input id="volume" name="volume" type="text" autocomplete="off" class="layui-input" value="${room.personCount! }"/>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">正常</label>
			<div class="layui-input-inline">
				<input name="isNormal" type="checkbox" lay-skin="switch" <#if room.isExamine! == '1'>checked</#if>>
			</div>
			<label class="layui-form-label">是否公开</label>
			<div class="layui-input-inline">
				<input name="isPublic" type="checkbox" lay-skin="switch" <#if room.isPublic! == '1'>checked</#if>>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">管理员</label>
			<div class="layui-input-block">
				<select id="roomAdmin" name="roomAdmin" lay-search></select>	
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">所属组织</label>
			<div class="layui-input-block">
				<select id="subOrg" name="subOrg" lay-search></select>	
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">会议室资源</label>
			<div class="layui-input-block">
				<input type="checkbox" name="roomResources" value="0" title="电话">
				<input type="checkbox" name="roomResources" value="1" title="视频会议">
				<input type="checkbox" name="roomResources" value="2" title="投影仪">
				<input type="checkbox" name="roomResources" value="3" title="摄像头">
			</div>
		</div>
		<div class="layui-form-item" style="text-align: center;padding-top: 20px;">
			<button id="formSubmit" class="layui-btn" lay-submit lay-filter="formSubmit">提交</button>
			<button type="button" class="layui-btn layui-btn-primary" onClick="_closeMe()">取消</button>
		</div>
	</form>
</div>
<script type="text/javascript">
layui.use([ 'element', 'form' ], function() {
	var element = layui.element;
	var form = layui.form;
	function _renderAddr2(area) {
		$.post("${__rootpath__! }/select/roomBuilding2?area=" + area, {}, function(result) {
			$("#roomAddr2").empty(); // 清空区域
			$(result).each(function() {
				if ("${room.roomBuilding! }" == this.roomBuilding) {
					$("#roomAddr2").append("<option value='" + this.areaId + "' selected='selected'>" + this.roomBuilding + "</option>");
				} else {
					$("#roomAddr2").append("<option value='" + this.areaId + "'>" + this.roomBuilding + "</option>");
				}
			});
			form.render('select');
		}, "JSON");
	}
	function _renderAddr1() {
		$.post("${__rootpath__! }/select/distinctRoomArea", {}, function(result) {
			$("#roomAddr1").empty(); 
			$(result).each(function() {
				if ("${room.roomAreaName! }" == this.id) {
					$("#roomAddr1").append("<option value='" + this.id + "' selected='selected'>" + this.title + "</option>");
				} else {
					$("#roomAddr1").append("<option value='" + this.id + "'>" + this.title + "</option>");
				}
			});
			// 渲染区域
			_renderAddr2($("#roomAddr1").val());
		}, "JSON");
	}
	_renderAddr1(); // <#-- 初始化下拉框 -->
	form.on('select(roomAddr1)', function(data) {
		_renderAddr2(data.value);
	});
	function _renderRoomAdmin() {
		$.post("${__rootpath__! }/select/person", {}, function(result) {
			$("#roomAdmin").empty();
            $("#roomAdmin").append("<option value=''>请选择</option>");
			$(result).each(function() {
				if ("${room.managerUid! }" == this.id) {
					$("#roomAdmin").append("<option value='" + this.id + "' selected='selected'>" + this.title + "</option>");
				} else {
					$("#roomAdmin").append("<option value='" + this.id + "'>" + this.title + "</option>");
				}
			});
			form.render('select');
		}, "JSON");
	}
	_renderRoomAdmin(); // <#-- 初始化下拉框 -->
	function _renderOrg() {
		$.post("${__rootpath__! }/select/organization", {}, function(result) {
			$("#subOrg").empty();
            $("#subOrg").append("<option value=''>请选择</option>");
			$(result).each(function() {
				if ("${room.subOrg! }" == this.id) {
					$("#subOrg").append("<option value='" + this.id + "' selected='selected'>" + this.title + "</option>");
				} else {
					$("#subOrg").append("<option value='" + this.id + "'>" + this.title + "</option>");
				}
			});
			form.render('select');
		}, "JSON");
	}
	_renderOrg(); // <#-- 初始化下拉框 -->
	form.on('submit(formSubmit)', function(data) {
		if ($("#formSubmit").hasClass("layui-btn-disabled")) {
			return false;
		}
		$("#formSubmit").addClass("layui-btn-disabled");
		var param = data.field;
		var roomResources = [];
		$("input[name='roomResources']:checked").each(function() {
			roomResources.push($(this).val());
		});
		param.roomResources = roomResources;
		param.roomAdminName = $("#roomAdmin").find("option:selected").html();
		param.roomAddr1 = $("#roomAddr2").val();
		param.roomAddr1Name = $("#roomAddr1").val();
		param.roomAddr2 = $("#roomAddr2").find("option:selected").html();
	
		$.post("${__rootpath__! }/room/updateRoom", param, function(data) {
			if (data.state == "1") {
				layer.msg("修改成功", {
					time : 1000
				}, function() {
					_closeMe();
					parent.roomManageTable.reload({}); // <#-- 刷新列表 -->
				});
			} else {
				layer.msg("修改失败");
				$("#formSubmit").removeClass("layui-btn-disabled");
			}
		}, "JSON");
		return false;
	});
})
function _closeMe() {
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}
</script>
</body>
</html>