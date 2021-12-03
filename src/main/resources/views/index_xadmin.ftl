<!doctype html>
<html  class="x-admin-sm">
<head>
	<meta charset="UTF-8">
	<title>广东财经大学图书管理系统</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="stylesheet" href="${ctx}/xadmin/font.css">
    <link rel="stylesheet" href="${ctx}/xadmin/login.css">
	  <link rel="stylesheet" href="${ctx}/xadmin/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="${ctx}/xadmin/layui.js" charset="utf-8"></script>
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <#include "common.ftl">
</head>
<body class="login-bg">
    
    <div class="login layui-anim layui-anim-up">
        <div class="message">广东财经大学图书馆</div>
        <div id="darkbannerwrap"></div>
        
        <form method="post" class="layui-form" >
            <input name="userId" placeholder="用户ID"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
            <hr class="hr20" >
        </form>
    </div>

    <script>
        $(function  () {
            layui.use('form','jquery','jquery_cookie', function(){
                var layer = layui.layer,
                $ = layui.jquery,
                $ = layui.jquery_cookie($);
                var form = layui.form;
              form.on('submit(login)', function(data){
                  if ( data.userId =="undefined" || data.userId =="" || data.userId.trim()=="") {
                      layer.msg('用户ID不能为空');
                      return false;
                  }
                  if ( data.password =="undefined" || data.password =="" || data.password.trim()=="")  {
                      layer.msg('密码不能为空');
                      return false;
                  }
                  //加载
                  var index= top.layer.msg("登录中,请稍后...",{icon:16,time:false,shade:0.8});
                  $.ajax({
                      type:"get",
                      url:ctx+"/user/login",
                      data:{
                          userId:data.userId,
                          userPassword:data.password
                      },
                      dataType:"json",
                      success:function (data) {
                          layer.close(index);
                          if(data.code==200){
                              layer.msg('登录成功',{
                                  icon: 1,
                                  time: 1500, //1.5秒关闭（如果不配置，默认是3秒）
                                  shade : [0.6 , '#000' , true],
                              },function () {
                                  var result =data.result;
                                  $.cookie("userId",result.userId);
                                  $.cookie("userName",result.userName);
                                  window.location.href=ctx+"/main";
                              });
                          }else{
                              layer.msg(data.msg);
                          }
                      }
                  });
                  return false;
              });
            });
        })
    </script>
    <!-- 底部结束 -->
    <script>
    //百度统计可去掉
    var _hmt = _hmt || [];
    (function() {
      var hm = document.createElement("script");
      hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
      var s = document.getElementsByTagName("script")[0]; 
      s.parentNode.insertBefore(hm, s);
    })();
    </script>
</body>
</html>