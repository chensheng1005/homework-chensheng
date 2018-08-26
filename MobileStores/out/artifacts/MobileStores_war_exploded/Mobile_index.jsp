<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2018/8/24
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="one">

</div>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="/js/dialog.js"></script>

<script>
    var li = function () {
        $.ajax({
            type:"get",
            url:"Mobile_listServlet",
            success:function (data) {
                $.each(data,function (index,obj) {
                    var a = $("<div style='float: left'/>");
                    $("<div></div>").append($("<img src='/img/"+obj.img+"'>")).appendTo(a);
                    $("<p></p>").append(obj.mprice).appendTo(a);
                    $("<p></p>").append(obj.brief).appendTo(a);
                    $("#one").append(a);
                })


            }
        })
    }
    li();
    document.querySelector("#index").classList.add("active");
</script>
</body>
</html>
