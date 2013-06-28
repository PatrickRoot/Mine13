<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'test.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>test
	<%
		if (request.getAttribute("topContent") == null)
		{
	%>未查询到任何内容，请更改查询时间
	<s:a href="index.jsp"> 首页 </s:a>
	<%
		} else
		{
	%>
	<s:iterator value="#request.topContent" id="tm">

		<div>
			<s:a href="http://t.qq.com/%{#tm.username}">
				<s:property value="#tm.usernick" />
			</s:a>
			:
			<s:property value="#tm.content" />
			<br>
			<s:property value="#tm.posttime" />
			<br>
			<s:a href="http://t.qq.com/%{#tm.contentid}">评论(<s:property
					value="#tm.mcount" />)</s:a>
			<s:a href="http://t.qq.com/p/t/%{#tm.contentid}">转发(<s:property
					value="#tm.count" />)</s:a>
		</div>

		<!--  		<tr>
				<td><s:property value="#tm.id" /></td>
				<td><s:property value="#tm.usernick" /></td>
				<td><s:property value="#tm.username" /></td>
				<td><s:property value="#tm.content" /></td>
				<td><s:property value="#tm.contentid" /></td>
				<td><s:property value="#tm.count" /></td>
				<td><s:property value="#tm.mcount" /></td>
				<td><s:property value="#tm.posttime" /></td>
			</tr>
		-->
	</s:iterator>
	<%
		}
	%>
</body>
</html>
