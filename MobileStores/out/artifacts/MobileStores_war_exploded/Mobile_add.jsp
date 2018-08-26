<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2018/8/23
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<form id="form">
    <div class="form-group">
        <label for="mname" class="col-form-label">手机名称:</label>
        <input type="text" name="mname" class="form-control" id="mname">
    </div>
    <div class="form-group">
        <label for="mprice" class="col-form-label">价格:</label>
        <input type="text" name="mprice" class="form-control" id="mprice">
    </div>
    <div class="form-group">
        <label for="ram" class="col-form-label">内存:</label>
        <input type="text" name="ram" class="form-control" id="ram">
    </div>
    <div class="form-group">
        <label for="brief" class="col-form-label">简介:</label>
        <input type="text" name="brief" class="form-control" id="brief">
    </div>
</form>
<input id="add" class="btn btn-primary" type="button" value="添加" />

<script>
    $("body").on("click",".btn",function () {
        if(confirm('您确定要添加吗？')){
            $.ajax({
                type:"post",
                url:"add",
                data:{
                    "mname": $("#mname").val(),"mprice":$("#mprice").val(),"ram":$("#ram").val(),"brief":$("#brief").val()
                },
                success:function (data) {
                    alert(data.msg);
                }
            })
        }
    })
    document.querySelector("#add").classList.add("active");
</script>
</body>
</html>
