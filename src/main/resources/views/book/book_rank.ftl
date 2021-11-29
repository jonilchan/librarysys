<!DOCTYPE html>
<html>
<head>
    <title>用户管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form">
    <form class="layui-form">
            <div class="layui-inline">
                <div>
                    <label class="layui-form-label">排行依据</label>
                    <div class="layui-input-block" lay-size="35">
                        <select name="category"  id="level">
                            <option value="" >图书借阅量</option>
                            <option value="1">图书借阅量</option>
                            <option value="2">某作者</option>
                            <option value="3">数学</option>
                        </select>
                    </div>
                </div>
                <div>
                    <label class="layui-form-label">排序方式</label>
                    <div class="layui-input-block" lay-size="35">
                        <select name="category"  id="level">
                            <option value="" >降序</option>
                            <option value="1">降序</option>
                            <option value="2">升序</option>
                        </select>
                    </div>
                </div>
            </div>
        <a class="layui-btn search_btn" data-type="reload"><i
                    class="layui-icon">&#xe615;</i> 查询</a>
    </form>
<#--    <a class="layui-btn search_btn" data-type="reload"><i-->
<#--                class="layui-icon">&#xe615;</i> 查询</a>-->

    <table id="userList" class="layui-table" lay-filter="users"></table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                图书入库
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                图书出库
            </a>
            <a class="layui-btn layui-btn-normal stock_btn" lay-event="stockInfo">
                <i class="layui-icon">&#xe608;</i>
                库存详情
            </a>
        </div>
    </script>
    <!--操作-->
    <script id="userListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">增加库存</a>
<#--        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>-->
    </script>
</form>
<script type="text/javascript" src="${ctx}/js/book/bookRank.js"></script>

</body>
</html>