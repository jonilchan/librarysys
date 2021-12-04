layui.use(['element', 'layer', 'layuimini','jquery','jquery_cookie'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        $ = layui.jquery_cookie($);

    // 菜单初始化
    $('#layuiminiHomeTabIframe').html('<iframe width="100%" height="100%" frameborder="0"  src="welcome"></iframe>')
    layuimini.initTab();

    $(".login-out").click(function () {
        layer.confirm('是否登出当前用户?', {icon: 3, title:'提示'}, function(index){
            $.removeCookie("userId",{path:"/"})
            $.removeCookie("userName",{path:"/"})
            $.removeCookie("trueName",{path:"/"})
            //页面跳转
            window.parent.location.href = ctx + "/index";
            layer.close(index);
        });
    })

    $(".loss-apply").click(function () {
        layer.confirm('是否挂失当前用户?', {icon: 3, title:'提示'}, function(index){
            $.post(ctx+"/user/lossApply", function (data) {
                if(data.code==200){
                    layer.msg("确认成功");
                    layer.close(index);
                }else{
                    layer.msg(data.msg);
                }
            })
            layer.close(index);
        });
    })
});
