<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%
	String path = request.getContextPath();
	String basePath = "/";
	String invitationCode= request.getParameter("invitationCode");
	String channelCode= request.getParameter("channelCode");
	String inviteUserId= request.getParameter("userId");
	String loanSource= request.getParameter("loanSource");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>成长钱包——注册</title>
    <meta name="keywords" content="贷款,小额借钱,借贷,贷款app,急用钱,短期快速放贷,极速借款借钱,小额贷款,成长钱包">
    <meta name="description" content="专注于为个人提供正规小额贷款、无抵押贷款、个人贷款、闪电借钱等服务">
    <script src="<%=basePath%>static/js/flexable.js"></script>
    <meta name="format-detection" content="telephone=no" />
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="#7CD88E">
    <link rel="stylesheet" href="<%=basePath%>static/css/style.css"/>
</head>
<style>
    body{background: #fff1dc;}
    .bar{
    	height:auto !important;
    }
    .signup .content {
        padding-top: 0.72rem;
	    background: #fff;
	    width: 80%;
	    margin: 0.5rem auto;
	    border: 1px solid red;
	    border-radius: 8px;
	}
	.bordergrey{
		border: 1px solid #b2b2b2 !important;
		border-radius:8px;
	}
	#btn{
		color:#f68b0d;
		background:none !important;
		box-shadow:none !important;
		-webkit-box-shadow:none !important;
		-moz-box-shadow:none !important;
	}
	#imgObj{
		border-radius:8px !important;
	}
	.picVerify{
		margin-bottom:.25rem !important;
	}
	.signup form p.other label {
		color:#7a7a7a;
	}
	.signup form p.other a{
		color:#f08519;
	}
	.signup .reg-btn{
		background:#f68b0d;
		color:#fff;
		box-shadow:none !important;
		-webkit-box-shadow:none !important;
		-moz-box-shadow:none !important;
	}
	.fiture{
		width:80%;
		margin:0 auto;
	}
	.signup p.ftitle{
		margin: 0.6rem auto;
    	font-size: 0.5rem;
    	color:#945000;
	}
	.fiture .fpart{
		display:inline-block;
		width:31%;
		text-align:center;
	}
	.fiture .fpart p{
		margin-top:0.3rem;
		color:#b56200;
	}
	.signup p{
		padding: 2px;
	}
	.content1{
		padding-bottom:1rem;
	}
	.signup .content1 .info{
		width: 80%;
	    margin: 1rem auto;
	    text-align: left;
	    font-size: 0.35rem;
	    color:#b56200;
	}
	.signup .content1 .winfo{
		text-align:center;
		color:#b56200;
		margin-top: 0.2rem;
	}
</style>
<body>
    <img src="" style="position:absolute;opacity: 0;left:-10000px;z-index: -1000;">

    <div class="signup seven">                          
        <img class="bar" alt="" src="<%=basePath%>static/images/reg.jpg"/>          
        <div class="content">
            <form action="/api/user/wxRegister.htm">
                <input name="phone" class="bordergrey" type="tel" value="" maxlength="11" placeholder="请输入手机号"/>
                <input name="password" class="bordergrey" type="password" value="" placeholder="设置登录密码"/>
                <%if(channelCode != null&&!channelCode.equals("")&&!channelCode.equals("null")){ %>
                <input id='channelCode' name="channelCode" class="bordergrey" type="hidden" value="<%=channelCode%>"/>
                <%} %>
                <%if(invitationCode != null&&!invitationCode.equals("")&&!invitationCode.equals("null")){ %>
                <input id='invitation' name="invitationCode" class="bordergrey" type="text" value="<%=invitationCode%>" disabled="true" placeholder="推荐人"/>
                <%} %>
                 <%if(channelCode != null&&!channelCode.equals("")&&!channelCode.equals("null")){ %>
                <input id='channelCode' name="channelCode" class="bordergrey" type="hidden" value="<%=channelCode%>" />
                <%} %>
                <p class="picVerify clearfix bordergrey">
                    <input type="text" id="code" name="code" placeholder="请输入图片验证码" style="margin-bottom:0;"/>  
                    <img id="imgObj" alt="验证码"  src="/api/h5/imgCode/generate.htm" onclick="changeImg()"/> 
                </p>
                <p class="special clearfix bordergrey">
                    <input name="vcode" type="text" value="" placeholder="请输入手机验证码" maxlength="4" style="margin-bottom:0;"/>
                    <button id="btn">获取验证码</button>
                </p>
                <a href="javascript:;" id="btn-reg" class="reg-btn">立即申请</a>
                <p class="clearfix other">
                    <input id="checkbox" name="yes" type="checkbox" value=""/>
                    <label for="checkbox" onclick="click_a();">同意<a href="protocol_register.jsp">《用户注册协议》</a>
                          <i src="<%=basePath%>static/images/yes.png" id="click_a"></i>
                    </label>                            
                </p>
            </form>           
        </div>
        <div class="content1">
        	<p class="ftitle">成长钱包优势</p>
            <div class="fiture">
            	<div class="fpart">
            		<img src="<%=basePath%>static/images/reg1.png" />
            		<p>一分钟认证</p>
            	</div>
            	<div class="fpart">
            		<img src="<%=basePath%>static/images/reg2.png" />
            		<p>十分钟放款</p>
            	</div>
            	<div class="fpart">
            		<img src="<%=basePath%>static/images/reg3.png" />
            		<p>随时随借</p>
            	</div>
            </div>
            <p class="info">成长钱包为用户打造一个极速金融借贷平台，用户可以在这里快速申请贷款，放款快捷，为用户轻松解决资金问题。</p>
       		<p class="winfo">武汉成长无限网络科技有限公司</p>
       		<p class="winfo">客服热线：400-6986967</p>
        </div>
    </div>

    <div class="popup tips" style="display:none">
        <div class="overlay"></div>
        <div class="dialog">
        <span class="close"></span>
        <h2 id="confirm">...</h2>
        <p>
            <a href="javascript:;" class="yes">确定</a>
        </p>
      </div>
    </div>

    <div class="popup pop" style="display:none">
        <div class="overlay"></div>
        <div class="dialog">
            <span class="close"></span>
            <h2>...</h2>
            <p>
                <a href="<%=basePath%>user/getAppUrl">立即下载APP，一键提现</a>
            </p>
        </div>
    </div>
