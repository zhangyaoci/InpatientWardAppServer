<%--
  Created by IntelliJ IDEA.
  User: zhangyaoci
  Date: 2018/5/25
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>血压</title>
</head>
<body>

<%--panel 表头--%>
<div class="panel panel-success">
    <div class="panel-heading">
        <h3 class="panel-title">病人血压数据</h3>
    </div>
    <div class="panel-body" style="padding-bottom: 0px">
        <form id="formSearch" class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-xs-1" for="search_patient_name">姓名:</label>
                <div class="col-xs-2">
                    <select class="form-control"   id="search_patient_name">
                    </select>
                </div>
                <label class="control-label col-xs-1" for="search_start_time">时间:</label>
                <div class="col-xs-3">
                    <input type="text" class="form-control" id="search_start_time" value="2018-01-01 00:00">
                </div>
                <label class="control-label" style="float: left;text-align: center;">~</label>
                <div class="col-xs-3">
                    <input type="text" class="form-control" id="search_end_time" >
                </div>
                <div class="col-xs-1" style="text-align:left;">
                    <button type="button"  id="btn_search"
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

<script src="./js/pageJs/patient_bp.js"></script>
</body>
</html>
