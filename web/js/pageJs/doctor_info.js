//# sourceURL=doctor_info.js

var searchDoctorName="";

$(function () {

    /*医生数据表格的初始化*/
    tableDoctorListInit();

    /*事件初始化*/
    searchAction();

})


/*医生数据表格的初始化*/
 function tableDoctorListInit(){
     let rows =5;
     var panel_1_table = $('#table').bootstrapTable({
         url: 'serverDoctorAction_acquireDoctorListByDoctorName',                      //请求后台的URL（*）
         method: 'post',                      //请求方式（*）
         striped: true,                      //是否显示行间隔色
         toolbar: '#toolbar',
         cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
         pagination: true,                   //是否显示分页（*）
         sortable: false,                     //是否启用排序
         sortOrder: "asc",                   //排序方式
         sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
         pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
         pageSize: rows,                       //每页的记录行数（*）
         pageList: [5,10, 25, 50, 100],        //可供选择的每页的行数（*）
         search: false,                       //是否显示表格搜索
         strictSearch: true,
         showColumns: true, //是否显示所有的列（选择显示的列）
         showRefresh:false,
         showToggle:false,
         minimumCountColumns: 2,             //最少允许的列数
         clickToSelect: true,
         height:420,
         uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
         cardView: false,                    //是否显示详细视图
         detailView: false,                  //是否显示父子表
         queryParams : function (params) {
             //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
             var temp = {
                 rows: params.limit,                         //页面大小
                 page: (params.offset / params.limit) + 1, //页码
                 doctorName:searchDoctorName

             };
             return {"queryParameter":temp};
         },
         responseHandler: function(res) {
             return {
                 "total":res.size, //总页数
                 "rows":res.doctorList   //数据
             };
         },
         columns: [{
             field:'doctorId',
             title:"医生序号"                  //是否显示复选框
         }, {
             field: 'name',
             title: '姓名'
         }, {
             field: 'sex',
             title: '性别',
             formatter:function (value,row,index) {
                 if(value==1){
                     return "男"
                 }else {
                     return '女'
                 }
             }

          },{
             field:"phone",
             title:'联系电话'
         }
         ,{
             field: 'department',
             title: '科室'
         },{
             field:'introduction',
             title:'医生简介'
         },{
             field : 'operation',
             title : '操作',
             formatter : function(value, row, index) {
                 var s = '<button class="btn btn-info btn-sm edit"><span>编辑</span></button>';
                 var d = '<button class="btn btn-danger btn-sm delete"><span>删除</span></button>';
                 var fun = '';
                 return s + ' ' + d;
             },
             events : {
                 // 操作列中编辑按钮的动作,类名匹配
                 'click .edit' : function(e, value,
                                          row, index) {
                     selectID = row.patientId;
                     rowEditOperation(row);
                 },
                 'click .delete' : function(e,
                                            value, row, index) {
                     selectID = row.patientId;
                     $('#deleteWarning_modal').modal(
                         'show');
                 }
             }
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



// 搜索动作
function searchAction() {
    $('#btn_search').click(function() {
        searchDoctorName= $('#search_doctor_name').val();
        tableRefresh();
    })
}

// 表格刷新
function tableRefresh() {
    $('#table').bootstrapTable('refresh', {
        query : {}
    });
}