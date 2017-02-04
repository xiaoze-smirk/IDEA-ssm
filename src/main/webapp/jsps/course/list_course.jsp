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
	
	<script type="text/javascript">
	   
	   function removeCourse(courseNo,courseName){
	      
	      if(confirm("确定要删除 ["+courseName+"] 课程信息吗?")){
	          
	   	      var formObject = document.createElement('form');   
	   	      document.body.appendChild(formObject);   
	   	      formObject.setAttribute('method', 'post');   
	   	      var url = "${pageContext.request.contextPath}/course/remove/"+courseNo;
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
	   
	   function updateCourse(courseNo){
	        location.href="${pageContext.request.contextPath}/course/preUpdate/"+courseNo;
	   }
	   
	   function $(id){
	      return document.getElementById(id);
	   }
	   
       function doQuery(pageno,totalPageNum)
       {
           var re = /^[0-9]+.?[0-9]*$/;
           
           if(!re.test(pageno))
           {
              alert("输入的不是数字!")
              return;
           }
           
           if(pageno<1 || pageno>totalPageNum)
           {
              alert("页号超出范围，有效范围：[1-"+totalPageNum+"]!");
              return;
           }
           
           var f=document.forms[0];
           f.action=f.action+"/"+pageno;
           f.submit();
           
       }	   
	
	</script>
	
  </head>
  
  <body style="padding:8px;">
    <h3 class="title">课程管理</h3>
    <div id="queryArea">
      <form:form action="${pageContext.request.contextPath}/course/list" method="post" modelAttribute="helper">
                      课程名称：<form:input path="qryCourseName" />&nbsp;&nbsp;
                      学分范围：<form:input path="qryStartPoint" size="6" /> - <form:input path="qryEndPoint" size="6" />&nbsp;&nbsp;
                      课程类型： 
         <form:select path="qryCourseType">  
           <option value="">=请选择=</option>
           <form:options items="${courseTypeList}" itemLabel="typeName" itemValue="typeId" />  
         </form:select>
         <input type="submit" value="查询"/>
      </form:form>
    </div>
    <c:if test="${!empty page.pageContent}">
       <table border="0" cellspacing="0">
	       <tr>
	        <th>序号</th>
	        <th>编号</th>
	        <th>名称</th>
	        <th>课时</th>
	        <th>学分</th>
	        <th>类型</th>
	        <th>状态</th>
	        <th>选课要求</th>
	        <th>备注</th>
	        <th>操作</th>
	       </tr>
	       <c:forEach var="course" items="${page.pageContent}" varStatus="idx">
	       <tr>
	         <td nowrap>${idx.index+1}</td>
	         <td nowrap>${course.courseNo}</td>
	         <td nowrap style="padding-top:10px;">
	            ${course.courseName}<br>
	            <img width="100" height="50" alt="${course.courseName}的教材" src="<c:url value="/course/getPic/${course.courseNo}"/>">   
	         </td>
	         <td nowrap>${course.courseHours}</td>
	         <td nowrap>${course.coursePoint}</td>
	         <td nowrap>           
	           ${course.courseType.typeName}
	         </td>
	         <td nowrap>
	           <c:choose>
	              <c:when test="${course.courseStatus=='O'}">开放公选</c:when>
	              <c:when test="${course.courseStatus=='Z'}">暂不开放</c:when>
	              <c:when test="${course.courseStatus=='C'}">停止授课</c:when>
	           </c:choose>         
	         </td>
	         <td nowrap>
	            <c:forEach var="req" items="${course.courseReqs}">
		           <c:choose>
		              <c:when test="${req=='a'}">大三以上</c:when>
		              <c:when test="${req=='b'}">平均成绩80分</c:when>
		              <c:when test="${req=='c'}">党员</c:when>
		              <c:when test="${req=='d'}">未拖欠学费</c:when>
		           </c:choose>
	            </c:forEach>
	         </td>
	         <td nowrap>${course.courseMemo}</td>
	         <td>
	           <button onclick="updateCourse('${course.courseNo}')">修改</button>
	           <button onclick="removeCourse('${course.courseNo}','${course.courseName}')">删除</button>
	         </td>
	       </tr>
	       </c:forEach>
	    </table>
	    <div id="pageinfo">
	            共${page.totalRecNum}条, 当前显示${page.startIndex+1}-${page.endIndex+1}条, 第${page.pageNo}/${page.totalPageNum}页
	           |
	           <c:if test="${page.pageNo>1}">
	             <span class="linkspan" onclick="doQuery(1,${page.totalPageNum})">首页</span>&nbsp;
	           </c:if>
	           <c:if test="${page.prePage}">
	             <span class="linkspan" onclick="doQuery(${page.pageNo-1},${page.totalPageNum})">上一页</span>&nbsp;
	           </c:if>
	           <c:if test="${page.nextPage}">
	             <span class="linkspan" onclick="doQuery(${page.pageNo+1},${page.totalPageNum})">下一页</span>&nbsp;
	           </c:if>
	           <c:if test="${page.pageNo!=page.totalPageNum}">
	             <span class="linkspan" onclick="doQuery(${page.totalPageNum},${page.totalPageNum})">末页</span>&nbsp;
	           </c:if>
	           |
	            到<input type="text" id="pageNo" size=4 style="text-align:right;" onkeypress="onlynumber();"/> 页
	           <button onclick="doQuery($('pageNo').value,${page.totalPageNum});"> 跳 转 </button>		           		           	           	              
	        </div>
    </c:if>
     <c:if test="${empty page.pageContent}">
       <div class="warnMsg">
         没有符合条件的课程信息被找到，请检查!
       </div>
    </c:if>  
  </body>
</html>
