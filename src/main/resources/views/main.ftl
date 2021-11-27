<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>广东财经大学图书管理系统</title>
    <#include "common.ftl">
</head>
<body class="layui-layout-body layuimini-all">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header">
        <div class="layui-logo">
            <a href="">
                <img src="images/logo.png" alt="logo">
                <h1>图书馆</h1>
            </a>
        </div>
        <a>
            <div class="layuimini-tool"><i title="展开" class="fa fa-outdent" data-side-fold="1"></i></div>
        </a>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;" data-check-screen="full"><i class="fa fa-arrows-alt"></i></a>
            </li>
            <li class="layui-nav-item layuimini-setting">
                <a href="javascript:;">${(user.userName)!"未登录"}</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx}/user/toPasswordPage" data-title="修改密码" data-icon="fa fa-gears">修改密码</a>
                    </dd>
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx}/user/toInfoPage" data-title="修改信息" data-icon="fa fa-gears">修改信息</a>
                    </dd>
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx}/index" data-title="登录"  data-icon="fa fa-gears" class="login-out">退出登录</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item layuimini-select-bgcolor mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;"></a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll layui-left-menu">
                <ul class="layui-nav layui-nav-tree layui-left-nav-tree layui-this" id="currency">
                    <li class="layui-nav-item">
                        <a href="javascript:" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-3"
                           data-tab="customer/index" target="_self"><i class="fa fa-exchange"></i><span
                                    class="layui-left-nav">客户信息管理</span></a>

                    </li>


                    <li class="layui-nav-item">
                        <a href="javascript:" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11"
                           data-tab="user/toManagePage" target="_self"><i class="fa fa-user">&nbsp;&nbsp;</i><span
                                    class="layui-left-nav">用户管理</span></a>
                    </li>

                    <li class="layui-nav-item">
                        <a href="javascript:" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11"
                           data-tab="book/index" target="_self"><i class="fa fa-user">&nbsp;&nbsp;</i><span
                                    class="layui-left-nav">图书信息</span></a>

                    </li>

                    <li class="layui-nav-item">
                        <a href="javascript:" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11"
                           data-tab="borrow/index" target="_self"><i class="fa fa-user">&nbsp;&nbsp;</i><span
                                    class="layui-left-nav">图书借阅</span></a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11"
                           data-tab="borrow/return" target="_self"><i class="fa fa-user">&nbsp;&nbsp;</i><span
                                    class="layui-left-nav">图书归还</span></a>
                    </li>

                    </li>
                    <span class="layui-nav-bar" style="top: 201px; height: 0px; opacity: 0;"></span>
                </ul>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab" lay-filter="layuiminiTab" id="top_tabs_box">
            <ul class="layui-tab-title" id="top_tabs">
                <li class="layui-this" id="layuiminiHomeTabId" lay-id="welcome"><i class="fa fa-home"></i> <span>首页</span></li>
            </ul>

            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:;"> <i class="fa fa-dot-circle-o"></i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-page-close="other"><i class="fa fa-window-close"></i> 关闭其他</a></dd>
                        <dd><a href="javascript:;" data-page-close="all"><i class="fa fa-window-close-o"></i> 关闭全部</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div id="layuiminiHomeTabIframe" class="layui-tab-item layui-show">
                </div>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript" src="${ctx}/js/main.js"></script>
</body>
</html>
