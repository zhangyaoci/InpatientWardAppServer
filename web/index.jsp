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
    <link rel="shortcut icon" href="./images/logo_ico.png" type="image/x-icon" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>移动病人APP后台管理系统</title>
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport">
    <link rel="stylesheet" type="text/css" href="./css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="./css/bootstrap-table.css">
    <link rel="stylesheet" type="text/css" href="./css/jquery.mloading.css">
    <link rel="stylesheet" type="text/css" href="./css/bootstrap-select.css">
    <link rel="stylesheet" type="text/css" href="./css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="./css/bootstrapValidator.css">
    <link rel="stylesheet" type="text/css" href="./css/fileinput.css">
</head>
<body>
<div class="header">
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <img src="./images/logo.png" alt="Logo" style="width:40px;margin-top: 7px;">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#example-navbar-collapse">
                    <span class="sr-only">切换导航</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div><p class="navbar-text">移动病人APP后台管理系统</p></div>
            <div class="collapse navbar-collapse" id="example-navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="./index.jsp">病人管理模块</a></li>
                    <li><a href="./doctor.jsp">医生管理模块</a></li>
                    <li><a href="./nurse.jsp">护士管理模块</a></li>
                    <li><a href="./user.jsp">病人家属管理模块</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${sessionScope.get("adminUser")["name"]}
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="dLabel">
                            <li><a href=""><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;个人设置</a></li>
                            <li><a href=""><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;登录日志</a></li>
                            <li><a href=""><span class="glyphicon  glyphicon-bell"></span>&nbsp;&nbsp;系统消息</a> </li>
                        </ul>
                    </li>
                    <li><a href="login.jsp"><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;退出</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<div class="main " style="padding-left: 6%;">
    <div class="row" style="margin: 0px;">
        <div class="col-lg-2">
            <div class="list-group">
                <a href="javascript:void(0)" name="page/patient_info.jsp" class="list-group-item">基本信息</a>
                <a href="javascript:void(0)" name="page/patient_hospital.jsp" class="list-group-item ">住院记录</a>
                <a href="javascript:void(0)" name="page/patient_bp.jsp" class="list-group-item ">血压</a>
                <a href="javascript:void(0)" name="page/patient_temp.jsp" class="list-group-item ">体温</a>
                <a href="javascript:void(0)" name="page/patient_hr.jsp" class="list-group-item ">心率</a>
                <a href="javascript:void(0)" name="page/patient_bg.jsp" class="list-group-item">血糖</a>
                <a href="javascript:void(0)" name="page/patient_bos.jsp" class="list-group-item ">血氧饱和度</a>
            </div>
        </div>
        <div class="col-lg-9" id="panel">
        </div>
    </div>
</div>



<!-- 提示消息模态框 -->
<div class="modal fade" id="global_info_modal" table-index="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">信息</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-4 col-sm-4"></div>
                    <div class="col-md-4 col-sm-4">
                        <div id="info_success" class=" hide" style="text-align: center;">
                            <img src="./images/success-icon.png" alt=""
                                 style="width: 100px; height: 100px;">
                        </div>
                        <div id="info_error" style="text-align: center;">
                            <img src="./images/error-icon.png" alt=""
                                 style="width: 100px; height: 100px;">
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-4"></div>
                </div>
                <div class="row" style="margin-top: 10px">
                    <div class="col-md-3"></div>
                    <div class="col-md-6" style="text-align: center;">
                        <h4 id="info_summary"></h4>
                    </div>
                    <div class="col-md-3"></div>
                </div>
                <dic class="row" style="margin-top: 10px">
                    <div class="col-md-3"></div>
                    <div class="col-md-6" style="text-align: center;">
                        <p id='info_content'></p>
                    </div>
                    <div class="col-md-3"></div>
                </dic>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">
                    <span>&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;</span>
                </button>
            </div>
        </div>
    </div>
</div>

<script src="./js/jquery.js"></script>
<script src="./js/jquery.mloading.js"></script>
<script src="./js/bootstrap.js"></script>
<script src="./js/popper.js"></script>
<script src="./js/bootstrap-table.js"></script>
<script src="./js/bootstrap-table-zh-CN.js"></script>
<script src="./js/bootstrap-select.js"></script>
<script src="./js/bootstrap-datetimepicker.min.js"></script>
<script src="./js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="./js/bootstrapValidator.js"></script>
<script src="./js/fileinput.js"></script>
<script src="./js/fileinput-zh.js"></script>
<script src="./js/pageJs/index.js"></script>
</body>
</html>
