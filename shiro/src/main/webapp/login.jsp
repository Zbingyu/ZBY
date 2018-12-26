<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<form action="${pageContext.request.contextPath }/login.do"  method="post">
    用户名:<input type=text name="username" /> <br>
    密码:<input type=password name="password" /> <br>
    <button type="submit">登录</button>
</form>