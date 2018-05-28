<%--
  Created by IntelliJ IDEA.
  User: zhangyaoci
  Date: 2018/5/25
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>病人基本信息</title>
</head>
<body>

<%--panel 表头--%>
<div class="panel panel-success">
    <div class="panel-heading">
        <h3 class="panel-title">病人基本信息</h3>
    </div>
    <div class="panel-body" style="padding-bottom: 0px">
        <form id="formSearch" class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-xs-2" for="search_patient_name">病人姓名:</label>
                <div class="col-xs-6">
                    <input type="text" class="form-control" id="search_patient_name">
                </div>
                <div class="col-xs-offset-2 col-xs-2" style="text-align:left;">
                    <button type="button" style="margin-left:50px" id="btn_search"
                            class="btn btn-primary">查询
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>

<%--对应的数据表--%>
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
                <h4 class="modal-title" id="myModalLabel">新增病人数据</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-1 col-sm-1"></div>
                    <div class="col-md-8 col-sm-8">
                        <form class="form-horizontal" role="form" id="patient_form"
                              style="margin-top: 25px">
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>姓名：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="text" class="form-control" id="patient_name"
                                           name="patient_name" placeholder="姓名" maxlength="4">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>性别：</span>
                                </label>

                                <div class="col-md-8 col-sm-8">
                                    <select id="patient_sex" name="patient_sex" class="form-control">
                                        <option value="1">男</option>
                                        <option value="0">女</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>电话号码：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="text" class="form-control" id="patient_phone"
                                           name="patient_phone" placeholder="电话号码">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>出生日期：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input class="form_date form-control" id="patient_dateOfBirth"
                                           name="patient_dateOfBirth" value="2012-05-15" placeholder="出生日期">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span> 现住地址：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="text" class="form-control" id="patient_address"
                                           name="patient_address" placeholder="现住地址">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span> 病例简介：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="text" class="form-control" id="patient_introduction"
                                           name="patient_introduction" placeholder="病例简介">
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>头像选择：</span>
                                </label>

                                <div class="col-md-8 col-sm-8">
                                    <select id="patient_picturePath" name="patient_picturePath" class="form-control">
                                        <option value="./assets/imgs/22.png">./assets/imgs/22.png</option>
                                        <option value="./assets/imgs/23.png">./assets/imgs/23.png</option>
                                        <option value="./assets/imgs/24.png">./assets/imgs/24.png</option>
                                        <option value="./assets/imgs/25.png">./assets/imgs/25.png</option>
                                        <option value="./assets/imgs/26.png">./assets/imgs/26.png</option>
                                        <option value="./assets/imgs/27.png">./assets/imgs/27.png</option>
                                        <option value="./assets/imgs/28.png">./assets/imgs/28.png</option>
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
                        <h3>是否删除该条病人信息</h3>
                        <p>(注意：若该病人有住院记录、关联用户数据，则该病人信息将删除失败。如需删除该病人的信息，请先确保该病人没有关联住院记录和用户表)</p>
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

<%--导入货物信息模态框--%>
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

<%--编辑病人模态框--%>
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
                        <form class="form-horizontal" role="form" id="patient_form_edit"
                              style="margin-top: 25px">
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>姓名：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="text" class="form-control" id="patient_name_edit"
                                           name="patient_name_edit" placeholder="姓名" maxlength="4">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>性别：</span>
                                </label>

                                <div class="col-md-8 col-sm-8">
                                    <select id="patient_sex_edit" name="patient_sex_edit" class="form-control">
                                        <option value="1">男</option>
                                        <option value="0">女</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>电话号码：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="text" class="form-control" id="patient_phone_edit"
                                           name="patient_phone_edit" placeholder="电话号码">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>出生日期：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input class="form_date form-control" id="patient_dateOfBirth_edit"
                                           name="patient_dateOfBirth_edit" placeholder="出生日期">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span> 现住地址：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="text" class="form-control" id="patient_address_edit"
                                           name="patient_address_edit" placeholder="现住地址">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span> 病例简介：</span>
                                </label>
                                <div class="col-md-8 col-sm-8">
                                    <input type="text" class="form-control" id="patient_introduction_edit"
                                           name="patient_introduction_edit" placeholder="病例简介">
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="control-label col-md-4 col-sm-4"> <span>头像选择：</span>
                                </label>

                                <div class="col-md-8 col-sm-8">
                                    <select id="patient_picturePath_edit" name="patient_picturePath_edit"
                                            class="form-control">
                                        <option value="./assets/imgs/22.png">./assets/imgs/22.png</option>
                                        <option value="./assets/imgs/23.png">./assets/imgs/23.png</option>
                                        <option value="./assets/imgs/24.png">./assets/imgs/24.png</option>
                                        <option value="./assets/imgs/25.png">./assets/imgs/25.png</option>
                                        <option value="./assets/imgs/26.png">./assets/imgs/26.png</option>
                                        <option value="./assets/imgs/27.png">./assets/imgs/27.png</option>
                                        <option value="./assets/imgs/28.png">./assets/imgs/28.png</option>
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


<!-- 导出货物信息模态框 -->
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

<script src="./js/pageJs/patient_info.js"></script>
</body>
</html>
