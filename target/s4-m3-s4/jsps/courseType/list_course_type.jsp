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
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">

		$(function(){
//           //动态创建form和input，方法可行，但是不推荐
//			$(".delete").click(function(){
//				var href = $(this).attr("href");
//				var typeName=$(this).parents(".type").children("#typeName").text();
//				if(confirm("确定要删除 ["+typeName+"] 课程类型信息吗?")){
//					$("<form action=''  method='POST'></form>").appendTo($("body"));
//					$("<input type='hidden' name='_method' value='DELETE'/>").appendTo($("form"));
//					$("form").attr("action", href).submit();
//					return false;
//				}
//
//			});

			$(".delete").click(function(){
				var href = $(this).attr("href");
				var typeName=$(this).parents(".type").children("#typeName").text();
				if(confirm("确定要删除 ["+typeName+"] 课程类型信息吗?")){
					$("form").attr("action", href).submit();
					return false;
				}

			});

			$(".update").click(function(){
				var href = $(this).attr("href");
				location.href(href);
			});
		});

	</script>

</head>

<body style="padding:8px;">
<h3 class="title">课程类型管理</h3>

	<form action="" method="POST">
		<input type="hidden" name="_method" value="DELETE"/>
	</form>

	<table border="0" cellspacing="0">
		<tr>
			<th>编号</th>
			<th>名称</th>
			<th>操作</th>
		</tr>
		<c:forEach var="courseType" items="${list}" >
			<tr class="type">
				<td nowrap>${courseType.typeId}</td>
				<td id="typeName" nowrap>${courseType.typeName}</td>
				<td nowrap>
					<button class="update" href="${pageContext.request.contextPath}/courseType/preUpdate/${courseType.typeId}" >修改</button>
					<button class="delete" href="${pageContext.request.contextPath}/courseType/remove/${courseType.typeId}">删除</button>
				</td>
			</tr>
		</c:forEach>

	</table>
	<div id="pageinfo">
		${pageLinks}
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
</body>
</html>
