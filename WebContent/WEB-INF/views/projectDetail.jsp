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
<script src='<c:url value="/js/spin.min.js"/>'></script>
<style type="text/css">


</style>
</head>

<body>
	<jsp:include page="navibar.jsp"></jsp:include>


	
	<!-- Start Application Detail Dialog -->
	<div class="modal fade" tabindex="-1" role="dialog"
		style="margin-top: 100px;" id="appSvcDetailDialog"
		aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">Close</span>
					</button>
					<h4 id="deploymentConfigOverviewTitle" class="modal-title">应用服务</h4>
				</div>
				<div class="modal-body">
				
					<ul class="nav nav-tabs" role="tablist">
			      		<li role="presentation" class="active"><a href="#overview"
							aria-controls="overview" role="tab" data-toggle="tab"><span class="icon_margin glyphicon glyphicon-plane btn-sm"></span>应用概览</a></li>
						<li role="presentation" ><a href="#containers"  onclick="getProjectPods();"
							aria-controls="containers" role="tab" data-toggle="tab"><span class="icon_margin glyphicon glyphicon-dashboard btn-sm"></span>容器实例</a></li>
					</ul>
					<div class="header_margin"></div>
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active overview"  id="overview">
							<form class="form-horizontal">
							  <div class="form-group">
							    <label class="col-sm-2 control-label">服务名称</label>
							    <div class="col-sm-10">
							      <p class="form-control-static" id="as_name">#NAME</p>
							    </div>
							  </div>
							 <div class="form-group">
							    <label class="col-sm-2 control-label" >容器镜像</label>
							    <div class="col-sm-10">
							      <p class="form-control-static" id="as_image">#IMAGE</p>
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">容器实例数</label>
							    <div class="col-sm-10">
							      <p class="form-control-static"  id="as_replicas">#CONTAINER_NUMBER</p>
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label" >服务访问点</label>
							    <div class="col-sm-10"  id="as_endpoint">
							      
							    </div>
							  </div>
							  <button type="button" class="btn btn-default" data-dismiss="modal">重启服务</button>
							  <button type="button" class="btn btn-default" data-dismiss="modal">删除服务</button>
							</form>
						</div>
						<div role="tabpanel" class="tab-pane containers" id="containers">		
							<div class="row">
								<div class="col-md-12">
								
									<table class="table table-striped  " id="podList">
										<tr>
											<th>容器名称</th>
											<th>网络地址</th>
											<th>创建时间</th>
											<th>状态</th>
											<th>类型</th>
											<th>操作</th>
										</tr>
											<tr id="podListTemplate" class="hide">
												<td><strong id="NAME">#NAME</strong></td>
												<td id="IP">#IP</td>
												<td id="START_TIME">#START_TIME</td>
												<td><span id="STATUS">#STATUS</span>
												</td>
												<td id="IS_APP_POD">#IS_APP_POD</td>
												<td>
													<div class="btn-group setting_btn_padding">
														<button type="button"
															class="btn btn-primary dropdown-toggle btn-sm"
															data-toggle="dropdown">
															<span class="icon_margin glyphicon glyphicon-cog"></span><span
																class="caret"></span>
														</button>
														<ul class="dropdown-menu" role="menu">
															<li><a href="#" id="link"
																class="jsPodOutputBtn" data-pod-name="#NAME"
																data-pod-ns="#PROJECT_NAME" onclick="getPodOutput();">
																<span class="icon_margin glyphicon glyphicon-zoom-in btn-sm"
																	data-pod-name="#NAME"
																data-pod-ns="#PROJECT_NAME"></span>详细</a></li>
															<li><a href="#"><span
																	class="icon_margin glyphicon glyphicon-remove btn-sm"></span>删除</a></li>
														</ul>
													</div>
												</td>
											</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- End Application Detail Dialog -->
	
		<div class="modal fade" tabindex="-1" role="dialog"
		style="margin-top: 100px;" id="podOutputModal"
		aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">Close</span>
					</button>
					<h4 id="podOutputModalTitle" class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<textarea wrap="hard" readonly="readonly" spellcheck="false"
								id="podOutputScreen-ta" class="podOutputScreen" rows="10"></textarea>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Create Project Modal End-->
	
	<div class="container">
