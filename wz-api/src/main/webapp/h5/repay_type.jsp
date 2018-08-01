<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8"/>
    <title>还款方式</title>
    <meta name="keywords" content="贷款,小额借钱,借贷,贷款app,急用钱,短期快速放贷,极速借款借钱,小额贷款">
    <meta name="description" content="现金贷专注于为个人提供正规小额贷款、无抵押贷款、个人贷款、闪电借钱等服务">
    <meta name="format-detection" content="telephone=no" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=0">
    <script src="/static/js/flexable.js"></script>
    <link href="/static/css/style1.css" rel="stylesheet"/>
	    <script src="/static/js/jquery.min.js"></script>
    <script type="text/javascript" src="/static/js/spin.js"></script>
    <script type="text/javascript" src="/static/js/common.js"></script>
    <script>
      var _hmt = _hmt || [];
      (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?985acfc678db5c774efb3ed1a2235b53";
        var s = document.getElementsByTagName("script")[0]; 
        s.parentNode.insertBefore(hm, s);
      })();
</script>
<style>
  p{
    padding: 10px 20px;
  }
</style>
  </head>
  <body>
    <div class="repayment-description">
    <h3>主动还款</h3>
    <p>用户可打开成长钱包，进入还款选择主动还款，点击主动还款，系统将会对用户绑定的银行卡进行还款金额扣除。</p>
    <h3>快捷支付</h3>
    <p>用户可以选择一张银行卡进行还款，点击还款 - 快捷支付，填入选择还款的银行卡信息并进行验证，通过验证后点击还款，系统将会对用户填入的银行卡进行还款金额扣除。</p>
    <h3>支付宝转账</h3>
    <p>用户可以选择支付宝进行还款，进入还款选择支付宝还款，点击支付宝还款，确认金额后点击确定，将进入支付宝支付流程。选择此项时请保证手机上已安装支付宝应用。</p>
  </div>
  <script type="text/javascript" src="/static/js/config.js" ></script>
  <script>
  $(function() {
    $("li").each(function(k,v){
      $(v).attr('id',k); 
    })
    $("li").click(function(){
      $(this).toggleClass("active");
      var id = $(this).attr('id');
      var lis = $('ul li').filter(function(i,e){
         return $(e).attr('id') != id;
      })
      lis.removeClass();
      
   });
 })
 $('.bank').text(getBank2());
 $('.airPay').text(getAirpay());
 $('.phone').text(getPhone());
 </script>  </body>
</html>
