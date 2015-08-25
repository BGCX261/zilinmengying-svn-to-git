<#import "/spring.ftl" as spring/>

<#--
 *
 * The contextPath
 -->
<#macro basePath><#if springMacroRequestContext.getContextPath()=="/">/<#else>${springMacroRequestContext.getContextPath()}/</#if></#macro>

<#--
 *
 * The blog's header.
 -->
<#macro blogHeader title,keywords,description,other>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<#assign contextPath ="">
<#if springMacroRequestContext.getContextPath()=="/">
	<#assign contextPath="/">
<#else>
	<#assign contextPath=springMacroRequestContext.getContextPath()+"/">
</#if>
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN" xml:lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="application/xhtml+xml; charset=utf-8" />
	<meta http-equiv="Content-Language" content="zh-CN" />
	<meta content="all" name="robots" />
	<meta name="author" content="子林梦影" />
	<meta name="Copyright" content="子林梦影" />
	<meta name="MSSmartTagsPreventParsing" content="true" />
	<meta name="keywords"  content="<#if keywords?exists && (keywords?length>0)>${keywords}<#else>${siteConfig.siteSimpleAbout}</#if>" />
	<meta name="description" content="<#if description?exists &&(description?length>0)>${description}<#else>${siteConfig.siteSimpleAbout}</#if>" />
	<link rel="made" rev="made" href="mailto:rory.cn(at)gmail.com" />
	<link rel="alternate" type="application/rss+xml" href="${contextPath}feed/rss2/" title="订阅 ${siteConfig.siteName} 所有文章(rss2)" />
	<link href="${contextPath}styles/web-20-100/style.css" rel="stylesheet" title="Web v2.0 beta" type="text/css" media="all" />
	<link href="${contextPath}styles/lightbox.css" rel="stylesheet" type="text/css" />
	<!--[if lte IE 7]>
	<link href="${contextPath}styles/web-20-100/style_ie.css" rel="stylesheet" type="text/css" />
	<![endif]-->
	<!--[if gt IE 6]>
	<style type="text/css" media="screen">
	#wp-footer p { margin: 0; }
	</style>
	<![endif]-->
	<link rel="icon" href="${contextPath}favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="${contextPath}favicon.ico" type="image/x-icon" />
	<script type="text/javascript">
	// Lightbox Image Configuration
	var loadingImage = "${contextPath}styles/img/loading.gif";		
	var closeButton = "${contextPath}styles/img/close.gif";
	</script>
	<script src="${contextPath}styles/lightbox.js" type="text/javascript"></script>
	<script src="${contextPath}styles/web-20-100/utilities.js" type="text/javascript"></script>
	<script type="text/javascript" src="${contextPath}js/prototype.js"></script>
	<#if other?exists>
	${other}
	</#if>
	<title>${title}</title>
</head>
</#macro>

<#macro blogFooter>
	<div id="wp-footer">
		<p>欢迎光临~！ <a href="${siteConfig.siteURL}">${siteConfig.siteName}</a>		&nbsp;
		<span class="powered"><a href="http://code.google.com/p/myblog" title="Find out more">我的blog</a>[${siteConfig.getAppVersion()}]</span>
		<#if siteConfig.getICPNumber()?exists && (siteConfig.getICPNumber()?length>0)>
		&nbsp;
		<a href="http://www.miibeian.gov.cn"><b>${siteConfig.getICPNumber()}</b></a>
		</#if>
		${siteConfig.analyticsCode?if_exists}
		</p>
	</div>
</#macro>

