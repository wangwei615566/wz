<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"
	name="viewport" />
<meta name="format-detection" content="telephone=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="#7CD88E" />
<title>【还款方式】</title>
<script src="/static/js/mobile.js"></script>
<script
	src="/static/js/zepto.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="/static/css/general.css" />
<style type="text/css">
body {
	font-size: 16px;
}

html, body, div, span, h1, h2, h3, p, em, img, dl, dt, dd, ol, ul, li,
	table, tr, th, td, form, input, select {
	margin: 0;
	padding: 0;
}

body {
	min-width: 320px;
	max-width: 480px;
	min-height: 100%;
	margin: 0 auto;
}

.bg {
	display: none;
	/*position: absolute;*/
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: 9999;
	background-color: rgba(0, 0, 0, .7);
}

.bg img {
	display: block;
	position: absolute;
	width: 65%;
	top: 5px;
	height: auto;
	float: left;
	left: 25%;
}
</style>
</head>
<body>
	<div class="container">
		<style type="text/css">
.container {
	margin: 16px;
}
</style>
		<p>目前还款方式：1.【主动还款】：用户可打开成长钱包，进入还款选择主动还款，点击主动还款，系统将会对用户绑定的银行卡进行还款金额扣除； 2.【快捷支付】：用户可以选择一张银行卡进行还款，点击还款 - 快捷支付，填入选择还款的银行卡信息并进行验证，通过验证后点击还款，系统将会对用户填入的银行卡进行还款金额扣除。3.【支付宝支付】用户可以选择支付宝进行还款，进入还款选择支付宝还款，点击支付宝还款，确认金额后点击确定，将进入支付宝支付流程。选择此项时请保证手机上已安装支付宝应用。4.【系统代扣】：若在还款期限日前没有主动进行还款，系统会自动在还款日当天凌晨两点左右进行系统代扣。</p>
	</div>
	<script src="/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="/static/js/config.js" ></script>
	<script>
		$('.bank').text(getBank());
		$('.airPay').text(getAirpay());
	</script>
</body>
</html>


