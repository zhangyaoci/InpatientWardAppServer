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

    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title">病人基本信息</h3>
        </div>
        <div class="panel-body" style="padding-bottom: 0px">
            <form id="formSearch" class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-sm-1" for="txt_search_departmentname">名称</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="txt_search_departmentname">
                    </div>
                    <label class="control-label col-sm-1" for="txt_search_statu">状态</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="txt_search_statu">
                    </div>
                    <div class="col-sm-2" style="text-align:left;">
                        <button type="button" style="margin-left:50px" id="btn_query"
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
        <button id="btn_edit" type="button" class="btn btn-default">
            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
        </button>
        <button id="btn_delete" type="button" class="btn btn-default">
            <span class="glyphicon  glyphicon-trash" aria-hidden="true"></span>删除
        </button>
        <button id="btn_import" type="button" class="btn btn-default">
            <span class="glyphicon  glyphicon-import" aria-hidden="true"></span>导入
        </button>
        <button id="btn_export" type="button" class="btn btn-default">
            <span class="glyphicon  glyphicon-export" aria-hidden="true"></span>导出
        </button>
    </div>


    <div id="panel_add" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
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
                                    <label  class="control-label col-md-4 col-sm-4"> <span>姓名：</span>
                                    </label>
                                    <div class="col-md-8 col-sm-8">
                                        <input type="text" class="form-control" id="patient_name"
                                               name="patient_name" placeholder="姓名" maxlength="4">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="control-label col-md-4 col-sm-4"> <span>性别：</span>
                                    </label>

                                    <div class="col-md-8 col-sm-8">
                                        <select  id="patient_sex" name="patient_sex"  class="form-control">
                                            <option value="1">男</option>
                                            <option value="0">女</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="control-label col-md-4 col-sm-4"> <span>电话号码：</span>
                                    </label>
                                    <div class="col-md-8 col-sm-8">
                                        <input type="text" class="form-control" id="patient_phone"
                                               name="patient_phone" placeholder="电话号码">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="control-label col-md-4 col-sm-4"> <span>出生日期：</span>
                                    </label>
                                    <div class="col-md-8 col-sm-8">
                                        <input class="form_date form-control" id="patient_dateOfBirth" name="patient_dateOfBirth" value="2012-05-15" placeholder="出生日期">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label  class="control-label col-md-4 col-sm-4"> <span> 现住地址：</span>
                                    </label>
                                    <div class="col-md-8 col-sm-8">
                                        <input type="text" class="form-control" id="patient_address"
                                               name="patient_address" placeholder="现住地址">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label  class="control-label col-md-4 col-sm-4"> <span> 病例简介：</span>
                                    </label>
                                    <div class="col-md-8 col-sm-8">
                                        <input type="text" class="form-control" id="patient_introduction"
                                               name="patient_introduction" placeholder="病例简介">
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label  class="control-label col-md-4 col-sm-4"> <span>头像选择：</span>
                                    </label>

                                    <div class="col-md-8 col-sm-8">
                                        <select  id="patient_picturePath" name="patient_picturePath"  class="form-control">
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
                    <button type="button" id="add_btn_submit" class="btn btn-primary" ><span
                            class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
                    </button>
                </div>

            </div>
        </div>
         </div>
    </div>


    <script src="./js/pageJs/patient_info.js"></script>
</body>
</html>
