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

    <script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">

        $(function(){

            $("#coursetextbookpic").change(function(){
                var val=$(this).val();
                $("#textbookPic").attr("src",val);
            });

            $("#courseNo").change(function(){
                var val = $(this).val();
                val = $.trim(val);
                $(this).val(val);

                var url = "${pageContext.request.contextPath }/course/ajaxValidateCourseNo";
                var args = {"courseNo":val,"date":new Date()};

                $.post(url, args, function(data){
                    if(data == "0"){
                        $("#courseNo").parent().find("#error").remove();

                    }else if(data == "1"){
                        $("#courseNo").parent().append("<span id='error'><font color='red'>该 courseNo 不可用!</font></span>");
                    }else{
                        alert("网络或程序出错. ");
                    }
                },"text");

//                可以用，但记得带两边的网络获取方法要一样，不一样的要改
//                $.getJSON(url, args, function(data){
//
////                  alert(data.courseName);
//                    alert(data);
//
//                });

//                可以用，但记得带两边的网络获取方法要一样，不一样的要改
//                $.post(url, args, function(data){
//
//                    alert(data.courseName);
////                    alert(data);
//
//                },"json");

            });

        });

	</script>
		
  </head>
  
  <body style="padding:8px;">
    <h3 class="title">新增课程</h3>
    <img id="textbookPic" 
         alt="默认教材封面" 
         width="300"
         height="100"
         style="float:right" src='<c:url value="/pics/default.jpg"/>'><br/>
    <form:form action="${pageContext.request.contextPath}/course/create" method="post"  enctype="multipart/form-data" modelAttribute="course">
       <div>
         <span>课程编号:</span>
         <form:input id="courseNo" path="courseNo"/>
       </div>
       <div>
         <span>课程名称:</span> 
         <form:input path="courseName"/>
       </div>
       <div>
         <span>教材封面:</span> 
         <input id="coursetextbookpic" type="file" name="coursetextbookpic" size="40" />
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
       <c:if test="${!empty requestScope.exceptionMessage}">
        <div class="error">
            ${exceptionMessage}
        </div>
	  </c:if>	         
       <div>
         <input type="submit" value="开设课程"/>
       </div>              
    </form:form>
    <c:if test="${not empty errMsg}">
        <script type="text/javascript">
           alert('${errMsg}');
           document.forms[0].courseno.select();
        </script>
    </c:if>
    <!-- first visit, set form default value -->
    <c:if test="${empty errMsg}">
        <script type="text/javascript">
        
           with(document.forms[0]){
             coursestatus[0].checked=true;
             coursereqs[0].checked=true;
             coursereqs[3].checked=true;
           }
           
        </script>
    </c:if>    
  </body>
</html>
