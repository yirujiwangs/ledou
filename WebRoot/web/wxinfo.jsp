<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${errormsg.errmsg}</title>
    <style>
        .main {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
    </style>
</head>
<body>
<div class='main'>

    <div style="text-align: center;font-size:35px;">
        <img id="img" src="">

        <p>${errormsg.errmsg}!</p></div>

</div>
</body>
<script>
    var errormsg = "${errormsg}";
    var showImg = document.getElementById("img");
    if (${errormsg.errcode} == "1"){
        showImg.src = "/admin/web/images/success.jpg";
    }else
    {
        showImg.src = "/admin/web/images/fail.jpg";
    }
</script>
</html>