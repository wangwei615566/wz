<%@ page import="com.wz.cashloan.core.common.util.IpUtil" %>
<%@ page import="com.wz.cashloan.core.service.UserInviteService" %>
<%@ page import="tool.util.BeanUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String userId= request.getParameter("id");
  String ip = IpUtil.getRemortIP(request);
  UserInviteService userInviteService = (UserInviteService) BeanUtil.getBean("userInviteService");
  userInviteService.saveExtension(userId,ip);
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport">
<meta name="MobileOptimized" content="320"/>
<meta name="format-detection" content="telephone=no"/>
<meta name="apple-touch-fullscreen" content="YES"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<title>lllll</title>
<link href="../static/css/css.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="../static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../static/js/koala.min.1.5.js"></script>
<style>
/***微信判断下载**/
#weixin-tip{display:none;position:fixed;left:0;top:0;background:rgba(0,0,0,0.8);filter:alpha(opacity=80);width:100%;height:100%;z-index:100;}
#weixin-tip p{text-align:center;margin-top:10%;padding:0 5%;position:relative;}
#weixin-tip .close{color:#fff;padding:5px;font:bold 20px/24px simsun;text-shadow:0 1px 0 #ddd;position:absolute;top:0;left:5%;}  
#weixin-tip img{
	width:100%;
	height:100%;
}
</style>
</head>
<body>

  <div class="header">
    <div class="logo fl flexbox"><a href="#"></a></div>
  </div>
  <div class="clear"></div>
   
  <div id="fsD1" class="focus" >
    <div id="D1pic1" class="fPic">
      <div class="fcon" style="display: none;"> <a target="_blank" ><img src="../static/images/banner1.jpg" style="opacity: 1; "></a> </div>
      <div class="fcon" style="display: none;"> <a target="_blank" ><img src="../static/images/banner2.jpg" style="opacity: 1; "></a> </div>
      <div class="fcon" style="display: none;"> <a target="_blank" ><img src="../static/images/banner3.jpg" style="opacity: 1; "></a> </div>
    </div>
    <div class="fbg">
      <div class="D1fBt" id="D1fBt"> <span class="prev"><img src="images/prev.jpg" /></span> <a href="javascript:void(0)" hidefocus="true" target="_self" class=""><i>1</i></a> <a href="javascript:void(0)" hidefocus="true" target="_self" class=""><i>2</i></a> <a href="javascript:void(0)" hidefocus="true" target="_self" class="current"><i>3</i></a>  <span class="next"><img src="images/next.jpg" /></span> </div>
    </div>
  </div>
  <script type="text/javascript">
	Qfast.add('widgets', { path: "../static/js/terminator2.2.min.js", type: "js", requires: ['fx'] });  
	Qfast(false, 'widgets', function () {
		K.tabs({
			id: 'fsD1',   //焦点图包裹id  
			conId: "D1pic1",  //** 大图域包裹id  
			tabId:"D1fBt",  
			tabTn:"a",
			conCn: '.fcon', //** 大图域配置class       
			auto: 1,   //自动播放 1或0
			effect: 'fade',   //效果配置
			eType: 'click', //** 鼠标事件
			pageBt:true,//是否有按钮切换页码
			bns: ['.prev', '.next'],//** 前后按钮配置class                          
			interval: 3000  //** 停顿时间  
		}) 
	})  
</script>
  <div style=" clear:both"></div>
  <div class="download">
	  <img class="bg" src="../static/images/download.jpg" />
	  <div class="button1" style="text-align:center">
		  <a id="J_weixin" class="android-btn" href="../static/apk/szb.apk">
		  	<img class="button" src="../static/images/dow1.jpg" />
		  </a>
		  <!-- <a href="http://wakuang-10027738.cossh.myqcloud.com/sppz.exe">
		  	<img class="button" src="../static/images/dow2.jpg" />
		  </a> -->
	  </div>
  </div>
       
 <div class="video">
   <video src="../static/mp4/zhuye.mp4" controls="controls" autoplay="autoplay" width="100%" ></video>
</div>


   
</div>
<script type="text/javascript">// 识别微信并遮罩 px
  var is_weixin = (function() {
      var ua = navigator.userAgent.toLowerCase();
      if (ua.match(/MicroMessenger/i) == "micromessenger") {
          return true;
      } else {
          return false;
      }
  })();
  window.onload = function(){
    var winHeight = typeof window.innerHeight != 'undefined' ? window.innerHeight : document.documentElement.clientHeight;
    var btn = document.getElementById('J_weixin');
    var tip = document.getElementById('weixin-tip');
    var close = document.getElementById('close');
    if(is_weixin){
      btn.onclick = function(e){
        tip.style.height = winHeight + 'px';
        tip.style.display = 'block';
        return false;
      }
      close.onclick = function(){
        tip.style.display = 'none';
      }
    }
  }
  

  </script>
<a id="J_weixin" class="android-btn" href="#"></a>
<div id="weixin-tip"><p><img src="../static/images/live_weixin.png" alt="微信打开"/><span id="close" title="关闭" class="close">×</span></p></div>

<!--以下为推广数据判断并写入数据库。-->
  
  
  </div>
<DIV style="DISPLAY: none">
</body>
</html>
