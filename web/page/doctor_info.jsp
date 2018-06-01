<%--
  Created by IntelliJ IDEA.
  User: zhangyaoci
  Date: 2018/5/28
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>医生基本信息</title>
</head>
<body>
<div class="panel panel-success">
    <div class="panel-heading">
        <h3 class="panel-title">医生基本信息</h3>
    </div>
    <div class="panel-body" style="padding-bottom: 0px">
        <form id="formSearch" class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-xs-2" for="search_doctor_name">医生姓名:</label>
                <div class="col-xs-6">
                    <input type="text" class="form-control" id="search_doctor_name">
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

<script src="./js/pageJs/doctor_info.js"></script>
</body>
</html>
