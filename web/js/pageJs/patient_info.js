//# sourceURL=patient_info.js

var selectID;
var searchPatientName="";

$(function () {

    /*数据表格初始化*/
    patientListInit();

    /*数据验证初始化*/
    bootstrapValidatorInit();


    /*按钮初始化*/
    searchAction();
    addPatientAction();
    editPatientAction();
    deletePatientAction();
    importPatientAction();
    exportPatientAction()


    /*导入按钮初始化*/
    import_exl_init();

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
                page: (params.offset / params.limit) + 1, //页码
                patientName:searchPatientName

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
            field:'patientId',
            title:"病人序号"                  //是否显示复选框
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

function  addPatientAction() {
    $("#btn_add").click(function () {
        $('#add_modal').modal();
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
                autoclose: true

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
                    $('#add_modal').modal("hide");
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
    $('#edit_modal_submit').click(
        function() {
            $('#patient_form_edit').data('bootstrapValidator')
                .validate();
            let isValidOfPatient =$('#patient_form_edit').data('bootstrapValidator').isValid()
            if (isValidOfPatient) {

                let patient_dateOfBirth = new Date($('#patient_dateOfBirth_edit').val());
                let data = {
                    patientId:selectID,
                    name : $('#patient_name_edit').val(),
                    sex : $('#patient_sex_edit').val(),
                    phone : $('#patient_phone_edit').val(),
                    tempDate :patient_dateOfBirth,
                    address : $('#patient_address_edit').val(),
                    introduction : $('#patient_introduction_edit').val(),
                    picturePath : $('#patient_picturePath_edit').val(),
                }

                // ajax
                $.ajax({
                    type : "POST",
                    url : 'serverPatientAction_updatePatientAction',
                    dataType : "json",
                    contentType : "application/json",
                    data : JSON.stringify({'patient':data}),
                    success : function(response) {
                        $('#edit_modal').modal("hide");
                        var type;
                        var msg;
                        var append = '';
                        if (response.result == "success") {
                            type = "success";
                            msg = "病人信息修改成功";
                        } else if (resposne == "error") {
                            type = "error";
                            msg = "病人信息修改失败";
                        }
                        showMsg(type, msg, append);
                        tableRefresh();
                    },
                    error : function(xhr, textStatus, errorThrow) {
                        $('#edit_modal').modal("hide");
                        // handler error
                        handleAjaxError(xhr.status);
                    }
                });
            }
        });
}

function deletePatientAction() {
    $('#delete_confirm').click(function(){
        var data = {
            "patientId" : selectID
        }
        // ajax
        $.ajax({
            type : "post",
            url : "serverPatientAction_deletePatientAction",
            dataType : "json",
            contentType : "application/json",
            data : JSON.stringify(data),
            success : function(response){
                $('#deleteWarning_modal').modal("hide");
                var type;
                var msg;
                var append = '';
                if(response.result == "success"){
                    type = "success";
                    msg = "病人信息删除成功";
                }else{
                    type = "error";
                    msg = "病人信息删除失败";
                }
                showMsg(type, msg, append);
                tableRefresh();
            },error : function(xhr, textStatus, errorThrow){
                $('#deleteWarning_modal').modal("hide");
                // handler error
                handleAjaxError(xhr.status);
            }
        })

        $('#deleteWarning_modal').modal('hide');
    });
}

function exportPatientAction() {
    $('#btn_export').click(function() {
        $('#export_modal').modal("show");
    })

    $('#export_patient').click(function(){
        var data = {
            patientName : searchPatientName,
        }
        var url = "fileDownloadAction_exportPatient?" + $.param(data)
        window.open(url, '_blank');
        $('#export_modal').modal("hide");
    })
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

    /*编辑模块框中的数据验证*/
    $("#patient_form_edit").bootstrapValidator({
        message : 'This is not valid',
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
        },
        excluded : [ ':disabled' ],
        fields : {
            patient_name_edit : {
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    }
                }
            },
            patient_phone_edit : {
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
// 行编辑操作
function rowEditOperation(row) {
    $('#edit_modal').modal("show");
    $('#patient_dateOfBirth_edit').datetimepicker(
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
            autoclose: true
        }
    );

    /*添加数据到模块框*/
    $('#patient_form_edit').bootstrapValidator("resetForm", true);


    /*数据的初始化操作*/
    let dateOfbirth_patient = row.dateOfBirth.split("T");
    $('#patient_name_edit').val(row.name);
    $('#patient_sex_edit').val(row.sex);
    $('#patient_phone_edit').val(row.phone);
    $('#patient_dateOfBirth_edit').val(dateOfbirth_patient[0]);
    $('#patient_address_edit').val(row.address);
    $('#patient_introduction_edit').val(row.introduction);
    $('#patient_picturePath_edit').val(row.picturePath);


}


var import_step = 1;
var import_start = 1;
var import_end = 3;
function importPatientAction() {
    /*导入点击事件*/
    $('#btn_import').click(function() {
        $('#import_modal').modal("show");
    });

    /*当前步骤大于开始步骤才往前走*/
    $('#previous').click(function() {
        if (import_step > import_start) {
            var preID = "step" + (import_step - 1)
            var nowID = "step" + import_step;

            $('#' + nowID).addClass("hide");
            $('#' + preID).removeClass("hide");
            import_step--;
        }
    })

    /*当前步骤小于最后步骤才往后走*/
    $('#next').click(function() {
        if (import_step < import_end) {
            var nowID = "step" + import_step;
            var nextID = "step" + (import_step + 1);

            $('#' + nowID).addClass("hide");
            $('#' + nextID).removeClass("hide");
            import_step++;
        }
    })

    /*文件选择之后，才显示提交按钮*/
    $('#file').on("change", function() {
        $('#previous').addClass("hide");
        $('#next').addClass("hide");
        $('#submit').removeClass("hide");
    })

    $('#submit').click(function() {

        /*隐藏提交按钮*/
        $('#submit').addClass("hide");
        /*提交文件*/
        $("#file").fileinput("upload");

        /*上传成功处理方法*/
        $("#file").on("fileuploaded", function(event, data, previewId, index) {

            $("#step3").addClass("hide");
            $("#uploading").removeClass("hide");

            var info;

            if(data.response.result == "success"){
                info = data.response.message;
                $('#import_success').removeClass('hide');
            }else{
                info = "数据导入错误"
                $('#import_error').removeClass('hide');
            }
            $('#import_result').removeClass('hide');
            $('#import_info').text(info);
            $('#confirm').removeClass("hide");

        });

        $('#confirm').click(function() {
            // modal dissmiss
            importModalReset();
            tableRefresh();
        })

    })



}
/*文件上传*/
function  import_exl_init() {
    $('#file').fileinput({
        showUpload : false,
        showRemove : false,
        uploadAsync: true,
        uploadLabel: "上传",//设置上传按钮的汉字
        uploadClass: "btn btn-primary",//设置上传按钮样式
        showCaption: false,//是否显示标题
        language: "zh",//配置语言
        uploadUrl: "fileUploadAction_patientUpload",
        maxFileSize : 0,//不限制文件上传大小
        maxFileCount: 1,/*允许最大上传数，可以多个，当前设置单个*/
        enctype: 'multipart/form-data',
        allowedFileExtensions : ['xls', 'xlsx'],/*上传文件格式*/
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        dropZoneTitle: "请通过拖拽文件放到这里",
        dropZoneClickTitle: "或者点击此区域添加文件",
        showBrowse: false,
        dropZoneEnabled:true,
        browseOnZoneClick: true,
        dropZoneTitleClass:'file-drop-zone-title',
        slugCallback : function(filename) {
            return filename.replace('(', '_').replace(']', '_');
        }
    });

    /*当点击清空处理的时候*/
    $("#file").on("filecleared",function(event, data, msg){
        $('#previous').removeClass("hide");
        $('#next').removeClass("hide");
        $('#submit').addClass("hide");
    })

    /*文件选择后事件*/
    $("#file").on("filebatchselected", function(event, files) {
        $('#previous').addClass("hide");
        $('#next').addClass("hide");
        $('#submit').removeClass("hide");
    })
}


/* 导入货物模态框重置*/
function importModalReset(){
    var i;
    for(i = import_start; i <= import_end; i++){
        var step = "step" + i;
        $('#' + step).removeClass("hide")
    }
    for(i = import_start; i <= import_end; i++){
        var step = "step" + i;
        $('#' + step).addClass("hide")
    }
    $('#step' + import_start).removeClass("hide");

    $('#import_result').removeClass("hide");
    $('#import_success').removeClass('hide');
    $('#import_error').removeClass('hide');


    $('#import_result').addClass("hide");
    $('#import_success').addClass('hide');
    $('#import_error').addClass('hide');
    $('#import_info').text("");
    $('#file').val("");

    $('#previous').removeClass("hide");
    $('#next').removeClass("hide");
    $('#submit').removeClass("hide");
    $('#confirm').removeClass("hide");
    $('#submit').addClass("hide");
    $('#confirm').addClass("hide");


    $("#file").fileinput("clear");

    $('#file').on("change", function() {
        $('#previous').addClass("hide");
        $('#next').addClass("hide");
        $('#submit').removeClass("hide");
    })

    import_step = 1;
}


// 搜索动作
function searchAction() {
    $('#btn_search').click(function() {
        searchPatientName = $('#search_patient_name').val();
        tableRefresh();
    })
}