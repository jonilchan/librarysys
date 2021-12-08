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
                <input type="text" class="layui-input"
                       name="isbn" id="isbn"  lay-verify="required"  placeholder="请输入ISBN">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">图书名字</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input"
                       name="bookName" id="legalPerson" lay-verify="required" placeholder="请输入图书名字">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">类别</label>
            <div class="layui-input-block">
                <select name="category"  id="level">
                    <option value="" >请选择</option>
                    <option value="1">马克思主义</option>
                    <option value="2">哲学、宗教</option>
                    <option value="3">社会科学理论</option>
                    <option value="4">政治、法律</option>
                    <option value="5">军事</option>
                    <option value="6">经济</option>
                    <option value="7">文化、科学、教育、体育</option>
                    <option value="8">语言、文字</option>
                    <option value="9">文学</option>
                    <option value="10">艺术</option>
                    <option value="11">历史、地理</option>
                    <option value="12">自然科学总论</option>
                    <option value="13">数理科学与化学</option>
                    <option value="14">天文学、地球科学</option>
                    <option value="15">生物科学</option>
                    <option value="16">医药、卫生</option>
                    <option value="17">农业科学</option>
                    <option value="18">工业技术</option>
                    <option value="19">交通运输</option>
                    <option value="20">航空、航天</option>
                    <option value="21">环境科学、安全科学</option>
                    <option value="22">综合性图书</option>
                </select>
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">作者</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input"
                       name="author" placeholder="请输入作者">
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
                       name="publisher" placeholder="请输入出版社">
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
<script type="text/javascript" src="${ctx}/js/book/add.update.js"></script>
</body>
</html>