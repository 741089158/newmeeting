<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="${__rootpath__! }/js/jquery.min.js"></script>
    <script src="${__rootpath__! }/js/initialization.js"></script>
    <link rel="stylesheet" href="${__rootpath__! }/layui/css/layui.css">
    <script src="${__rootpath__! }/layui/layui.js"></script>
    <link rel="stylesheet" href="${__rootpath__! }/css/main.css">
    <title>会议管理系统</title>
    <link href='${__rootpath__! }/packages/core/main.css' rel='stylesheet'/>
    <link href='${__rootpath__! }/packages/daygrid/main.css' rel='stylesheet'/>
    <link href='${__rootpath__! }/packages/timegrid/main.css' rel='stylesheet'/>
    <link href='${__rootpath__! }/packages/timeline/main.css' rel='stylesheet'/>
    <link href='${__rootpath__! }/packages/resource-timeline/main.css' rel='stylesheet'/>
    <script src='${__rootpath__! }/packages/core/main.js'></script>
    <script src='${__rootpath__! }/packages/interaction/main.js'></script>
    <script src='${__rootpath__! }/packages/daygrid/main.js'></script>
    <script src='${__rootpath__! }/packages/timegrid/main.js'></script>
    <script src='${__rootpath__! }/packages/timeline/main.js'></script>
    <script src='${__rootpath__! }/packages/resource-common/main.js'></script>
    <script src='${__rootpath__! }/packages/resource-timeline/main.js'></script>
    <script src="${__rootpath__! }/js/jquery.min.js"></script>
    <script src="${__rootpath__! }/js/popper.min.js"></script>
    <script src="${__rootpath__! }/js/bootstrap.min.js"></script>

    <style>
        .calendar{
            background-color: #fff;
            position: absolute;
            margin: 30px 15px;
            width: 90%;
        }
    </style>
    <script>
        var maxDate3 = function () {
            var now = new Date();
            return now.getFullYear()+"-" + (now.getMonth()+4) + "-" + now.getDate();
        };
        var maxDate = function () {
            var now = new Date();
            return now.getFullYear()+"-" + (now.getMonth()+2) + "-" + now.getDate();
        };
        var minDate = function () {
            var now = new Date();
            return now.getFullYear()+"-" + (now.getMonth()+1) + "-" + now.getDate();
        };
    </script>
</head>
