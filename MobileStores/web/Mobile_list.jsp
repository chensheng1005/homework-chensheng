<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>漫游手机店</title>
</head>
<body>
<nav class="navbar navbar-light bg-light">
    <form class="form-inline">
        <input id="searname" class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
        <button id="search" class="btn btn-outline-success my-2 my-sm-0" type="button">搜索</button>
    </form>
</nav>
<table id="Ubtable" class="table table-striped">
    <tr>
        <th><input type="checkbox" onclick="toggle_check_all(this)">全选</th>
        <th>编号</th>
        <th>手机名称</th>
        <th>价格</th>
        <th>图片</th>
        <th>手机内存</th>
        <th>当前状态</th>
        <th>操作</th>
    </tr>
</table>

<%@include file="from_up.jsp" %>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="/js/dialog.js"></script>
<script>
    var list = function () {
        $.ajax({
            type:"get",
            url:"Mobile_listServlet",
            success:function (data) {
                $("#Ubtable tr:gt(0)").remove();
                $.each(data,function (index,data) {
                    var tr = $("<tr/>");
                    $("<td/>").append($("<input type='checkbox'>")).appendTo(tr)
                    $("<td/>").append(data.mid).appendTo(tr)
                    $("<td/>").append(data.mname).appendTo(tr)
                    $("<td/>").append(data.mprice).appendTo(tr)
                    $("<td/>").append("<img width='30px' height='40px' src='/img/"+data.img+"'>").appendTo(tr)
                    $("<td/>").append(data.ram).appendTo(tr)
                    if(data.amount>0){
                        $("<td/>").append("<button type='button' disabled class='btn btn-success'>已上架</button>").appendTo(tr)
                    }else{
                        $("<td/>").append("<button type='button' disabled class='btn btn-secondary'>已下架</button>").appendTo(tr)
                    }
                    $("<td/>").append("<a class='update btn btn-primary' data-toggle='modal' href='#'>更新</a> <a id='" + data.id + "' class='del btn btn-primary' data-toggle='modal' href='#'>删除</a>").appendTo(tr);
                    $("#Ubtable").append(tr);
                })
            }
        })
    }

    list();

    $("#Ubtable").on("click",".update",function () {
        var mid = $(this).parent().prev().prev().prev().prev().prev().prev().html();
        $.ajax({
            type:"get",
            url:"updates",
            data:{"mid":mid},
            success:function (data) {
                $("#mid").val(data.mid)
                $("#mname").val(data.mname)
                $("#mprice").val(data.mprice)
                $("#ram").val(data.ram)
                $("#brief").val(data.brief)
            }
        })
        var d = dialog({
            title: '更新信息',
            width: '600px',
            height: '320px',
            content: $("#form"),
            okValue: '确定',
            ok: function () {
                if(confirm("你确定要更新吗？")) {
                    $.ajax({
                        type: "post",
                        url: "updates",
                        data: {
                            "mid": $("#mid").val(),
                            "mname": $("#mname").val(),
                            "mprice": $("#mprice").val(),
                            "ram": $("#ram").val(),
                            "brief": $("#brief").val()
                        },
                        success: function (data) {
                            alert(data.msg);
                            list();
                        }
                    })
                }
            },
            cancelValue: '取消',
            cancel: function () {
            }
        });
        d.show();
    })
    document.querySelector("#list").classList.add("active");
    $("#Ubtable").on("click",".del",function () {
        var mid = $(this).parent().prev().prev().prev().prev().prev().prev().html();
        if(confirm("你确定要删除吗？")){
            $.ajax( {
                type:"get",
                url:"del",
                data:{"mid":mid},
                success:function (data) {
                    alert(data.msg);
                    list();
                }
            })
        }
    })

    $("#search").click(function () {
        $.ajax({
            type:"get",
            url:"Search",
            data:{"mname":$("#searname").val()},
            success:function (data) {
                $("#Ubtable tr:gt(0)").remove();
                $.each(data,function (index,data) {
                    var tr = $("<tr/>");
                    $("<td/>").append($("<input type='checkbox'>")).appendTo(tr)
                    $("<td/>").append(data.mid).appendTo(tr)
                    $("<td/>").append(data.mname).appendTo(tr)
                    $("<td/>").append(data.mprice).appendTo(tr)
                    $("<td/>").append("<img width='30px' height='40px' src='/img/"+data.img+"'>").appendTo(tr)
                    $("<td/>").append(data.ram).appendTo(tr)
                    if(data.amount>0){
                        $("<td/>").append("<button type='button' disabled class='btn btn-success'>已上架</button>").appendTo(tr);
                    }else{
                        $("<td/>").append("<button type='button' disabled class='btn btn-secondary'>已下架</button>").appendTo(tr);
                    }
                    $("<td/>").append("<a class='update btn btn-primary' data-toggle='modal' href='#'>更新</a> <a id='" + data.id + "' class='del btn btn-primary' data-toggle='modal' href='#'>删除</a>").appendTo(tr);
                    $("#Ubtable").append(tr);
                })
            }
        })
    })
</script>
</body>
</html>
