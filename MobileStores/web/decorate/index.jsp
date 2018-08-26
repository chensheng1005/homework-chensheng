<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2018/8/23
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<html>
<head>
    <title>Title</title>
    <%@include file="../Mobile_res.jsp" %>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/dashboard.css" rel="stylesheet">
</head>
<body>
<%@include file="../index_top.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%--菜单栏--%>
        <%@include file="/index_menu.jsp" %>
        <%--body--%>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="table-responsive">
                <decorator:body/>
            </div>
        </div>
    </div>
</div>
</body>
</html>
