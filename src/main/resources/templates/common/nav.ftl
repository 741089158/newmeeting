<ul>
	<li>
		<div class="navItemBox">
			<img src="${__rootpath__! }/image/menu_reserve.png"><br><span class="navItemTitle" url="${__rootpath__! }/reserve">会议预定<span/>
		</div>
	</li>
	<li>
		<div class="navItemBox">
			<img src="${__rootpath__! }/image/menu_mymeeting.png"><br><span class="navItemTitle" url="${__rootpath__! }/schedule">我的会议<span/>
		</div>
	</li>
	<li>
		<div class="navItemBox">
			<img src="${__rootpath__! }/image/menu_his.png"><br><span class="navItemTitle" url="${__rootpath__! }/history">历史会议<span/>
		</div>
	</li>
<#-- 
	<li>
		<div class="navItemBox">
			<img src="${__rootpath__! }/image/menu_room_manage.png"><br><span class="navItemTitle" url="${__rootpath__! }/roomManage">会议管理<span/>
		</div>
	</li>
 -->
 	<#if auth! == 'sysAdmin'>
	<li>
		<div class="navItemBox">
			<img src="${__rootpath__! }/image/mune_user_manage.png"><br><span class="navItemTitle" url="${__rootpath__! }/userManage">后台管理<span/>
		</div>
	</li>
	<li>
		<div class="navItemBox">
			<img src="${__rootpath__! }/image/menu_sys_manage.png"><br><span class="navItemTitle" url="${__rootpath__! }/sysManage">系统管理<span/>
		</div>
	</li>
 	</#if>
</ul>
<script type="text/javascript">
for (i in document.images) {
	document.images[i].ondragstart = false;
}
<#-- 导航跳转 -->
$(".navItemBox").click(function() {
	var $item = $(this).find(".navItemTitle");
	var url = $item.attr("url") || "#";
	window.location.href = url;
});
<#-- 初始化导航 -->
var initNav = function() {
	var url = window.location.href;
	var $nit = $('.navItemTitle[url="' + url + '"]');
	if ($nit) {
		$nit.parent().addClass("navItemSelect");
	} else {
		$(".navItemTitle").first().parent().addClass("navItemSelect");
	}
}();

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