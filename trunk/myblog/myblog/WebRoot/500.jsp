<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" isErrorPage="true"%><%
String contextPath = request.getContextPath();
if(contextPath.equals("/")){
	contextPath = "";
}
%><html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN" xml:lang="zh-CN">
<head>
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="0"/>
	<meta http-equiv="Content-Type" content="application/xhtml+xml; charset=utf-8" />
	<meta http-equiv="Content-Language" content="zh-CN" />
	<meta content="all" name="robots" />
	<meta name="author" content="莫多泡泡(somebody)" />
	<meta name="Copyright" content="jdkcn.com,All Rights Reserved,2001-2007" />
	<link href="<%=contextPath %>/css/style.css" rel="stylesheet" type="text/css" />
	<title>The server error 500</title>
</head>
<body>
<div id="header">
  <dl id="globalnav">
		<dt id="jdkcn-logo"><a href="/">返回首页(Return Home)</a></dt>
		<dd id="account-menu">
		</dd>
	</dl>
</div>

<div id="content">
	<div class="clear"></div>
	<div>
		<div id="container">
	<div id="main">
      <div class="centerblock">
		<p>您好！</p>
		<p>正在进行系统升级或者更新，网络服务暂时停止使用，请稍候继续访问，对您造成不便敬请谅解。您可以报告问题<a href="mailto:service@jdkcn.com">Jdkcn.com Service</a>给站长，</p>
		<p>ooops!!,server error, may by updating.if any questions report to <a href="mailto:service@jdkcn.com">Jdkcn.com Service</a></p>
		</div>
     </div>
		</div>
	</div>
</div>
<div id="footer">
	<dl id="bottomnav">
		<dd id="bottom-menu">
			<ul class="menu">
				<li><a href="/">首页(Home)</a></li>
				<li><a href="http://groups.google.com/group/myblogdev">帮助?(Help?)</a></li>
			</ul>
		</dd>
		<dt id="copyright">
			<span>(c) 2001 - 2007 <a href="http://www.jdkcn.com/" class="Remove">Jdkcn.com</a>, All Rights Reserved </span>
		</dt>
	</dl>
</div>
</body>
</html>