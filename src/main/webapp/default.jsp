<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh"   content="10; url=/myweb/login"/>
<title>This is a JSP File - default.jsp</title>

<link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
<script language="text/script" src="/js/bootstrap.min.js" ></script>

</head>
<body>
	<h1>This is a test on default.Jsp</h1>

	<h3> ===============这是一个关于使用SpringMVC注解的程序。================</h3>
   		现在时间是：<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) %>
</body>
</html>