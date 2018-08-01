<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
	basePath = "/";
	String invitationCode = request.getParameter("invitationCode");
	String channelCode = request.getParameter("channelCode");
	String inviteUserId = request.getParameter("userId");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=0">
  <title>授权失败</title>
  <style>
    html{
      font-family: "微软雅黑";
    }
    .clearfix:after{
      content: ".";
      display: block;
      height: 0;
      clear: both;
      visibility: hidden;
    }
    html,.probody,.proView{
      width: 100%;
      height: 100%;
      margin: 0px;
      padding: 0px;
    }
    .proTop{
      position: relative;
    }
    .proTop .probg{
      width: 100%;
      height: auto;
      display: block;
    }
    .nobtnLeft{
      position: absolute;
      bottom: 8%;
      left: 11%;
      width: 16.4%;
      height: auto;
    }
    .nobtnLeft img{
      width: 100%;
      height: auto;
      display: block;
    }
    .nobtnRight{
      position: absolute;
      bottom: 8%;
      right: 11%;
      width: 16.4%;
      height: auto;
    }
    .nobtnRight img{
      width: 100%;
      height: auto;
      display: block;
    }
    .textinfo{
      text-align:center;
    }
  </style>
</head>
<body class="probody">
  <div class="proView">
      <div class="proTop">
        <img src="<%=basePath%>static/images/fail.jpg" class="authImage" />
      </div>
      <div class="proBtm">
      	<p class="textinfo">授信失败</p>
      	<p class="textinfo">请返回确认信息正确稍后重新认证</p>
      </div>
  </div>
  <script src="<%=basePath%>static/js/jquery.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>static/js/config.js" ></script>
  <script>
    $('.probg').attr('src',getIndex_img1()); //头部图片
    $('#txtTopImg').attr('src',getIndex_img2()); //第一个横幅
    $('.txtBtmImg').attr('src',getIndex_img3()); //第二个横幅
  </script>
</body>
</html>