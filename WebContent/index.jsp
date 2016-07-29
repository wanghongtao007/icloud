<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %> 
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

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="./css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./css/jumbotron.css" rel="stylesheet">
    
    <!-- iConCloud自定义CSS -->
    <link href="./css/iconcloud.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="./js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script src="./js/jquery.min.js"></script>

  </head>

  <body>
<div class='notifications top-left'></div>
   <jsp:include page="WEB-INF/views/navibar.jsp"></jsp:include>

    <!-- Main jumbotron for a primary marketing message or call to action
    <div class="jumbotron">
      <p style="height: 100px;"/><p/>
      <div class="container">
        <h1 style="text-shadow: 1px 1px #FFFFFF;">容器云时代</h1>
        <p style="text-shadow: 1px 1px #FFFFFF;">iConCloud容器云所提供的丰富应用服务即用即得，使得应用开发速度变得前所未有地迅速。通过容器云实现快速应用部署，并实现弹性扩展!研发、测试及运维全流程贯通，全面提升企业IT效率！加速创新，提升企业竞争力！</p>
        <p><a class="btn btn-primary btn-lg" href="#" role="button">马上启用！ &raquo;</a></p>
      </div>
    </div> -->
   
<!-- Carousel
    ================================================== -->
    <link href="./css/carousel.css" rel="stylesheet">
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
      </ol>
      <div class="carousel-inner" role="listbox">
        <div class="item active" >
          <img class="first-slide" src="./images/cloud.jpg" alt="First slide">
          <div class="container">
            <div class="carousel-caption">
              <h1 style="text-shadow: 1px 1px #000000;">容器云时代的应用开发革命</h1>
              <p style="text-shadow: 1px 1px #000000;">iConCloud容器云所提供的丰富应用服务即用即得，使得应用开发速度变得前所未有地迅速。通过容器云实现快速应用部署，并实现弹性扩展!研发、测试及运维全流程贯通，全面提升企业IT效率！加速创新，提升企业竞争力！</p>
              <p><a class="btn btn-lg btn-primary" href="#" role="button">马上启用</a></p>
            </div>
          </div>
        </div>
        <div class="item">
          <img class="second-slide" src="./images/ship-s.jpg" alt="Second slide">
          <div class="container">
            <div class="carousel-caption">
              <h1 style="text-shadow: 1px 1px #000000;">容器云全面推进DevOps变革</h1>
              <p style="text-shadow: 1px 1px #000000;">iConCloud容器云所提供的丰富应用服务即用即得，使得应用开发速度变得前所未有地迅速。通过容器云实现快速应用部署，并实现弹性扩展!研发、测试及运维全流程贯通，全面提升企业IT效率！加速创新，提升企业竞争力！</p>
              <p><a class="btn btn-lg btn-primary" href="#" role="button">马上启用</a></p>
            </div>
          </div>
        </div>
        
      </div>
      <!-- <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a> -->
    </div><!-- /.carousel -->

    <div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-4">
          
        <h2>敏捷开发</h2>
          <p>iConCloud容器云为应用开发提供各种所需要服务，如应用服务器、数据库、分布式缓存、消息队列等。所有资源即用即得，无需繁琐的配置与等待。</p>
          <p><a class="btn btn-default" href="#" role="button">详细了解 &raquo;</a></p>
        </div>
        <div class="col-md-4">
          <h2>弹性伸缩</h2>
          <p>iConCloud容器云通过智能容器集群编排技术，实现应用运行实例的智能弹性伸缩，保证应用服务总是运行在足够的资源之上，再也不必为资源扩容而烦恼了。</p>
          <p><a class="btn btn-default" href="#" role="button">详细了解 &raquo;</a></p>
       </div>
        <div class="col-md-4">
          <h2>快速上线</h2>
          <p>iConCloud容器云基于容器的应用发布，实现发布流程的高度自动化，全面提供工作效率。内置多种应用发布和更新策略，轻松实现灰度发布及滚动更新。</p>
          <p><a class="btn btn-default" href="#" role="button">详细了解 &raquo;</a></p>
        </div>
      </div>

     
    </div> <!-- /container -->
   <jsp:include page="WEB-INF/views/footer.jsp"></jsp:include> 
  </body>
</html>
