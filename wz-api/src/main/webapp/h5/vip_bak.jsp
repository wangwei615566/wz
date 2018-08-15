<%@ page import="tool.util.BeanUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	/* String zh= request.getParameter("zh");
	String je= request.getParameter("je");
	String bz= request.getParameter("bz");
	UserChargeLogService userChargeLogService = (UserChargeLogService)BeanUtil.getBean("userChargeLogService");
	userChargeLogService.insertSelective(zh,je,bz); */

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0061)http://enok2.cn/chongzhi/vip.php?zh=2931143404&je=216&bz=wanf -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
	<title>VIP开通</title>
	<link rel="stylesheet" type="text/css" href="/static/css/User.css">
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
<div class="text">
	<div class="text_s">由于支付宝接口维护，临时采用财付通〔QQ钱包〕，请按以下步骤操作付款开通VIP。</div>
	<ul>
		<li><p class="num">1</p><p class="text_t">付款金额为：216.00，如填写其他金额系统将不能自动开通VIP。</p></li>
		<li><p class="num">2</p><p class="text_t">请在转账留言明中填写你在我们软件中的注册用户名，如果填错或乱填，系统将不能自动开通。</p></li>
		<li><p class="num">3</p><p class="text_t">付款成功后，自动开通VIP会员，如需免费获取VIP会员，可以通过推广APP获取。</p></li>
		<li><img style="border:0px;margain:0px;padding:0px;" src="/static/images/wzimg/pay4.gif"></li>
	</ul>
</div>
<div class="pay">
	<div class="pay_title">
		<p>使用QQ扫描以下二维码即可自动开通VIP</p>
	</div>
	<div class="pay_mess">
		<div class="pay_mess_list">
			<p><span class="pay_name">财付通账号：</span><span class="pay_text">
		  		${zh}  </span></p>
		</div>
		<div class="pay_mess_list"><span class="pay_name">付款金额：</span><span class="pay_text"></span></div>
		<div class="pay_mess_list"><span class="pay_name">转账留言：</span><span class="pay_text"></span></div>
	</div>
</div>
<div class="ewm">
	<p><img src="/static/images/wzimg/111.png"></p>
	<p>使用QQ扫描二维码或者在财付通付款。</p>
</div>
<div class="foot">注意：转账留言要填写自己的软件用户名，否则系统不会自动开通，若出现系统没有及时开通清等待即可。</div>
<div class="foot">
	<p>手机用户可以通过截图，保存二维码，打开QQ扫一扫，从相册导入二维码，付款成功即可立刻自动开通VIP会员    </p>
	<p>电脑用户直接打开手机QQ扫码二维码付款，备注填写您的软件账号即可立刻自动开通VIP会员    </p>
	<p> 付款成功之后若出现没有及时开通请等待即可，会在几小时之内为你开通，退出软件从新打开即可查看是否升级, 忘记写账号补充账号即可。   </p>
	<p>如果您无法通过QQ扫一扫付款，那么你可以选择耐心等待，因为微支付宝动开通的功能目前处于维护升级状态，建议您使用QQ支付！。</p>
</div>
<div class="foot"></div>
<br>&nbsp;&nbsp;

<div style="DISPLAY: none">
	<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1261164725'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/stat.php%3Fid%3D1261164725' type='text/javascript'%3E%3C/script%3E"));</script><span id="cnzz_stat_icon_1261164725"><a href="http://www.cnzz.com/stat/website.php?web_id=1261164725" target="_blank" title="站长统计">站长统计</a></span><script src="./VIP开通_files/stat.php" type="text/javascript"></script><script src="./VIP开通_files/core.php" charset="utf-8" type="text/javascript"></script>
</div>


</body></html>