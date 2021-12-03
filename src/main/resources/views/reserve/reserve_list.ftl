<!DOCTYPE html>
<html>
<head>
    <title>借阅管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="isbn" class="layui-input searchVal" placeholder="isbn"/>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="readerId" class="layui-input searchVal" placeholder="读者ID"/>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="reserveId" class="layui-input searchVal" placeholder="预约号"/>
                </div>
                <div class="layui-input-inline">
                    <select name="status" class="layui-input searchVal">
                        <option value="">状态</option>
                        <option value="0">未处理</option>
                        <option value="1">已处理</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <select name="status" class="layui-input searchVal">
                        <option value="">身份</option>
                        <option value="0">学生</option>
                        <option value="1">老师</option>
                        <option value="1">图书管理员</option>
                    </select>
                </div>

                <a class="layui-btn search_btn" data-type="reload"><i
                            class="layui-icon">&#xe615;</i> 搜索</a>
            </div>
        </form>
    </blockquote>
    <table id="userList" class="layui-table" lay-filter="users"></table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
<#--            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">-->
<#--                <i class="layui-icon">&#xe608;</i>-->
<#--                添加用户-->
<#--            </a>-->
<#--            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">-->
<#--                <i class="layui-icon">&#xe608;</i>-->
<#--                删除用户-->
<#--            </a>-->
        </div>
    </script>
    <!--操作-->
    <script id="userListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="process">处理预约</a>
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="remind">提醒取书</a>
    </script>
</form>
<script type="text/javascript" src="${ctx}/js/reserve/reserve_list.js"></script>

</body>
</html>