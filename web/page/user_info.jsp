<%--
  Created by IntelliJ IDEA.
  User: zhangyaoci
  Date: 2018/6/1
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户基本信息</title>
</head>
<body>
<%--panel 表头--%>
<div class="panel panel-success">
    <div class="panel-heading">
        <h3 class="panel-title">用户基本信息</h3>
    </div>
    <div class="panel-body" style="padding-bottom: 0px">
        <form id="formSearch" class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-xs-2" for="search_user_name">用户姓名:</label>
                <div class="col-xs-6">
                    <input type="text" class="form-control" id="search_user_name">
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
</body>
</html>
