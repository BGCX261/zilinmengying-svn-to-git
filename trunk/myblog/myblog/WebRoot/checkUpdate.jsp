<%@page import="com.jdkcn.domain.SiteConfig"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%><%
response.setHeader("Pragma","No-Cache");
response.setHeader("Cache-Control","No-Cache");
response.setDateHeader("Expires",0);

String ver = request.getParameter("ver");
List oldVers = new ArrayList();
oldVers.add("Ver1.5.20070819qixi");
oldVers.add("Ver1.5.20070819qixi_P1");
oldVers.add("Ver1.6.20070925mid-autumn");
oldVers.add("Ver1.8.20071006-National-Day");
oldVers.add("Ver1.8.20071006-National-Day-Final");
SiteConfig siteConfig = new SiteConfig();
if(ver==null||oldVers.contains(ver)||"".equals(ver)){
%>
document.write("<div class=\"message\"><ul>");
document.write("<li>A new version[<%=siteConfig.getAppVersion() %>] available.recommend <a href=\"http://jdkcn.com/page/download.html\">update.</a>,Important for myblog1.8</li>");
document.write("<li>新版本[<%=siteConfig.getAppVersion() %>] 发布了。请您 <a href=\"http://jdkcn.com/page/download.html\">更新</a> 到最新版本。<strong>myblog1.8版本的请务必升级!</strong></li>");
document.write("</ul></div>");
<%}%>