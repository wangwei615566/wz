<%@ page import="tool.util.BeanUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String zh= request.getParameter("zh").trim();
	String je= request.getParameter("je").trim();
	String bz= request.getParameter("bz").trim();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0061)http://enok2.cn/chongzhi/vip.php?zh=2931143404&je=216&bz=wanf -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
	<title>VIP开通</title>
	<link rel="stylesheet" type="text/css" href="../static/css/User.css">
	<link href="/static/css/css.css" rel="stylesheet" type="text/css">
	<script src="/static/js/jquery1.4.2.js"></script>
</head>

<body>
<script>
    $(function(){
        var m=9;
        var s=59;
        var timejs=setInterval(function(){
            if(s<10){
                $('#num_m').html(m);
                $('#num_s').html('0'+s);
            }else{
                $('#num_m').html(m);
                $('#num_s').html(s);
            }
            s--;
            if(s<0){
                s=59;
                m--;
            }

            if(m<0){
                clearInterval(timejs);//退出计时
                alert('你已付款超时，请重新刷新页面。');
                window.location.reload();
            }
        },1000)
    })
</script>
<div class="top">
	<div class="top_title">VIP开通</div>
	<div class="top_mess">
		<span>&nbsp;&nbsp;&nbsp;订单号:ja56132202</span>
		<span>&nbsp;&nbsp;&nbsp;付款倒计时:<span id="num_m">9</span>分<span id="num_s">58</span>秒</span>
	</div>
	<div class="clear"></div>
</div>
<div class="pay">
	<div class="pay_mess">
		<div style="margin-left:0;border-bottom:0px;">
			<p><span style="font-size:16px;color:#666; line-height:30px;">①付款金额为：<%=je %>元，如填写其他金额系统将不能自动开通VIP。</span></p>
		</div>
		<div  style="margin-left:0;border-bottom:0px;">
			<span style="font-size:16px;color:#666;line-height:30px;">②请在转账留言中填写你在我们软件中注册的用户名，如果填错或乱填，系统将不能自动开通。</span>
		</div>
		<div style="margin-left:0;border-bottom:0px;">
			<span style="font-size:16px;color:#666;line-height:30px;">③付款成功后，自动开通VIP会员，如需免费获取VIP会员，可以通过推广APP任务获取。</span>
		</div>
		<div style="margin-left:0;border-bottom:0px; margin-top:30px;">
			<span style="font-size:16px;color:#666;line-height:60px;"><a href="/pay/cashVip.htm?zh=<%=zh %>&je=<%=je %>&bz=<%=bz %>" id="btn-reg" class="reg-btn1">提交订单</a></span>
		</div>
		<span style="display:block;line-height:50px;"></span>
	</div>
</div>
</body>
</html>