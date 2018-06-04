<%--
  Created by IntelliJ IDEA.
  User: zhangyaoci
  Date: 2018/5/25
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>病人住院记录</title>
</head>
<body>

<div class="panel panel-success">
    <div class="panel-heading">
        <h3 class="panel-title">病人住院记录</h3>
    </div>
    <div class="panel-body" style="padding-bottom: 0px">
        <form id="formSearch" class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-xs-2" style="width: 77px;text-align: left;padding-right: 0px;" for="search_patient_name">病人姓名:</label>
                <div class="col-xs-6">
                    <input type="text" class="form-control" id="search_patient_name">
                </div>
                <div class="col-xs-offset-2 col-xs-2" style="text-align:right;margin-left: 215px">
                    <button type="button"  id="btn_search"
                            class="btn btn-primary">查询
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>

<table id="table"></table>
<div id="toolbar" class="btn-group">
    <button id="btn_add" type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
    </button>
    <button id="btn_import" type="button" class="btn btn-default">
        <span class="glyphicon  glyphicon-import" aria-hidden="true"></span>导入
    </button>
    <button id="btn_export" type="button" class="btn btn-default">
        <span class="glyphicon  glyphicon-export" aria-hidden="true"></span>导出
    </button>
</div>

<%--添加模态框--%>
<div id="add_modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增住院记录</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-1 col-sm-1"></div>
                    <div class="col-md-8 col-sm-8">
                        <form class="form-horizontal" role="form" id="hospital_form"
                              style="margin-top: 25px">
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>病人姓名：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                   <select id = "hospital_patient_name" name="hospital_patient_name" class="form-control">
                                   </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>开始时间：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input class="form_date form-control" id="hospital_start_time"
                                           name="hospital_start_time"  placeholder="开始时间">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>结束时间：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input class="form_date form-control" id="hospital_end_time"
                                           name="hospital_end_time"  placeholder="结束时间">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>病房号：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="text" class="form-control" id="hospital_room"
                                           name="hospital_room" placeholder="病房号">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>医生姓名：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <select id = "hospital_doctor_name" class="form-control" name="hospital_doctor_name">
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>护士姓名：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <select id = "hospital_nurse_name" class="form-control" name="hospital_nurse_name">
                                    </select>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-1 col-sm-1"></div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><span
                            class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                    <button type="button" id="add_btn_submit" class="btn btn-primary"><span
                            class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
                    </button>
                </div>

            </div>
        </div>
    </div>
</div>



<%--删除提示模态框 --%>
<div class="modal fade" id="deleteWarning_modal" table-index="-1"
     role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title" id="myModalLabel_1">警告</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-3 col-sm-3" style="text-align: center;">
                        <img src="./images/warning-icon.png" alt=""
                             style="width: 70px; height: 70px; margin-top: 20px;">
                    </div>
                    <div class="col-md-8 col-sm-8">
                        <h3>是否删除该条住院信息</h3>
                        <p>(注意：删除病人的住院信息，该条数据将不再显示)</p>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">
                    <span>取消</span>
                </button>
                <button class="btn btn-danger" type="button" id="delete_confirm">
                    <span>确认删除</span>
                </button>
            </div>
        </div>
    </div>
</div>

