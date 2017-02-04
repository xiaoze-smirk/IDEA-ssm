<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsps/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">	
	<style>
	   
	   .errorMessage{
	      list-style:none;
	      color:red;
	      display:inline;
	   }
	   
	   .errorMessage li{
	      display:inline-block;
	   }
	   
	   
	</style>
  </head>
  
  <body style="padding:8px;">
    <form:form action="${pageContext.request.contextPath}/courseType/update" method="post" modelAttribute="courseType">
    <h3 class="title">修改课程类型-${courseType.typeName}</h3>
       <input type="hidden" name="_method" value="PUT"/>
       <form:hidden path="typeId"/>
       <div>
         <span>课程类型名称:</span> 
         <form:input path="typeName"/>
       </div>             
       <div>
         <input type="submit" value=" 登 录 "/> 
       </div>              
    </form:form>
  </body>
</html>
