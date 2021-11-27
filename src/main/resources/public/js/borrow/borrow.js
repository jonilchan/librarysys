layui.use(['table', 'layer', "form"], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;
    //图书列表展示
    var tableIns = table.render({
        elem: '#userList',
        url: ctx + '/book/list',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limits: [10, 15, 20, 25],
        limit: 10,
        toolbar: "#toolbarDemo",
        id: "userListTable",
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: "isbn", title: 'ISBN', fixed: "true", width: 150},
            {field: 'bookName', title: '图书名', minWidth: 50, align: "center"},
            {field: 'categoryId', title: '类别', minWidth: 50, align: "center", templet : function(data) {// 替换数据
                    if(data.categoryId==0){
                        return "科幻";
                    }else if(data.categoryId==1){
                        return "小说";
                    }else if(data.categoryId==2){
                        return "散文";
                    }
                }
            },
            {field: 'author', title: '作者', minWidth: 100, align: 'center'},
            {field: 'publisher', title: '出版社', minWidth: 100, align: 'center'},
            {field: 'totalStock', title: '总库存', align: 'center'},
            {field: 'bookLocation', title: '馆藏地址', align: 'center', templet : function(data) {// 替换数据
                    if(data.bookLocation==0){
                        return "三水";
                    }else if(data.bookLocation==1){
                        return "广州";
                    }else if(data.bookLocation==2){
                        return "山水、广州";
                    }
                }},
            {field: 'presentStock', title: '当前库存', align: 'center'},
            {field: 'status', title: '状态', align: 'center', templet : function(data) {// 替换数据
                    if(data.status==0){
                        return "正常";
                    }else if(data.status==1){
                        return "暂停借阅";
                    }
                }},
            {field: 'enterTime', title: '入库时间', align: 'center', width: 120},
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
                isbn: $("input[name='isbn']").val(),// isbn
                bookName: $("input[name='bookName']").val(),//书名
                author: $("input[name='author']").val()    //作者
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
        if (layEvent === "borrow") {
            layer.confirm("确认借阅当前书籍?", {icon: 3, title: "书籍借阅"}, function (index) {
                $.get(ctx + "/borrow/borrow?isbn=" + obj.data.isbn, {ids: obj.data.id}, function (data) {
                    if (data.code == 200) {
                        layer.msg("书籍借阅申请成功");
                        tableIns.reload();
                    } else {
                        layer.msg(data.msg);
                    }
                })
            })
        } else if (layEvent === "book") {
            layer.confirm("确认预约当前书籍?", {icon: 3, title: "书籍预约"}, function (index) {
                $.get(ctx + "/borrow/book?isbn=" + obj.data.isbn, {ids: obj.data.id}, function (data) {
                    if (data.code == 200) {
                        layer.msg("书籍预约申请成功");
                        tableIns.reload();
                    } else {
                        layer.msg(data.msg);
                    }
                })
            })
        }
    });


    //弹出框
    function openAddOrUpdateUserDialog(id) {
        var title = "用户管理-用户添加";
        var url = ctx + "/user/addOrUpdateUserPage";
        if (id) {
            title = "用户管理-用户更新";
            url = url + "?id=" + id;
        }
        layui.layer.open({
            title: title,
            type: 2,
            area: ["700px", "500px"],
            maxmin: true,
            content: url
        })
    }
});
