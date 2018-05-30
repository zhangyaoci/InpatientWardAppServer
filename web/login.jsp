<%--
  Created by IntelliJ IDEA.
  User: zhangyaoci
  Date: 2018/5/20
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--在返回到login.jsp前清空session里面的用户数据--%>
<% session.removeAttribute("adminUser");%>
<html>
<head>
    <link rel="shortcut icon" href="./images/logo.ico" type="image/x-icon" />
    <meta charset="UTF-8">
    <title>登录首页</title>
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport">
    <link rel="stylesheet" type="text/css" href="./css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="./css/pageCss/login.css">
    <link rel="stylesheet" type="text/css" href="./css/bootstrapValidator.css">
</head>
<body>
        <div class="container">
            <form action="serverLoginAction_adminLogin" method="post" class="form-signin" role="form" id="loginForm">
                <h2 class="text-center form-header">移动病房后台系统</h2>
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                        <input type="text" class="form-control" name="name" placeholder="用户名" required autofocus />
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                        <input type="password"  id="password"  class="form-control" name="password" placeholder="密码" required />
                    </div>
                </div>


                <div class="form-group">
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group has-feedback">
                                <div class="input-group">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-qrcode"></span></span>
                                    <input id="idcode-btn" name="IDcode" class="form-control" placeholder="请输入验证码" maxlength="4" type="text">
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-6">
                            <div id="code" name="IDcodeConfirm"
                                 style="display: inline-block;margin-top: 3px;background-color: #eee; text-align: center;width: 45%;font-size: 20px;border-radius: 4px;"></div>
                            <a href="javascript:void(0)" onclick="createCode()">刷新验证码</a>
                        </div>

                    </div>
                </div>
                <div class="form-group" style="color: #a94442" >
                   ${errorMessage}
                </div>
                <button class="btn  btn-primary btn-block" type="submit" onclick="submitForm()">登录</button>
            </form>
        </div>


        <script src="./js/jquery.js"></script>
        <script src="./js/popper.js"></script>
        <script src="./js/md5.js"></script>
        <script src="./js/bootstrap.js"></script>
        <script src="./js/bootstrapValidator.js"></script>
        <script src="./js/pageJs/login.js"></script>
</body>
</html>
