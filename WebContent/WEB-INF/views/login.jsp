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

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="./js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <!-- For login page -->
    <link href="./css/signin.css" rel="stylesheet">
  </head>

  <body>
   
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index.jsp">iConCloud容器云</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
            <li class="active"><a href="userHome.page">我的容器云</a></li>
             <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">帮助中心 <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">容器学堂</a></li>
                <li><a href="#">新手上路</a></li>
                <li><a href="#">常见问题</a></li>
              </ul>
            </li>
            <li class="active"><a href="#">关于iConCloud</a></li>
        </ul>
         
        </div><!--/.navbar-collapse -->
      </div>
    </nav>

                   
    <!-- Custom styles for this template -->
<div class="container" >

      <div class="alert alert-danger alert-dismissable ${(errorMessage==null)?'hidden':'show'}" style="margin-top:10px;"> 
          <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button> 
          <strong>噢！</strong> ${errorMessage}
      </div> 

      <form class="form-signin ${sessionScope['CLOUD-AUTHENTICATION']=='AUTHENTICATED'?'hidden':'show'}" action="login.action" method="post" >
        <h2 class="form-signin-heading">请登录</h2>
        <label for="uname" class="sr-only">用户名</label>
        <input type="text" name="uname" id="uname" class="form-control" placeholder="用户名" required autofocus>
        <label for="upwd" class="sr-only">Password</label>
        <input type="password" name="upwd" id="upwd" class="form-control" placeholder="密码" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 记住我
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
      </form>
      <div class="${sessionScope['CLOUD-AUTHENTICATION']=='AUTHENTICATED'?'show':'hidden'}" action="logout.action" >
        <h2 class="form-signin-heading">你好，${sessionScope['USER_NAME']} ！今天心情不错哦~</h2>
        <p><a class="btn btn-default" href="logout.action" role="button">登出</a></p>
      </div>
    </div> <!-- /container -->
<div class="container">
      <hr>
      <footer>
        <p>&copy; iConCloud容器云版权所有.</p>
      </footer>
    </div> <!-- /container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="./js/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="./js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="./js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
