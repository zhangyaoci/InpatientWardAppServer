//# sourceURL=patient_info.js

$(function () {
    /*数据表格初始化*/
    patientListInit();

    /*数据验证初始化*/
    bootstrapValidatorInit();


    /*按钮初始化*/
    addPatientAction();
    editPatientAction();
    deletePatientAction();
    importPatientAction();
    exportPatientAction()


})



/*数据初始化*/
function patientListInit(){
    let rows =5;
    var panel_1_table = $('#table').bootstrapTable({
        url: 'serverPatientAction_acquirePatientList',                      //请求后台的URL（*）
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
                page: (params.offset / params.limit) + 1  //页码
            };
            return {"parameter":temp};
        },
        responseHandler: function(res) {
            return {
                "total":res.size, //总页数
                "rows":res.patientList   //数据
            };
        },
        columns: [{
            checkbox: true,
            visible: true                  //是否显示复选框
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
            field: 'address',
            title: '住址'
        },{
            field: 'hospitalState',
            title: '住院状态',
            formatter:function (value,row,index) {
                if(value==1){
                    return "正在住院"
                }else {
                    return "已经出院"
                }
            }
        },{
            field:'roomNumber',
            title:"病房号",
            formatter:function (value,row,index) {
                if(value==''){
                    return " 无"
                }
                else{
                    return value
                }
            }
        },{
            field:'guardianName',
            title:"监护人姓名",
            formatter:function (value,row,index) {
                if(value==''){
                    return '无';
                }else{
                    return value;
                }
            }
        },{
            field:'guardianPhone',
            title:'监护人联系电话',
            formatter:function (value,row,index) {
                if(value==''){
                    return '无'
                }
                else {
                    return value;
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



function  addPatientAction() {
    $("#btn_add").click(function () {
        $('#panel_add').modal();
        $('#patient_dateOfBirth').datetimepicker(
            {
                format:'yyyy-mm-dd',
                language : 'zh-CN',
                weekStart : 1,
                todayBtn : 1,
                autoClose : 1,
                todayHighlight : 1,
                startView : 2,
                forceParse : 0,
                minView:2,
                autoclose: true,
                startDate: "2013-02-14"
            }
        );

    });
    $('#add_btn_submit').click(function() {
        /*验证表单数据是否合理*/
        $("#patient_form").data("bootstrapValidator").validate()
        let isValidOfPatient=$("#patient_form").data("bootstrapValidator").isValid();
        if(isValidOfPatient){
            let patient_dateOfBirth = new Date($('#patient_dateOfBirth').val());
            var data = {
                name : $('#patient_name').val(),
                sex : $('#patient_sex').val(),
                phone : $('#patient_phone').val(),
                tempDate :patient_dateOfBirth,
                address : $('#patient_address').val(),
                introduction : $('#patient_introduction').val(),
                picturePath : $('#patient_picturePath').val(),
            }
            $.ajax({
                type : "POST",
                url : "serverPatientAction_addPatientAction",
                dataType : "json",
                contentType : "application/json",
                data : JSON.stringify({'patient':data}),
                success : function(response) {
                    $('#panel_add').modal("hide");
                    var msg;
                    var type;
                    var append = '';
                    if (response.result == "success") {
                        type = "success";
                        msg = "病人信息添加成";
                    } else if (response.result == "error") {
                        type = "error";
                        msg = "病人信息添加失败";
                    }
                    showMsg(type, msg, append);
                    tableRefresh();

                    // reset
                    $('#goods_name').val("");
                    $('#goods_type').val("");
                    $('#goods_size').val("");
                    $('#goods_value').val("");
                    $('#goods_form').bootstrapValidator("resetForm", true);
                },
                error : function(xhr, textStatus, errorThrow) {
                    $('#add_modal').modal("hide");
                    // handler error
                    handleAjaxError(xhr.status);
                }
            })
        }
    })
}

function editPatientAction() {
    
}

function deletePatientAction() {
    
}

function importPatientAction() {
    
}

function exportPatientAction() {
    
}

/*提交表单数据认证*/
function bootstrapValidatorInit() {
    $("#patient_form").bootstrapValidator({
        message : 'This is not valid',
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
        },
        excluded : [ ':disabled' ],
        fields : {
            patient_name : {
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    }
                }
            },
            patient_phone : {
                validators : {
                    notEmpty : {
                        message : '电话号码不能为空'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '电话号码必须为11位数字'
                    },
                    regexp: {
                        regexp: /^1(3|4|5|7|8)\d{9}$/,
                        message: '电话号码格式错误'
                    }

                }
            }
        }
    });
}

// 表格刷新
function tableRefresh() {
    $('#table').bootstrapTable('refresh', {
        query : {}
    });
}