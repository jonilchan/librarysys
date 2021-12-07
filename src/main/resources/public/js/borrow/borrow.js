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
            // {type: "checkbox", fixed: "left", width: 50},
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
            {field: 'bookLocation', title: '馆藏地址', align: 'center', templet : function(data) {// 替换数据
                    if(data.bookLocation==0){
                        return "三水";
                    }else if(data.bookLocation==1){
                        return "广州";
                    }else if(data.bookLocation==2){
                        return "三水、广州";
                    }
                }},
            {field: 'enterTime', title: '入库时间', align: 'center', width: 120},
            {field: 'totalStock', title: '总库存', align: 'center'},
            {field: 'presentStock', title: '当前库存', align: 'center'},
            {field: 'status', title: '状态', align: 'center', templet : function(data) {// 替换数据
                    if(data.status==0){
                        return "正常";
                    }else if(data.status==1){
                        return "暂停借阅";
                    }
                }},

            {title: '操作', minWidth: 150, templet: '#userListBar', fixed: "right", align: "center"}
        ]]
    });

    // 编辑 删除选项
    table.on('tool(users)', function (obj) {
        openBookStockInfo(obj.data);
    });

    //弹出框--库存信息
    function openBookStockInfo(data) {
        var readerId = prompt("请输入读者ID：");
        var title = "图书库存记录";
        layui.layer.open({
            title: title,
            type: 2,
            area: ["900px", "600px"],
            maxmin: true,
            content: ctx + "/borrow/toStock?isbn=" + data.isbn + "&readerId=" + readerId
        })
    }
})