</body>
</html>
 
<script src="<%=basePath%>static/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/placeholders.js" ></script> 
<script type="text/javascript" src="<%=basePath%>static/js/signup.js" ></script>
<script type="text/javascript" src="<%=basePath%>static/js/jquery.md5.js" ></script>
<script type="text/javascript" src="<%=basePath%>static/js/config.js" ></script>
<script>
    //头部信息
    var _hmt = _hmt || [];
    (function() {
      var hm = document.createElement("script");
      hm.src = "<%=basePath%>static/js/hm.js";
      var s = document.getElementsByTagName("script")[0]; 
      s.parentNode.insertBefore(hm, s);
    })();

    kdlcJsApiShareBack(); 
    function kdlcJsApiShareBack(){
        if (typeof(kdlcJsApi) != 'undefined') {
            kdlcJsApi.pageAddShare('{"isShare":1,"shareBtnTitle":"\u6309\u94ae\u6587\u6848","shareTitle":"\u5206\u4eabtitle","sharePageTitle":"\u5206\u4eab\u6709\u5956\u63cf\u8ff0","shareContent":"\u5206\u4eab\u63cf\u8ff0","shareUrl":"http:\/\/www.koudailc.com","shareImg":"http:\/\/res.koudailc.com\/article\/20160506\/3572c6e05464b6.png","sharePlatform":["wx","wechatf","qq","qqzone","sina","sms"],"shareSuccessAlert":"\u5206\u4eab\u6210\u529f\u5f39\u6846\u6587\u6848","shareIsUp":1,"shareUpId":11,"shareUpType":1,"shareUpUrl":"http:\/\/www.koudailc.com"}');
        };
        return 'kdlc_share_back';
    }

    //接口定义
    //var codeurl = '<%=basePath%>invite/getRegCode';
    //var signup ='<%=basePath%>invite/getRegister';
    //var reg ='<%=basePath%>user/addRegister';
    //var register ='<%=basePath%>appSystem/getBorrowByApp';

    //新加
    var codeurl = '/api/user/sendSms.htm';//获取验证码
    var signup = '/api/user/validateSmsCode.htm';//判断验证码手否正确
    var checkurl = '/api/user/h5SendSms.htm';
    //app注册接口
    //var reg ='/api/user/register.htm';
    //微信渠道注册接口
    var reg ='/api/user/wxRegister.htm';
    
    var invitationCode='<%=invitationCode%>';
    var inviteUserId='<%=inviteUserId%>';
    var channelCode='<%=channelCode%>';
    var platform = window.location.href;
	if(platform.indexOf('=')<=0){
		platform = undefined;
	}else{
		platform = platform.substr(platform.indexOf('=')+1,platform.length);
	}	
    //协议选中切换
    var i = 0;
    function click_a(){
        if(i%2==0){
            $('#click_a').css('display', 'none');
            if(typeof bgColor !== 'undefined'){
               $('#click_a').css('background-color',bgColor);
            }
        }else{
            var src = $('#click_a').attr('src');
            $('#click_a').css('display', 'inline');
            $('#click_a').css('background','url('+ src + ') 0 0 no-repeat').css('background-size','0.3733333333rem 0.3733333333rem');
        }
        i++;
    } 
 // 刷新图片  
    function changeImg() {  
        var imgSrc = $("#imgObj");  
        var times = (new Date()).getTime(); 
        imgSrc.attr("src", '/api/h5/imgCode/generate.htm?timestamp='+times);  
    }
    //头部图片
    $('img').eq(0).attr('src',getInvite_img());
    //app下载地址
    $('.other>a').attr('href',getInvite_a());
</script>