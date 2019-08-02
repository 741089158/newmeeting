$(function() {
	// 初始化导航和主体区域的行高
	var bodyHeight = $("body").height();
	var docHeight = document.documentElement.clientHeight;
	if (docHeight > bodyHeight) {
		bodyHeight = docHeight;
	}
	var headHeight = $("#header").height();
	var mainHeigth = bodyHeight - headHeight;
	$(".mainNavCell").height(mainHeigth);
	$(".mainCentreCell").height(mainHeigth);
});