let currentPanel=1;

$(function() {
    currentPanel = 1;
    initialPanel1_table();
})

function choosePanel(i) {
    if(i!=currentPanel){
        document.getElementById("panel_" + i).hidden=false;
        document.getElementById("panel_"+currentPanel).hidden=true;
        currentPanel = i;
    }
}


/*初始初始化病人基本数据展示*/
function initialPanel1_table(){

        let rows =5;
        var panel_1_table = $('#panel_1_table').bootstrapTable({
            url: 'http://localhost:8080/inpatientWardAppServer/serverPatientAction_acquirePatientList',                      //请求后台的URL（*）
            method: 'post',                      //请求方式（*）
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
            pageSize: rows,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索
            height:420,
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列（选择显示的列）
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            queryParams : function (params) {
                //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                var temp = {
                    rows: params.limit,                         //页面大小
                    page: (params.offset / params.limit) + 1  //页码
                };
                return {"parameter":temp};
            },
            responseHandler: function(res) {
                return {
                    "total": 3,//总页数
                    "rows":res.patientList   //数据
                };
            },
            columns: [{
                checkbox: true,
                visible: true                  //是否显示复选框
            }, {
                field: 'name',
                title: '姓名',
                sortable: true
            }, {
                field: 'sex',
                title: '性别',
                sortable: true
            }, {
                field: 'phone',
                title: '电话号码',
                sortable: true
            },{
                field: 'address',
                title: '住址',
                sortable: true
            },{
                field: 'introduction',
                title: '简介',
                sortable: true
            }
                ],
            onLoadSuccess: function () {
            },
            onLoadError: function () {
            },
            onDblClickRow: function (row, $element) {
                var id = row.ID;
            },
        });

}