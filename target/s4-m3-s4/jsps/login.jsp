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
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"></c:url>">
    
    <style>

		#wrapper{ 
		  margin:10px;  
		}
		
		#f_title{
		  font-size:14px;
		  font-weight:bold;
		  width:100%;
		  border-bottom:1px solid gray; 
		  padding-border:2px;
		}
		
		.f_row{
		  margin-top:8px;
		}
		
		.f_row span,.f_row input{
		  font-size:12px;
		}    
    
      .error{
        border:1px solid yellow;
        color:red;
        width:180px;
        padding:8px;
        margin:5px;
      }  
    </style>
    
  </head>
  
  <body>
    <div id="wrapper">
	    <div id="f_title"><fmt:message key="user.login"/></div>
	    <form:form action="${pageContext.request.contextPath}/security/login" method="post" modelAttribute="user">
	        <div class="f_row">
	          <span>用户编号:</span>
	          <form:input path="userNo" />
	          <form:errors path="userNo" />
	          <c:if test="${!empty requestScope.userError}">
		          ${requestScope.userError}
	          </c:if>
	        </div>
	        <div class="f_row">
	          <span>登录密码:</span>
	          <form:password path="userPwd"/>   
	          <form:errors path="userPwd" />
	          <c:if test="${!empty requestScope.userPwdError}">
		          ${requestScope.userPwdError}
	          </c:if>     
	        </div>
	        <c:if test="${!empty requestScope.exceptionMessage}">
		        <div class="error">
		            ${exceptionMessage}
		        </div>
	        </c:if>	
	        <div class="f_row">
	          <input type="submit" value=" 登 录 "/>      
	        </div>                        
	    </form:form>
    </div>
    <%@ include file="/jsps/footer.jsp"%>
  </body>
</html>
