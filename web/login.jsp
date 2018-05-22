<%--
  Created by IntelliJ IDEA.
  User: zhangyaoci
  Date: 2018/5/20
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录首页</title>
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport">
    <link rel="stylesheet" type="text/css" href="./css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="./css/pageCss/login.css">
</head>
<body>
        <div class="container">
            <form action="serverLoginAction_adminLogin" method="post" class="form-signin" role="form">
                <h2 class="text-center form-header">用户登录</h2>
                <div class="form-group">
                    <input type="text" class="form-control" name="adminUser.name" placeholder="用户名" required autofocus />
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="adminUser.password" placeholder="密码" required />
                </div>

                <div>
                    <s:property value="errorMessage"/>
                </div>

                <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
            </form>
        </div>
<script src="./js/jquery.js"></script>
<script src="./js/popper.js"></script>
<script src="./js/bootstrap.js"></script>
<script>
</script>
</body>
</html>
