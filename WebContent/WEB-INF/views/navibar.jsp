<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href='<c:url value="/index.jsp"/>'>OpenConCloud容器云</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href='<c:url value="/userHome.page"/>'><span
							class="icon_margin glyphicon glyphicon-home"></span>我的容器云</a></li>
							<li><a href='<c:url value="#"/>'><span class="icon_margin glyphicon glyphicon-gift"></span>镜像市场</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><span
							class="icon_margin glyphicon glyphicon-question-sign"></span>帮助中心
							<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">容器学堂</a></li>
							<li><a href="#">新手上路</a></li>
							<li><a href="#">常见问题</a></li>
						</ul></li>
					<li class=""><a href="#"><span
							class="icon_margin glyphicon glyphicon-info-sign"></span>关于iConCloud</a></li>
				</ul>
				<form
					class="navbar-form navbar-right ${sessionScope['CLOUD-AUTHENTICATION']=='AUTHENTICATED'?'hidden':'show'}"
					action="login.action">
					<div class="form-group">
						<input id="username" name="uname" type="text" placeholder="用户名"
							class="form-control">
					</div>
					<div class="form-group">
						<input id="password" name="upwd" type="password" placeholder="密码"
							class="form-control">
					</div>
					<button type="submit" class="btn btn-success">登录</button>
				</form>

				<ul
					class="nav navbar-nav navbar-right ${sessionScope['CLOUD-AUTHENTICATION']=='AUTHENTICATED'?'show':'hidden'}">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><span
							class="icon_margin glyphicon glyphicon-user"></span>${sessionScope['USER_NAME']}
							<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">用户设置</a></li>
						</ul></li>
					<li class="active"><a href='<c:url value="/logout.action"/>'>登出</a></li>
				</ul>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</nav>
	<!-- Nav End -->