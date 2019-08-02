<div id="header" class="header">
	<div class="layui-row">
		<#-- LOG区域 Start -->
		<div class="layui-col-md10">
			<img src="${__rootpath__! }/image/header_logo.png" style="height: 99px;	">
		</div>
		<#-- LOG区域 End -->
		<#-- 右侧区域 Start -->
		<#--<div class="layui-col-md2">
			&lt;#&ndash; 时间区域 Start &ndash;&gt;
			<div id="headerDateBox" class="headerDateBox">
				<h3 id="t" style="font-family: 'KaiTi'; font-size: large;">当前时间：<span></span>-<span></span>-<span></span> <span>&lt;#&ndash;</span>:<span></span>:<span>&ndash;&gt;</span></h3>
				&lt;#&ndash;${nowTime}&ndash;&gt;
			</div>
			&lt;#&ndash; 时间区域 END &ndash;&gt;
		</div>-->
		<#-- 右侧区域 End -->
		<div class="layui-col-md2" >
			<div id="headerUserBox" class="headerUserBox">
				<#-- 登录人区域 Start -->
					<h3 style="font-family: 'KaiTi'; font-size: large;"><img src="${__rootpath__! }/image/top.jpg" class="layui-nav-img">${loginUserName! }</h3>
				<#-- 登录人区域 End -->
			</div>
		</div>
	</div>
</div>
<script>


    //var ss=document.getElementById('t').getElementsByTagName('span');
    function changetime(){
        var time=new Date();
        ss[0].innerHTML=time.getFullYear();
        ss[1].innerHTML=time.getMonth()+1;
        ss[2].innerHTML=time.getDate();
       /* if (time.getHours()<10){
            ss[3].innerHTML="0"+time.getHours();
        }else {
            ss[3].innerHTML=time.getHours();
		}
        if (time.getMinutes()<10){
            ss[4].innerHTML="0"+time.getMinutes();
        }else {
            ss[4].innerHTML=time.getMinutes();
		}

        if (time.getSeconds()<10){
            ss[5].innerHTML="0"+time.getSeconds();
        }else {
            ss[5].innerHTML=time.getSeconds();
		}*/

    }
   // changetime();
   /* setInterval(function(){
        changetime();
    },1000);*/

</script>