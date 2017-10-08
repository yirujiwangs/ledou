<%@ page language="java" import="java.util.*"  contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<html>
<head>
    <title></title>
    <style>
        #content{
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%,-50%);
            display: none;
        }
    </style>
</head>
<body>
<div id="content">
    <div><img src="../images/warn.png" alt=""/></div>
    <div style="text-align: center"><p>${result.errmsg} </p></div>
</div>
<a href="http://lutianfei.vicp.io/ledou/web/index.html#account">test</a>

</body>
<script>

    if(${result.errcode} === 1){
        sessionStorage.userName=<c:choose><c:when test="${result.object==null}">"not login"</c:when><c:otherwise>${result.object}</c:otherwise></c:choose>;
        var show=document.getElementById("content");
        show.style.display="block";
        window.location.href = "http://lutianfei.vicp.io/ledou/web/index.html#account";
    }else{
        var show=document.getElementById("content");
        show.style.display="block";
    }
</script>
</html>
