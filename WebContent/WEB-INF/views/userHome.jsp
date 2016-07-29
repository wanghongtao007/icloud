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
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/sidebar.css" rel="stylesheet">
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="./css/ie10-viewport-bug-workaround.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="./css/jumbotron.css" rel="stylesheet">
<!-- iConCloud自定义CSS -->
<link href="./css/iconcloud.css" rel="stylesheet">
<script src="./js/ie-emulation-modes-warning.js"></script>

</head>

<body>

	<jsp:include page="navibar.jsp"></jsp:include>

	<!-- Create Project Modal -->
	<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
		style="margin-top: 100px;" id="createProjectModal"
		aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">新建项目</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="projectid">项目标识</label> <input type="text"
								class="form-control" id="projectid"
								placeholder="项目唯一标识，请输入小写英文字母">
						</div>
						<div class="form-group">
							<label for="projectName">项目名称</label> <input type="text"
								class="form-control" id="projectName" placeholder="项目名称">
						</div>
						<div class="form-group">
							<label for="projectDescription">项目描述</label>
							<textArea rows="2" class="form-control" id="projectDescription"
								placeholder="项目描述"></textArea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Create Project Modal End-->

<div class="container">
<div class="row">

 <div id="page-content-wrapper">
	<div class="container navbar-left">
		<div class="row">
			<div style="height: 20px;"></div>
		</div>
		<div class="row">
			<div class="col-md-3">
				<div class="container">				
					<div class="row">
						<div style="height: 20px;"></div>
					</div>
					<div class="row">
						<div class="col-md-3">
							<!-- User Info List -->
							<ul class="list-group">
								<li class="list-group-item"><span class="badge">20</span> <span
									class="icon_margin glyphicon glyphicon-inbox"></span>未读信息</li>
								<li class="list-group-item"><span class="badge">${model.projectsSize }</span>
									<span class="icon_margin glyphicon glyphicon-cloud"></span>容器项目</li>
								<li class="list-group-item"><span class="badge">8</span> <span
									class="icon_margin glyphicon glyphicon-flag"></span>容器服务</li>
								<li class="list-group-item"><span class="badge">0</span> <span
									class="icon_margin glyphicon glyphicon-bell"></span>系统警报</li>
							</ul>
							<!-- User Info List End-->
						</div>
					</div>
					<div class="row">
						<div class="col-md-3">
							<div class="list-group">
								<a href="#" class="list-group-item active"><span
									class="icon_margin glyphicon glyphicon-gift icon_margin"></span>热门镜像
								</a> <a href="#" class="list-group-item">JBoss EAP_6.2.4-RHEL6.4</a>
								<a href="#" class="list-group-item">JBoss Tomcat_7-RHEL6.4</a> <a
									href="#" class="list-group-item">MySQL_5.5-RHEL6.4</a> <a
									href="#" class="list-group-item">Redis_3.0-RHEL6.4</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="row">
					<div class="col-md-7">
						<h2>我的项目</h2>
					</div>
					<div class="col-md-2">
						<!-- Create Button -->
						<div class="btn-group setting_btn_padding">
							<button type="button"
								style="margin-top: 20px; margin-bottom: 10px;"
								class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
								<span class="icon_margin glyphicon glyphicon-plus"></span>创 建<span
									class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#" id="createProject">项目</a></li>
							</ul>
						</div>
						<!-- Create Button  End-->
					</div>
				</div>
				<div class="row">
					<div class="col-md-9">
						<!-- Project List -->
						<c:forEach var="p" items="${model.projects}">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										<span class="icon_margin glyphicon glyphicon-cloud"></span> <strong>${p.dispalyName}</strong>
									</h3>
									<div>
										<span class="label label-success"
											style="float: right; margin-top: -15px;">启用</span>
									</div>
								</div>
								<div class="panel-body">
									<div class="project_desc_container"
										style="display: inline; width: 500px;">${p.description}</div>
									<div style="display: inline;">
										<div class="btn-group navbar-right setting_btn_padding">
											<button type="button" class="btn btn-default dropdown-toggle"
												data-toggle="dropdown">
												<span class="icon_margin glyphicon glyphicon-cog"></span> 设置<span
													class="caret"></span>
											</button>
											<ul class="dropdown-menu" role="menu">
												<li><a href="projectDetail.page/${p.name}"><span
														class="icon_margin glyphicon glyphicon-zoom-in"></span>详细</a></li>
												<li><a href="#"><span
														class="icon_margin glyphicon glyphicon-pause"></span>停用</a></li>
												<li class="divider"></li>
												<li><a href="#"><span
														class="icon_margin glyphicon glyphicon-remove"></span>删除</a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
						<!-- Project List End -->
					</div>
				</div>
				<div class="container"></div>
			</div>
		</div>
	</div>
	<!-- /container -->
	</div>
	</div></div>
	<div class="container"></div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script>
		$(function() {
			$("#createProject").click(function() {
				$('#createProjectModal').modal('show');
			});
		});
	</script>

</body>
</html>