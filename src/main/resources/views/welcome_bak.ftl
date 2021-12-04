<!DOCTYPE html>
<html>
<head>
  <#include "common.ftl">
</head>


<body class="childrenBody">

<div class="layui-box">
    <div class="layui-row layui-col-space10">
        <div class="layui-col-md12">
            <blockquote class="layui-elem-quote main_btn">
                <img src="/images/GdufeLibraryLogo.png">
                <br/><br/><br/>
                <p>姓名：${(user.userName)!"获取错误"}</p>
                <p>电话：${(user.phone)!"获取错误"}</p>
                <p>身份：${(identity)!"获取错误"}</p>
                <p>状态：${(status)!"获取错误"}</p>
                <p>罚款：${(fine)!"获取错误"}&nbsp;元</p>
            </blockquote>
        </div>
    </div>
</div>


<script>
    layui.use(['carousel', 'form'], function(){
        var carousel = layui.carousel
            ,form = layui.form;

        //常规轮播
        carousel.render({
            elem: '#test1'
            ,arrow: 'always'
        });

        //改变下时间间隔、动画类型、高度
        carousel.render({
            elem: '#test2'
            ,interval: 1800
            ,anim: 'fade'
            ,height: '120px'
        });

        //设定各种参数
        var ins3 = carousel.render({
            elem: '#test3'
        });
        //图片轮播
        carousel.render({
            elem: '#test10'
            ,width: '100%'
            ,height: '440px'
            ,interval: 3000
        });

        //事件
        carousel.on('change(test4)', function(res){
            console.log(res)
        });

        var $ = layui.$, active = {
            set: function(othis){
                var THIS = 'layui-bg-normal'
                    ,key = othis.data('key')
                    ,options = {};

                othis.css('background-color', '#5FB878').siblings().removeAttr('style');
                options[key] = othis.data('value');
                ins3.reload(options);
            }
        };

        //监听开关
        form.on('switch(autoplay)', function(){
            ins3.reload({
                autoplay: this.checked
            });
        });

        $('.demoSet').on('keyup', function(){
            var value = this.value
                ,options = {};
            if(!/^\d+$/.test(value)) return;

            options[this.name] = value;
            ins3.reload(options);
        });

        //其它示例
        $('.demoTest .layui-btn').on('click', function(){
            var othis = $(this), type = othis.data('type');
            active[type] ? active[type].call(this, othis) : '';
        });
    });
</script>
</body>
</html>