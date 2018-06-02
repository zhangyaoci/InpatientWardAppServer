//# sourceURL=patient_bp.js

var searchPatientId="";
var searchStartTime=new Date("2018-01-01");
var searchEndTime=new Date();

$(function () {

    /*初始化搜索框*/
    searchDataInit();


    /*点击事件*/
    searchAction();

});


/*搜索数据的初始化*/
function searchDataInit() {
    $('#search_start_time').datetimepicker(
        {
            format:'yyyy-mm-dd hh:ii',
            language : 'zh-CN',
            autoclose: true,
            todayBtn: true,
            pickerPosition: "bottom-left"
        }
    );

    let today= new Date();
    $("#search_end_time").val(today.toISOString().split("T")[0]+" 00:00");

    $('#search_end_time').datetimepicker(
        {
            format:'yyyy-mm-dd hh:ii',
            language : 'zh-CN',
            autoclose: true,
            todayBtn: true,
            pickerPosition: "bottom-left"
        }
    );



    // 通过ajax 获取选中的名字
    $.ajax({
        type : "post",
        url : "serverPatientAction_acquirePatientIdAndPatientName",
        dataType : "json",
        contentType : "application/json",
        data:JSON.stringify({}),
        success : function(response){
            let patientInfoList = response["patientInfoList"];
            for(let patientInfo of patientInfoList){
                $('#search_patient_name').append("<option value=" + patientInfo[0] + ">" + patientInfo[1] + "</option>");
            }

            /*默认搜索第一个病人血压值*/
          searchPatientId =  $("#search_patient_name").val();

          /*绘制图表*/
          tableBPListInit();

        },error : function(xhr, textStatus, errorThrow){
            //handleAjaxError(xhr.status);
        }
    })
}

function tableBPListInit() {
    let rows =5;
    var panel_1_table = $('#table').bootstrapTable({
        url: 'serverBPAction_acquireBPListWithPage',                      //请求后台的URL（*）
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
                patientId:searchPatientId,
                startTime:searchStartTime,
                endTime:searchEndTime
            };
            return {"queryParameter":temp};
        },
        responseHandler: function(res) {
            return {
                "total":res.size, //总页数
                "rows":res.bloodpressureList  //数据
            };
        },
        columns: [{
            field:'bpId',
            title:"血压序号"                  //是否显示复选框
        }, {
            field: 'patientName',
            title: '姓名'
        }, {
            field: 'type',
            title: '血压类型',
            formatter:function (value,row,index) {
                if(value==1){
                    return "收缩压"
                }else {
                    return '舒张压'
                }
            }

        },{
            field:"value",
            title:'血压值'
        },{
            field:'time',
            title:'时间'
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
       searchPatientId= $('#search_patient_name').val();
       searchStartTime = new Date($("#search_start_time").val());
       searchEndTime = new Date($("#search_end_time").val());
       tableRefresh();
    })
}

// 表格刷新
function tableRefresh() {
    $('#table').bootstrapTable('refresh', {
        query : {}
    });
}

