<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
<#--    <input name="id" type="hidden" value="${(bookinfo.isbn)!}"/>-->

    <div class="layui-form-item layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">ISBN</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" readonly
                       name="isbn" id="isbn"  lay-verify="required" value="${(bookInfo.isbn)!}" placeholder="请输入ISBN">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">图书名字</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input"
                       name="bookName" id="legalPerson" lay-verify="required" value="${(bookInfo.bookName)!}"  placeholder="请输入图书名字">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">类别</label>
            <div class="layui-input-block">
                <select name="categoryId">
                    <option value="${(bookInfo.categoryId)!}" >请选择</option>
                    <option value="0" <#if bookInfo.categoryId==0>selected="selected"</#if>>计算机</option>
                    <option value="1" <#if bookInfo.categoryId==1>selected="selected"</#if>>文学</option>
                    <option value="2" <#if bookInfo.categoryId==2>selected="selected"</#if>>散文</option>
                </select>
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">作者</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input"
                       name="author" value="${(bookInfo.author)!}" placeholder="请输入作者">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-row">
<#--        <div class="layui-col-xs6">-->
<#--            <label class="layui-form-label">三水馆藏量</label>-->
<#--            <div class="layui-input-block">-->
<#--                <input type="text" class="layui-input"-->
<#--                       name="numberOfOutputs"  placeholder="没有则为空">-->
<#--            </div>-->
<#--        </div>-->
        <div class="layui-col-xs6">
            <label class="layui-form-label">出版社</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input"
                       name="publisher"  value="${(bookInfo.publisher)!}" placeholder="请输入出版社">
            </div>
        </div>

    </div>

<#--    <div class="layui-form-item layui-row">-->
<#--        <div class="layui-col-xs6">-->
<#--            <label class="layui-form-label">广州馆藏量</label>-->
<#--            <div class="layui-input-block">-->
<#--                <input type="text" class="layui-input"-->
<#--                       name="numberOfInputs"  placeholder="没有则为空">-->
<#--            </div>-->
<#--        </div>-->
<#--    </div>-->





    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="addOrUpdateBook"
                    lay-filter="addOrUpdateBook">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/book/update_bookInfo.js"></script>
</body>
</html>