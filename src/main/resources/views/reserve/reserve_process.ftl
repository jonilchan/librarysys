<!DOCTYPE html>
<html>
<head>
    <title>预约处理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
<#--                    <input type="text" name="isbn"-->
<#--                           class="layui-input-->
<#--					searchVal" placeholder="isbn"/>-->
                    <p>ISBN：${(isbn)!"获取错误"}&nbsp;</p>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="author" class="layui-input
					searchVal" placeholder="图书ID"/>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="author" class="layui-input
					searchVal" placeholder="馆藏地址"/>
                </div>
                <a class="layui-btn search_btn" data-type="reload"><i
                            class="layui-icon">&#xe615;</i> 搜索</a>
            </div>
        </form>
    </blockquote>
    <table id="userList" class="layui-table" lay-filter="users"></table>

<#--    <script type="text/html" id="toolbarDemo">-->
<#--        <div class="layui-btn-container">-->
<#--            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">-->
<#--                <i class="layui-icon">&#xe608;</i>-->
<#--                图书入库-->
<#--            </a>-->
<#--            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">-->
<#--                <i class="layui-icon">&#xe608;</i>-->
<#--                图书出库-->
<#--            </a>-->
<#--        </div>-->
<#--    </script>-->
    <!--操作-->
    <script id="userListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="select">选择该书</a>
<#--        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>-->
    </script>
</form>
<script type="text/javascript" src="${ctx}/js/reserve/reserveStock.js"></script>

</body>
</html>