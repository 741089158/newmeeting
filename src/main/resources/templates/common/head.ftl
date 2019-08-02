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