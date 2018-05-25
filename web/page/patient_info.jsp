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
            <span class="glyphicon  glyphicon-log-in" aria-hidden="true"></span>导入
        </button>
        <button id="btn_export" type="button" class="btn btn-default">
            <span class="glyphicon  glyphicon-log-out" aria-hidden="true"></span>导出
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
                    <div class="form-group">
                        <label for="txt_departmentname">部门名称</label>
                        <input type="text" name="txt_departmentname" class="form-control"
                               id="txt_departmentname" placeholder="部门名称">
                    </div>
                    <div class="form-group">
                        <label for="txt_parentdepartment">上级部门</label>
                        <input type="text" name="txt_parentdepartment" class="form-control"
                               id="txt_parentdepartment" placeholder="上级部门">
                    </div>
                    <div class="form-group">
                        <label for="txt_departmentlevel">部门级别</label>
                        <input type="text" name="txt_departmentlevel" class="form-control"
                               id="txt_departmentlevel" placeholder="部门级别">
                    </div>
                    <div class="form-group">
                        <label for="txt_statu">描述</label>
                        <input type="text" name="txt_statu" class="form-control" id="txt_statu"
                               placeholder="状态">
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><span
                            class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                    <button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal"><span
                            class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
                    </button>
                </div>

            </div>
        </div>
    </div>


    <script src="./js/pageJs/patient_info.js"></script>
</body>
</html>