<%--导入模态框--%>
<div class="modal fade" id="import_modal" table-index="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog" style="height: 260px">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title" id="myModalLabel_3">导入病人信息</h4>
            </div>
            <div class="modal-body">
                <div id="step1">
                    <div class="row" style="margin-top: 15px">
                        <div class="col-md-1 col-sm-1"></div>
                        <div class="col-md-10 col-sm-10">
                            <div>
                                <h4>点击下面下载货物病人电子表格</h4>
                            </div>
                            <div style="margin-top: 30px; padding-bottom: 15px">
                                <a class="btn btn-info"
                                   href="./download/patientInfo.xls"
                                   target="_blank"> <span class="glyphicon glyphicon-download"></span>
                                    <span>下载</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="step2" class="hide">
                    <div class="row" style="margin-top: 15px">
                        <div class="col-md-1 col-sm-1"></div>
                        <div class="col-md-10 col-sm-10">
                            <div>
                                <h4>请按照病人信息电子表格中指定的格式填写</h4>
                            </div>
                            <div class="alert alert-info"
                                 style="margin-top: 10px; margin-buttom: 30px">
                                <p>注意：表格中各个列均不能为空，若存在未填写的项，则该条信息将不能成功导入</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="step3" class="hide">
                    <div class="row" style="margin-top: 15px">
                        <div class="col-md-1 col-sm-1"></div>
                        <div class="col-md-8 col-sm-10">
                            <div>
                                <div>
                                    <h4>请点击下面上传病人信息电子表格</h4>
                                </div>
                                <div style="margin-top: 30px; margin-buttom: 15px">
                                    <form><input type="file" id="file" name="file" class="file-loading"></form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="hide" id="uploading">
                    <div class="row">
                        <div class="col-md-4 col-sm-4"></div>
                        <div class="col-md-4 col-sm-4">
                            <div id="import_result" class="hide">
                                <div id="import_success" class="hide" style="text-align: center;">
                                    <img src="./images/success-icon.png" alt=""
                                         style="width: 100px; height: 100px;">
                                </div>
                                <div id="import_error" class="hide" style="text-align: center;">
                                    <img src="./images/error-icon.png" alt=""
                                         style="width: 100px; height: 100px;">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-4"></div>
                    </div>
                    <div class="row" style="margin-top: 10px">
                        <div class="col-md-3 col-sm-3"></div>
                        <div class="col-md-6 col-sm-6" style="text-align: center;">
                            <h4 id="import_info"></h4>
                        </div>
                        <div class="col-md-3 col-sm-3"></div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn ben-default" type="button" id="previous">
                        <span>上一步</span>
                    </button>
                    <button class="btn btn-success" type="button" id="next">
                        <span>下一步</span>
                    </button>
                    <button class="btn btn-success hide" type="button" id="submit">
                        <span>&nbsp;&nbsp;&nbsp;上传&nbsp;&nbsp;&nbsp;</span>
                    </button>
                    <button class="btn btn-success hide" type="button"
                            id="confirm" data-dismiss="modal">
                        <span>&nbsp;&nbsp;&nbsp;确认&nbsp;&nbsp;&nbsp;</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<%--编辑模态框--%>
<div id="edit_modal" class="modal fade" table-index="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title" id="myModalLabel_2">编辑病人信息</h4>
            </div>
            <div class="modal-body">
                <!-- 模态框的内容 -->
                <div class="row">
                    <div class="col-md-1 col-sm-1"></div>
                    <div class="col-md-8 col-sm-8">
                        <form class="form-horizontal" role="form" id="hospital_form_edit"
                              style="margin-top: 25px">
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>病人姓名：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <select id = "hospital_patient_name_edit" name="hospital_patient_name_edit" class="form-control" disabled >
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>开始时间：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input class="form_date form-control" id="hospital_start_time_edit"
                                           name="hospital_start_time_edit"  placeholder="开始时间">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>结束时间：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input class="form_date form-control" id="hospital_end_time_edit"
                                           name="hospital_end_time_edit"  placeholder="结束时间">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>病房号：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="text" class="form-control" id="hospital_room_edit"
                                           name="hospital_room_edit" placeholder="病房号">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>医生姓名：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <select id = "hospital_doctor_name_edit" class="form-control" name="hospital_doctor_name_edit">
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>护士姓名：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <select id = "hospital_nurse_name_edit" class="form-control" name="hospital_nurse_name_edit">
                                    </select>
                                </div>
                            </div>

                        </form>
                    </div>
                    <div class="col-md-1 col-sm-1"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">
                    <span>取消</span>
                </button>
                <button class="btn btn-success" type="button" id="edit_modal_submit">
                    <span>确认更改</span>
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 导出模态框 -->
<div class="modal fade" id="export_modal" table-index="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel_4">导出病人信息</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-3 col-sm-3" style="text-align: center;">
                        <img src="./images/warning-icon.png" alt=""
                             style="width: 70px; height: 70px; margin-top: 20px;">
                    </div>
                    <div class="col-md-8 col-sm-8">
                        <h3>是否确认导出病人</h3>
                        <p>(注意：请确定要导出的病人信息，导出的内容为当前列表的搜索结果)</p>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">
                    <span>取消</span>
                </button>
                <button class="btn btn-success" type="button" id="export_patient">
                    <span>确认下载</span>
                </button>
            </div>
        </div>
    </div>
</div>

<script src="./js/pageJs/patient_hospital.js"></script>

</body>
</html>
