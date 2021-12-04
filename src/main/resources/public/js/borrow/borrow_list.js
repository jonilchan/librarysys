layui.use(['table', 'layer', "form"], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    //借阅列表展示
    var tableIns = table.render({
        elem: '#userList',
        url: ctx + '/borrow/list',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        toolbar: "#toolbarDemo",
        id: "userListTable",
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: "borrowId", title: '借阅ID', fixed: "true", width: 150},
            {field: 'bookId', title: '图书ID', minWidth: 50, align: "center"},
            {field: 'readerId', title: '读者ID', minWidth: 50, align: "center"},
            {field: 'borrowTime', title: '借阅时间', minWidth: 100, align: 'center'},
            {field: 'returnTime', title: '归还时间', minWidth: 100, align: 'center'},
            {field: 'operator', title: '操作员', align: 'center'},
            {field: 'fine', title: '罚款', align: 'center'},
            {field: 'status', title: '状态', align: 'center', templet : function(data) {// 替换数据
                    if(data.status==0){
                        return "已借未还";
                    }else if(data.status==1){
                        return "已还";
                    }else if(data.status==2){
                        return "预约未拿";
                    }
                }},
            {field: 'renew', title: '是否续借', align: 'center', templet : function(data) {// 替换数据
                    if(data.renew==0){
                        return "未续借";
                    }else if(data.renew==1){
                        return "已续借";
                    }
                }},
            {title: '操作', minWidth: 150, templet: '#userListBar', fixed: "right", align: "center"}
        ]]
    });


    // 多条件搜索
    $(".search_btn").on("click", function () {
        table.reload("userListTable", {
            page: {
                curr: 1
            },
            where: {
                bookId: $("input[name='bookId']").val(),// isbn
                readerId: $("input[name='readerId']").val(),//书名
                status: $("select[name='status']").val() , //状态
                operator: $("input[name='operator']").val(),//操作员
                fine: $("select[name='fine']").val(), //罚款
            }
        })
    });


    // 头工具栏事件
    table.on('toolbar(users)', function (obj) {
        switch (obj.event) {
            case "add":
                openAddOrUpdateUserDialog();
                break;
            case "del":
                delUser(table.checkStatus(obj.config.id).data);
                break;
        }
    });


    function delUser(datas) {
        /**
         * 批量删除
         *   datas:选择的待删除记录数组
         */
        if (datas.length == 0) {
            layer.msg("请选择待删除记录!");
            return;
        }
        layer.confirm("确定删除选中的记录", {
            btn: ['确定', '取消']
        }, function (index) {
            layer.close(index);
            // ids=10&ids=20&ids=30
            var ids = "ids=";
            for (var i = 0; i < datas.length; i++) {
                if (i < datas.length - 1) {
                    ids = ids + datas[i].id + "&ids=";
                } else {
                    ids = ids + datas[i].id;
                }
            }
            $.ajax({
                type: "post",
                url: ctx + "/user/delete",
                data: ids,
                dataType: "json",
                success: function (data) {
                    if (data.code == 200) {
                        tableIns.reload();
                    } else {
                        layer.msg(data.msg);
                    }
                }
            })


        })
    }

    // 编辑 删除选项
    table.on('tool(users)', function (obj) {
        var layEvent = obj.event;
        if (layEvent === "giveback") {
            layer.confirm("确认归还当前书籍?", {icon: 3, title: "书籍归还"}, function (index) {
                $.get(ctx + "/borrow/giveback", {borrowId: obj.data.borrowId}, function (data) {
                    if (data.code == 200) {
                        layer.msg("书籍归还申请成功");
                        tableIns.reload() ;
                    } else {
                        layer.msg(data.msg);
                    }
                })
            })
        } else if (layEvent === "urgereturn") {
            layer.confirm("确认催还当前书籍?", {icon: 3, title: "书籍催还"}, function (index) {
                $.get(ctx + "/borrow/urgereturn", {borrowId: obj.data.borrowId}, function (data) {
                    if (data.code == 200) {
                        layer.msg("书籍催还申请成功");
                        tableIns.reload() ;
                    } else {
                        layer.msg(data.msg);
                    }
                })
                }
            )
        }
    });

});
