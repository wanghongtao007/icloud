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
			<div class="col-md-2"></div>
			<div class="col-md-6">
			<div class="text-center" style="font-family: 'microsoft yahei',Arial,sans-serif;">
				<h3>
				应用服务镜像: ${model.template.name}
				</h3>
				<hr size="1" width="100%">
			</div>
				<form class="form-horizontal">
					<c:forEach var="p" items="${model.template.parameters}">
						<div class="form-group">
							<label class="col-sm-2 control-label">参数名</label>
							<div class="col-sm-10">
								<p class="form-control-static">${p.name}</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10">
								<p class="form-control-static">${p.description}</p>
							</div>
						</div>
						<div class="form-group">
							<label for="value-${p.name}" class="col-sm-2 control-label">参数值</label>
							<div class="col-sm-10">
								<input type="text" id="value-${p.name}" class="form-control"
									placeholder="请输入选项值" value="${p.value}">
							</div>
						</div>
						<hr size="1" width="95%">

					</c:forEach>
				</form>
			</div>
		</div>

		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-6">
				<a class="btn btn-primary btn-lg btn-block"
					href="/iconcloud/${model.projectName}/createApplication.action/${model.template.name}"
					role="button">创建</a>
			</div>
		</div>

	</div>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
