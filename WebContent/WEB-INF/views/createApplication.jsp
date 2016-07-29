<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>欢迎使用容器云</title>

<!-- Bootstrap core CSS -->
<link href='<c:url value="/css/bootstrap.min.css"/>' rel="stylesheet">
<link href='<c:url value="/css/iconcloud.css"/>' rel="stylesheet">
<link href='<c:url value="/css/ie10-viewport-bug-workaround.css"/>'
	rel="stylesheet">
<link href='<c:url value="/css/sidebar.css"/>' rel="stylesheet">
<script src='<c:url value="/js/ie-emulation-modes-warning.js"/>'></script>

</head>

<body>
	<jsp:include page="navibar.jsp"></jsp:include>



	<div class="container">
		<div class="row">

			<div class="header_margin"></div>
			<h3 class="title">请选择所需应用服务</h3>
			<div >
			<c:forEach var="t" items="${model.templates}">
				<div class="panel panel-default" style="float: left;width:250px;height:250px;margin:2px;">
					
					<div class="panel-body" style="height:160px;"><center>
					<div>
						<img style="width:80px; height:80px;margin:10px;" src="../../images/app-service.png"/>
						</div>
						<div class="project_desc_container"
							style="display: inline; width: 250px;"><h4>${t.name}</h4></div><center>
					</div>
					<center>
					<a class="btn btn-primary btn-lg"  href="../../${model.projectName}/createApplicationDetail.page/${t.name}" role="button" style="width:200px;"><span class="glyphicon glyphicon-circle-arrow-down btnIcon"></span>选择</a>
					</center>
				</div>
			</c:forEach>
</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