<div class="row">
       <div class="header_margin"></div>
       <h3 class="title">我的项目：${model.projectName}</h3>
       <ul class="nav nav-tabs" role="tablist">
      		<li role="presentation" class="active"><a href="#create"
				aria-controls="create" role="tab" data-toggle="tab"><span class="icon_margin glyphicon glyphicon-plane btn-sm"></span>创建应用</a></li>			
		</ul>
	<div class="header_margin"></div>
	<div class="tab-content">
	<div role="tabpanel" class="tab-pane active container"  id="create">
		<div class="row">
		<div class="col-md-2">
		</div>
			<div class="col-md-8">
				<a class="btn btn-primary btn-lg btn-block glyphicon glyphicon-plus" href="../${model.projectName}/createApplication.page/" role="button">创建应用服务</a>
			</div>
			<div class="col-md-2">
		</div>
		</div>
		<br>
		<div class="row">
		<div class="col-md-2">
		</div>
			<div class="col-md-8">
			 <div style="margin-right: auto;margin-left:auto;" id="applicationList">
				<div class="text-center hide" style="float:left;width:200px; height:150px;margin:20px;" id="applicationListTemplate" >
  					<div><a href="#" onclick="openAppSvcDetail();"><img src="<c:url value='/'/>/images/CloudApp.png" style="width:120px; height:120px;"/></a></div>
  					<span class="glyphicon glyphicon glyphicon-th-large "></span>
					应用服务 <h4 id="NAME"></h4>
				</div>
			</div>
			</div>
			<div class="col-md-2">
		</div>
		</div>
	</div>
	<div role="tabpanel" class="tab-pane" id="profile">...</div>
    <div role="tabpanel" class="tab-pane" id="messages">...</div>
    <div role="tabpanel" class="tab-pane" id="settings">...</div>
  </div>
   </div>
   </div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script>
	
	//projectDetail Tab
	//---------------------------------------------------------------
	var uidata = {};
	
	$('#myTabs a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    })

    //podOutput Dialog
    //---------------------------------------------------------------
	function getPodOutput(e) {
		var t = $(event.target);
		var podName = t.data('pod-name');
		var podNamespace = t.data('pod-ns');

		$.ajax({
			type : 'GET',
			url : '<c:url value="/objects.svc/"/>'
					+ podNamespace + '/log/' + podName,
			timeout : 2000,
			beforeSend : function() {
				$('#podOutputScreen-ta').text('');
				$('#podOutputModal').modal('show');
			},
			success : function(data) {

				$('#podOutputModalTitle').html(
						'<h5>容器<em>[' + podName
								+ ']</em>日志输出</h5>');
				$('#podOutputScreen-ta').text(data);
			},
			complete : function() {

			}
		});
	}

	//---------------------------------------------------------------
	function getProjetDeploymentConfigs() {
		var projectName = '${model.projectName}';
		
		$.ajax({
			type : 'GET',
			url : '<c:url value="/objects.svc/"/>'
					+ projectName + '/deploymentConfig/',
			timeout : 2000,
			beforeSend : function() {
				var spinner = new Spinner().spin()
				$('#applicationList').append(spinner.el)
			},
			success : function(data) {
				$("#applicationList div.xxcontentxx").remove();
				
                json =  $.parseJSON(data);
                uidata.dc = json;                  

                   $.each(json.items, function(i, item) {
                       var row = $('#applicationListTemplate').clone().attr("class","text-center xxcontentxx");
                       row.find('[ id = NAME]').text(item.metadata.name);
               
                       $("#applicationList").append(row);
                    });
                   
			},
			complete : function() {
				$('#applicationList div.spinner').remove();
			}
		});
	}

	//---------------------------------------------------------------
	function getProjectPods() {
		var projectName = '${model.projectName}';
		
		$.ajax({
			type : 'GET',
			url : '<c:url value="/objects.svc/"/>'
					+ projectName + '/pod/',
			timeout : 2000,
			beforeSend : function() {
				var spinner = new Spinner().spin();
				$('#podList').append(spinner.el);
			},
			success : function(data) {
                //Reset
                $("#podList tr.xxcontentxx").remove();

				//Create rows
                json =  $.parseJSON(data);
                $.each(json.items, function(i, item) {
                   var row = $('#podListTemplate').clone().attr("class","xxcontentxx");
                   row.find('[ id = NAME]').text(item.metadata.name);
                   row.find('[ id = IP]').text(item.status.podIP == null ? '':item.status.podIP);
                   row.find('[ id = START_TIME]').text(item.metadata.creationTimestamp);
                   row.find('[ id = link]').attr("data-pod-name", item.metadata.name);
                   row.find('[ id = link]').attr("data-pod-ns", item.metadata.namespace);
                   row.find('span').attr("data-pod-name", item.metadata.name);
                   row.find('span').attr("data-pod-ns", item.metadata.namespace);
                   
                   var status = item.status.phase;
                   var status_str = status=='Running'?'活动':status=='Failed'?'失败':status=='Succeeded'?'完成':status=='Pending'?'准备':'未知';
                   var status_label = "label label-" + (status=='Running'?'info':status=='Failed'?'danger':status=='Pending'?'warning':'success')
                   row.find('[ id = STATUS]').text(status_str).attr('class', status_label);
                   
                   $("#podList").append(row);
                });
			},
			complete : function() {
				$('#podList div.spinner').remove();
			}
		});
	}

	function getProjectServices() {
		var projectName = '${model.projectName}';
		
		$.ajax({
			type : 'GET',
			url : '<c:url value="/objects.svc/"/>' + projectName + '/service/',
			timeout : 2000,
			async:false,
			beforeSend : function() {
			},
			success : function(data) {			
                uidata.services =  $.parseJSON(data);
			},
			complete : function() {
			}
		});
	}
	
	//---------------------------------------------------------------
	function openAppSvcDetail(){
		var t = $(event.target);
		
		$('#appSvcDetailDialog').modal('show');
		var appSvc = t.parent().parent().parent().children('h4').text();

		var svc = {};
		svc.name = appSvc;
        $.each(json.items, function(i, item) { if (item.metadata.name = appSvc) svc.dc = item});
        svc.image = svc.dc.spec.template.spec.containers[0].image;
        svc.replicas = svc.dc.spec.replicas;

        $('#appSvcDetailDialog').find('#as_name').text(svc.name);
        $('#appSvcDetailDialog').find('#as_image').text(svc.image);
        $('#appSvcDetailDialog').find('#as_replicas').text(svc.replicas);

        getProjectServices();
        
        $.each(uidata.services.items, function(i, item) { if (item.metadata.name = svc.name) svc.service = item});

        if( svc.service.spec.type == 'NodePort'){
        	$.each(svc.service.spec.ports, function(p, port) {
        		 $('#appSvcDetailDialog').find('#as_endpoint').append('<p class="form-control-static">'+port.nodePort + '</p>');
            });
        }
       
        
       
		
	}
	
	$(function() {
        getProjetDeploymentConfigs();
	});
	</script>
</body>
</html>
