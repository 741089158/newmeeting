<!DOCTYPE html>
<html>
<#include "../common/head.ftl"/>
<link rel="stylesheet" href="${__rootpath__! }/layui/formSelects/formSelects-v4.css"></link>
</head>
<body>
<div id="formBox" style="padding: 25px;">
	<form class="layui-form layui-form-pane" action="">
		<div class="layui-form-item">
			<label class="layui-form-label">会议名称</label>
			<div class="layui-input-block">
				<input id="meetName" name="meetName" value="${tName! }" type="text" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">决策人</label>
			<div class="layui-input-block">
				<select id="decisionMaker" name="decisionMaker" xm-select="decisionMaker" xm-select-search="" xm-select-max="1" onautocomplete="false">

				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">AR</label>
			<div class="layui-input-block">
                <table lay-filter="demo" id="demo" name="demo">
                    <thead>
                    <tr>
                        <th lay-data="{field:'title', width:175, edit: 'text'}">标题</th>
                        <th lay-data="{field:'owner', width:175, edit: 'text'}">owner</th>
                        <th lay-data="{field:'time',  Width: 160, edit: 'text'}">时间</th>
                        <th lay-data="{field:'remark',Width: 160, edit: 'text'}">备注</th>
                    </tr>
                    </thead>
                    <tbody id="ar">
                    <tr style="height: 20px">
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
                <button type="button" class="layui-btn layui-btn-normal" id="btn">添加行</button>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">决策</label>
			<div class="layui-input-block">
                <textarea placeholder="请输入内容" class="layui-textarea" name="desc"></textarea>
			</div>
		</div>
        <div class="layui-form-item">
            <button type="button" class="layui-btn layui-btn-normal" id="testList">选择文件</button>
            <div class="layui-upload-list">
                <table class="layui-table">
                    <thead>
                    <tr><th>文件名</th>
                        <th>大小</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr></thead>
                    <tbody id="demoList"></tbody>
                </table>
            </div>
            <button type="button" class="layui-btn" id="testListAction">开始上传</button>
        </div>

		<div class="layui-form-item" style="text-align: center;padding-top: 20px;">
			<button id="formSubmit" class="layui-btn" lay-submit lay-filter="formSubmit">提交</button>
			<button type="button" class="layui-btn layui-btn-primary" onClick="_closeMe()">取消</button>
		</div>
	</form>
</div>
<script type="text/javascript">
layui.use([ 'table', 'form','upload' ], function() {
    var form = layui.form,upload = layui.upload,table = layui.table;
    function _init() {
        table.init('demo', {
            page: false //开启分页
            ,height:'auto'
        });
    }
    var param = {};
    //监听单元格编辑
    table.on('edit(demo)', function(obj){
         //console.log(obj);
        var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
        console.log(data);
       //layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);
    });

    _init();
    $("#btn").click(function () {
        $("#ar").append(" <tr style=\"height: 40px\">\n" +
                "                        <td></td>\n" +
                "                        <td></td>\n" +
                "                        <td></td>\n" +
                "                        <td></td>\n" +
                "                    </tr>");
        _init();
    });

    // 提交表单
    form.on('submit(formSubmit)', function (data) {
        console.log($("#demo").data);
        console.log(data.field);
        return false;
    });

    //多文件列表示例
    var demoListView = $('#demoList')
            ,uploadListIns = upload.render({
        elem: '#testList'
        ,url: '/upload/'
        ,accept: 'file'
        ,multiple: true
        ,auto: false
        ,bindAction: '#testListAction'
        ,choose: function(obj){
            var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
            //读取本地文件
            obj.preview(function(index, file, result){
                var tr = $(['<tr id="upload-'+ index +'">'
                    ,'<td>'+ file.name +'</td>'
                    ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
                    ,'<td>等待上传</td>'
                    ,'<td>'
                    ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                    ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                    ,'</td>'
                    ,'</tr>'].join(''));

                //单个重传
                tr.find('.demo-reload').on('click', function(){
                    obj.upload(index, file);
                });

                //删除
                tr.find('.demo-delete').on('click', function(){
                    delete files[index]; //删除对应的文件
                    tr.remove();
                    uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                });

                demoListView.append(tr);
            });
        }
        ,done: function(res, index, upload){
            if(res.code == 0){ //上传成功
                var tr = demoListView.find('tr#upload-'+ index)
                        ,tds = tr.children();
                tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                tds.eq(3).html(''); //清空操作
                return delete this.files[index]; //删除文件队列已经上传成功的文件
            }
            this.error(index, upload);
        }
        ,error: function(index, upload){
            var tr = demoListView.find('tr#upload-'+ index)
                    ,tds = tr.children();
            tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
            tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
        }
    });

});
//配置 layui 第三方扩展组件存放的基础目录   查询联系人
function getSelect() {
    layui.config({
        base: '${__rootpath__! }/layui/formSelects/' //此处路径请自行处理, 可以使用绝对路径
    }).extend({
        formSelects: 'formSelects-v4'
    }).use(['form', 'layer', 'laydate', 'jquery', 'formSelects'], function () {
        var layer = layui.layer
                , form = layui.form
                , laydate = layui.laydate
                , formSelects = layui.formSelects;
        formSelects.config('decisionMaker', {
            type: 'post',
            searchUrl: "${__rootpath__! }/select/internal",
            keyName: 'name',            //自定义返回数据中name的key, 默认 name
            keyVal: 'name',    //自定义返回数据中value的key, 默认 value
            searchName: 'username',      //自定义搜索内容的key值
            searchVal: '',              //自定义搜索内容, 搜素一次后失效, 优先级高于搜索框中的值
            keySel: 'selected',         //自定义返回数据中selected的key, 默认 selected
            keyDis: 'disabled',         //自定义返回数据中disabled的key, 默认 disabled
            keyChildren: 'children',    //联动多选自定义children
            delay: 500,                 //搜索延迟时间, 默认停止输入500ms后开始搜索
            direction: 'auto',          //多选下拉方向, auto|up|down
            response: {
                statusCode: 0,          //成功状态码
                statusName: 'code',     //code key
                msgName: 'msg',         //msg key
                dataName: 'data'        //data key
            },
            error: function(id, url, searchVal, err){           //使用远程方式的error回调
            },
            beforeSuccess: function(id, url, searchVal, result){        //success之前的回调, 干嘛呢? 处理数据的, 如果后台不想修改数据, 你也不想修改源码, 那就用这种方式处理下数据结构吧
                return result;  //必须return一个结果, 这个结果要符合对应的数据结构
            },
            beforeSearch: function(id, url, searchVal){         //搜索前调用此方法, return true将触发搜索, 否则不触发
                if(!searchVal){//如果搜索内容为空,就不触发搜索
                    return false;
                }
                return true;
            },
            clearInput: false,          //当有搜索内容时, 点击选项是否清空搜索内容, 默认不清空
            success: function () {
                var array = new Array( ($("#decisionMaker").val()||"").split(','));
                var decisionMaker = array.join(',').split(',');
                formSelects.value('username', decisionMaker);
            }
        }, true);
    });
}
getSelect();

function _closeMe() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
</script>
</body>
</html>