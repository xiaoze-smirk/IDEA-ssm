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
	   
	     function $(id){
	        return document.getElementById(id);
	     }
	
	</script>
	
  </head>
  
  <body style="padding:8px;">
    <h3 class="title">修改 [${course.courseName}] 课程信息</h3>
    <img id="textbookPic" 
         alt="默认教材封面" 
         width="300"
         height="100"
         style="float:right" src='<c:url value="/course/getPic/${course.courseNo}"/>'><br/>
    <form:form action="${pageContext.request.contextPath}/course/update" method="post" enctype="multipart/form-data" modelAttribute="course">
       <form:hidden path="courseNo" />
       <div>
         <span>课程编号: ${courseNo}</span>
       </div>
       <div>
         <span>课程名称:</span> 
         <form:input path="courseName"/>
       </div>
       <div>
         <span>教材封面:</span> 
         <input type="file" name="coursetextbookpic" size="40"
                onchange="$('textbookPic').src=this.value"/>
       </div>       
       <div>
         <span>课程课时:</span> 
         <form:input path="courseHours"/>
       </div> 
       <div>
         <span>课程学分:</span> 
         <form:input path="coursePoint"/>
       </div>
       <div>
         <span>课程类型:</span> 
         <form:select path="courseType.typeId">  
           <option value="">=请选择=</option>
           <form:options items="${courseTypeList}" itemLabel="typeName" itemValue="typeId" />  
         </form:select>
       </div>
       <div>
          <span>课程状态:</span>
           <% 
            Map<String, String> status = new HashMap<String, String>();
            status.put("O", "开放公选");
            status.put("Z", "暂不开放");
            status.put("C", "停止授课");
			
			request.setAttribute("status", status);
		  %>
		  <form:radiobuttons  path="courseStatus" items="${status}"/>
       </div>
       <div>
          <span>选课条件:</span>
          <% 
            Map<String, String> creqs = new HashMap<String, String>();
            creqs.put("a", "大三以上");
            creqs.put("b", "平均成绩80分");
            creqs.put("c", "党员");
            creqs.put("d", "未拖欠学费");
			
			request.setAttribute("creqs", creqs);
		  %>       
		  <form:checkboxes path="courseReqs" items="${creqs}"/>
       </div>  
       <div>
          <span>备注说明:</span>
          <form:textarea path="courseMemo" rows="6" cols="60" />
       </div>             
       <div>
         <input type="submit" value="修改课程信息"/>
       </div>              
    </form:form>
  </body>
</html>
