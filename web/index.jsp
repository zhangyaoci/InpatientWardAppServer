<%--
  Created by IntelliJ IDEA.
  User: zhangyaoci
  Date: 2018/5/20
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>移动病人APP后台管理系统</title>
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport">
    <link rel="stylesheet" type="text/css" href="./css/bootstrap.css">

</head>
<body>
<div class="header">
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <img src="./images/logo.png" alt="Logo" style="width:40px;margin-top: 7px;">
            </div>
            <div><p class="navbar-text">移动病人APP后台管理系统</p></div>
            <div>

                <ul class="nav navbar-nav">
                    <li class="active"><a href="./index.jsp">病人管理模块</a></li>
                    <li ><a href="./doctor.jsp">医生管理模块</a></li>
                    <li ><a href="./nurse.jsp">护士管理模块</a></li>
                    <li ><a href="./user.jsp">病人家属管理模块</a></li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a  type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${sessionScope.get("adminUser")["name"]}
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="dLabel">
                            <li><a href=""><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;个人设置</a></li>
                            <li><a href=""><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;登录日志</a></li>
                        </ul>
                    </li>
                    <li><a href="login.jsp" ><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;退出</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<div class="main " style="padding-left: 6%;">
    <div class="row">
        <div class="col-lg-2">
            <div class="list-group" >
                <a href="javascript:void(0)" onclick="choosePanel(1)"  class="list-group-item ">基本信息</a>
                <a href="javascript:void(0)" onclick="choosePanel(2)" class="list-group-item">住院记录</a>
                <a href="javascript:void(0)" onclick="choosePanel(3)"  class="list-group-item">血压</a>
                <a href="javascript:void(0)" onclick="choosePanel(4)"  class="list-group-item">体温</a>
                <a href="javascript:void(0)" onclick="choosePanel(5)"  class="list-group-item">心率</a>
                <a href="javascript:void(0)" onclick="choosePanel(6)"  class="list-group-item">血糖</a>
                <a href="javascript:void(0)" onclick="choosePanel(7)"  class="list-group-item">血氧饱和度</a>
            </div>
        </div>
        <div class="col-lg-9">
            <div id="panel_1">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title">病人基本信息</h3>
                    </div>
                    <div class="panel-body">
                        这是一个基本的面板
                    </div>
                </div>
            </div>

            <div id="panel_2" hidden>
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title">病住院记录</h3>
                    </div>
                    <div class="panel-body">
                        这是一个基本的面板
                    </div>
                </div>
            </div>

            <div id="panel_3" hidden>
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title">血压</h3>
                    </div>
                    <div class="panel-body">
                        这是一个基本的面板
                    </div>
                </div>
            </div>
            <div id="panel_4" hidden>
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title">体温</h3>
                    </div>
                    <div class="panel-body">
                        这是一个基本的面板
                    </div>
                </div>
            </div>

            <div id="panel_5" hidden>
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title">心率</h3>
                    </div>
                    <div class="panel-body">
                        这是一个基本的面板
                    </div>
                </div>
            </div>

            <div id="panel_6" style="" hidden >
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title">血糖</h3>
                    </div>
                    <div class="panel-body">
                        这是一个基本的面板
                    </div>
                </div>
            </div>

            <div id="panel_7" hidden>
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title">血氧饱和度</h3>
                    </div>
                    <div class="panel-body">
                        这是一个基本的面板
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>



<script src="./js/jquery.js"></script>
<script src="./js/popper.js"></script>
<script src="./js/bootstrap.js"></script>
<script src="./js/pageJs/index.js"></script>
</body>
</html>