<#macro blogSidebar>
		<div id="wp-content-sec">
		   <#if siteConfig.adSenseForSearch??&&siteConfig.adSenseForSearch!="">
			<h3>Search Website</h3>
			${siteConfig.adSenseForSearch}
			<br />
		   </#if>
						<h3><@spring.message "label.about"/></h3>
						<p>${siteConfig.siteAbout}</p>
						<h3><@spring.message "label.recent.posts"/></h3>
			<ol>
					<#if recentEntries?exists>
					<#list recentEntries as entry>
					<li><a href="<@blog.basePath/>entry<#if entry.name?exists>/${entry.name}<#else>/id/${entry.id}</#if>.html" title="${entry.title}">${entry.title}</a></li>
					</#list>
					</#if>
			</ol>
						<h3><@spring.message "label.recent.comments"/></h3>
			<ol>
					<#if recentComments?exists>
					<#list recentComments as comment>
					<li><a href="<@blog.basePath/>entry<#if comment.entry.name?exists>/${comment.entry.name}<#else>/id/${comment.entry.id}</#if>.html#c_${comment.id}" title="${comment.authorName} Says:${comment.content?html}"><#if (comment.content?length>20)>${comment.content?substring(0,20)?html}<#else>${comment.content}</#if></a></li>
					</#list>
					</#if>
			</ol>
			<h3><@spring.message "label.tags"/></h3>
			<li id="tag_cloud" class="widget widget_tag_cloud">
		<#if hotTags??>
        <#assign totalCount = 0/>
        <#list hotTags as t>
        <#assign totalCount = totalCount + t.count/>
        </#list>
        <#list hotTags as tag>
        <#assign fontSize=8/>
        <#if (totalCount>0)>
		<#assign fontSize=8 + (tag.count/totalCount)*100/>
		</#if>
        <#if (fontSize>15)>
        <#assign fontSize=15/>
        </#if>
      	<a href="<@blog.basePath/>tag/${tag.name}/" title="${springMacroRequestContext.getMessage("label.topic.count",[tag.count])}" style='font-size: ${fontSize}pt;'>${tag.name}</a>
      	</#list>
      	</#if>
            </li>
			<div class="wp-content-sec-column">
				<h3><@spring.message "label.category"/></h3>
				<ul>
						<#if categories?exists>
						<#list categories as category>
						<#assign args = [category.name]>
						<li><a href="<@blog.basePath/>category/${category.name}/" title="${springMacroRequestContext.getMessage("label.view.all.entry",args)}">${category.name}(${category.count})</a></li>
						</#list>
						</#if>
				</ul>
			</div>
			<br style="clear:left;" />
			<div class="wp-content-sec-column">
				<h3><@spring.message "label.archives"/></h3>
				<#if archiveMonthList??>
				<ul>
						<#list archiveMonthList as month>
						<li><a href="<@blog.basePath/>archive/${month?string("yyyyMM")}/" title="${month?string("MMMM")} ${month?string("yyyy")}">${month?string("MMMM")} ${month?string("yyyy")}</a></li>
						</#list>
				</ul>
				</#if>
			</div>
			<br style="clear:left;" />
			<div class="link">
			<h3><@spring.message "label.links"/></h3>
			<ul>
<#if recommendLinks?exists>
<#list recommendLinks as link>
				<li><a href="${link.URL}" title="${link.description}"><#if link.imgURL?exists><img src="${link.imgURL}" alt="${link.name}"/><#else>${link.name}</#if></a></li>
</#list>
</#if>
				<li><a href="<@blog.basePath/>links.html" title="More links">More...</a></li>
			</ul>
			</div>
		</div>
</#macro>

<#macro blogNav current>
<#assign entryCategoryId = ""/>
<#if entry?exists>
	<#assign entryCategoryId = entry.categories[0].id/>
</#if>
	<div id="wp-menu-main">
		<ol>
			<li class="first"><a href="<@blog.basePath/>" id="home"<#if current=='home'> class="selected"</#if> title="Return to the home page."><@spring.message "label.home"/></a></li>
			<#if categories?exists>
			<#list categories as category>
			<#assign args = [category.name]>
			<li><a href="<@blog.basePath/>category/${category.name}/"<#if (id?exists&&category.id==id)||(name?exists&&category.name==name)> class="selected"</#if><#if entryCategoryId?exists&&category.id==entryCategoryId> class="selected"</#if> id="c_${category.id}" title="${springMacroRequestContext.getMessage("label.view.all.entry",args)}">${category.name}</a></li>
			</#list>
			</#if>
			<#if pages?exists>
		  	<#list pages as p>
		  	<li><a href="<@blog.basePath/>page/${p.name}.html"<#if page?exists&&(page.name==p.name)> class="selected"</#if> id="p_${p.id}" title="${p.title}">${p.title}</a></li>
		  	</#list>
		  	</#if>
			<li><a href="<@blog.basePath/>tags.html"<#if current=='tags'> class="selected"</#if> id="tags" title="<@spring.message "label.tags"/>"><@spring.message "label.tags"/></a></li>
			<li><a href="<@blog.basePath/>search.html"<#if current=='search'> class="selected"</#if> id="search" title="Search this blog">Search</a></li>
			<li><a href="<@blog.basePath/>links.html"<#if current=='links'> class="selected"</#if> id="links" title="Blog Links"><@spring.message "label.links"/></a></li>
			<li><#if _myblog_authuser?exists><a href="<@blog.basePath/>admin/index.jspx" id="siteAdmin" title="<@spring.message "label.site.admin"/>"><@spring.message "label.site.admin"/></a><#else><a href="<@blog.basePath/>admin/login.jspx" id="login" title="<@spring.message "label.login"/>"><@spring.message "label.login"/></a></#if></li>
			<#if _myblog_authuser?exists><li><a href="<@blog.basePath/>admin/logout.jspx" id="about" title="<@spring.message "menu.logout"/>"><@spring.message "menu.logout"/></a></li></#if>
		</ol>
	</div>
</#macro>

<#--
 *
 * show the messages if exists.
 -->
<#macro message>
<#if messages?exists>
<div class="message">
	<#list messages as msg>
		${msg}<br />
	</#list>
</div>
</#if>
</#macro>

<#--
 *
 * show the errors if exists.
 -->
<#macro error>
<#if errors??>
<div class="error">
<#list errors as error>${error}<br /></#list>
</div>
</#if>
</#macro>