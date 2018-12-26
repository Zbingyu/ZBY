<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 

欢迎<shiro:principal></shiro:principal><a href="${pageContext.request.contextPath}/logout">注销</a>    <br>
<hr>
<a href="${pageContext.request.contextPath}/addStudent">增加学生</a>    <br>
<a href="${pageContext.request.contextPath}/showStudent">显示学生</a>  <br>