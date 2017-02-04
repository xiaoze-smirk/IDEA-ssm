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
	<script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
	   
	   function removeCourseType(typeId,typeName){
		      
	      if(confirm("确定要删除 ["+typeName+"] 课程类型信息吗?")){
	       
	    	  var formObject = document.createElement('form');   
    	      document.body.appendChild(formObject);   
    	      formObject.setAttribute('method', 'post');   
    	      var url = "${pageContext.request.contextPath}/courseType/remove/"+typeId;
    	      formObject.action = url; 
    	                     
    	      var inputObject = document.createElement('input');   
    	      inputObject.setAttribute('type', 'hidden');   
    	      inputObject.setAttribute('name', '_method');   
    	      inputObject.setAttribute('value', 'delete');   
    	      formObject.appendChild(inputObject);   

    	               
    	      formObject.submit();   
    	               
    	      return false;
	      }
	   }
	   
	   function updateCourseType(typeId){
	      
          location.href="${pageContext.request.contextPath}/courseType/preUpdate/"+typeId;

	   }
	 
	</script>
	
  </head>
  
  <body style="padding:8px;">
    <h3 class="title">课程类型管理</h3>

	    <table border="0" cellspacing="0">
	       <tr>
	        <th>编号</th>
	        <th>名称</th>
	        <th>操作</th>
	       </tr>
	       <c:forEach var="courseType" items="${requestScope.courseTypeList}" >
	         <tr>
	          <td nowrap>${courseType.typeId}</td>
	          <td nowrap>${courseType.typeName}</td>
	          <td nowrap>
	          	  <button onclick="updateCourseType(${courseType.typeId})">修改</button>
	          	  <button onclick="removeCourseType(${courseType.typeId},'${courseType.typeName}')">删除</button>
	          </td>
	         </tr>   
	       </c:forEach>
	       <tr>
              <td> 
                <c:forEach begin="1" end="${requestScope.typePage.allpage}" var="index" >
                  <a href="${pageContext.request.contextPath}/courseType/list/${index}">${index}</a>&nbsp;
                </c:forEach>
              </td>
              <td></td>
              <td>总页数:${typePage.allpage}</td>
           </tr>   	       
	    </table>  
  </body>
</html>
