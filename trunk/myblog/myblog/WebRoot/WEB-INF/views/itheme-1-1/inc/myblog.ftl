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
	<meta name="author" content="子林梦影(somebody)" />
	<meta name="Copyright" content="jdkcn.com,All Rights Reserved,2001-2007" />
	<meta name="MSSmartTagsPreventParsing" content="true" />
	<meta name="keywords"  content="<#if keywords?exists && (keywords?length>0)>${keywords}<#else>${siteConfig.siteSimpleAbout}</#if>" />
	<meta name="description" content="<#if description?exists &&(description?length>0)>${description}<#else>${siteConfig.siteSimpleAbout}</#if>" />
	<link rel="made" rev="made" href="mailto:rory.cn(at)gmail.com" />
	
	<link rel="stylesheet" href="${contextPath}styles/itheme-1-1/style.css" type="text/css" media="all" />
	<link rel="stylesheet" href="${contextPath}styles/itheme-1-1/print.css" type="text/css" media="print" />
	<link rel="stylesheet" href="${contextPath}styles/lightbox.css" type="text/css" media="all" />
	<link rel="stylesheet" href="${contextPath}styles/messages.css" type="text/css" media="all" />
	
	
	<script type="text/javascript" src="${contextPath}styles/itheme-1-1/dbx.js"></script>
	<script type="text/javascript" src="${contextPath}styles/itheme-1-1/dbx-key.js"></script>
	<link rel="stylesheet" type="text/css" href="${contextPath}styles/itheme-1-1/dbx.css" media="screen, projection" />
	
	<!--[if lt IE 7]>
	<link rel="stylesheet" href="${contextPath}styles/itheme-1-1/ie-gif.css" type="text/css" />
	<![endif]-->
	
	<link rel="icon" href="${contextPath}favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="${contextPath}favicon.ico" type="image/x-icon" />
	<script type="text/javascript">
	// Lightbox Image Configuration
	var loadingImage = "${contextPath}img/loading.gif";		
	var closeButton = "${contextPath}img/close.gif";
	</script>
	<script src="${contextPath}styles/lightbox.js" type="text/javascript"></script>
	<script src="${contextPath}styles/utilities.js" type="text/javascript"></script>
	<script type="text/javascript" src="${contextPath}js/prototype.js"></script>
	<#if other?exists>
	${other}
	</#if>
	<title>${title}</title>
</head>
<body>
<div id="page">
  <div id="wrapper">
    <div id="header">
      <h1><a href="<@blog.basePath/>" title="Return to the home page.">${siteConfig.siteName}</a></h1>
      <div class="description">${siteConfig.siteSubName}</div>
      <form method="get" id="searchform" action="<@blog.basePath/>search.html">
	  <div>
	    <input type="text" value="<#if query?exists>${query}<#else>Search</#if>" name="query" id="query" onfocus="if (this.value == 'Search') {this.value = '';}" onblur="if (this.value == '') {this.value = 'Search';}" />
        <input type="submit" id="searchsubmit" value="Go" />
      </div>
      </form>
    </div><!-- /header -->
</#macro>

<#macro blogFooter>
      <div id="footer">
		Copyright &copy; 2001-2007 <a href="${siteConfig.siteURL}">${siteConfig.siteName}</a>		&nbsp;
		<span class="powered"><a href="http://code.google.com/p/myblog" title="Find out more">Myblog</a>[${siteConfig.getAppVersion()}]</span>
		&nbsp;
		<span class="layout"><a href="http://www.ndesign-studio.com/resources/wp-themes/">iTheme</a></span>
		&nbsp;
		<span class="validate"><a href="http://jigsaw.w3.org/css-validator/check/referer" title="Check validity">CSS</a></span>
		&nbsp;
		<span class="validate"><a href="http://validator.w3.org/check?uri=referer" title="Check validity">XHTML</a></span>
		&nbsp;
		<span class="feed"><a href="${siteConfig.siteURL}/feed/atom/" title="Get posts delivered to your inbox">Subscribe</a></span>
		<#if siteConfig.getICPNumber()?exists && (siteConfig.getICPNumber()?length>0)>
		&nbsp;
		<a href="http://www.miibeian.gov.cn"><b>${siteConfig.getICPNumber()}</b></a>
		</#if>
      </div>
</#macro>

<#macro blogBottom>
    <hr class="hidden" />
  </div><!--/wrapper -->
</div><!--/page -->
${siteConfig.analyticsCode?if_exists}
</body>
</html>
</#macro>

<#macro blogSidebar>
<div class="dbx-group" id="sidebar">
	<#if siteConfig.adSenseForSearch??&&siteConfig.adSenseForSearch!="">
    <!--sidebox start -->
    <div id="googlesearch" class="dbx-box">
    	<h3 class="dbx-handle">GoogleSearch</h3>
        <div class="dbx-content">
          ${siteConfig.adSenseForSearch}
        </div>
    </div>
    <!--sidebox end -->
    </#if>
    <!--sidebox start -->
    <div id="simpleAbout" class="dbx-box">
    	<h3 class="dbx-handle"><@spring.message "label.about"/></h3>
        <div class="dbx-content">
          ${siteConfig.siteAbout}
        </div>
    </div>
    <!--sidebox end -->

    <!--sidebox start -->
    <div id="tag_cloud" class="dbx-box widget_tag_cloud">
      <h3 class="dbx-handle"><@spring.message "label.tags"/></h3>
      <div class="dbx-content">
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
      	<a href="<@blog.basePath/>tag/${tag.name}/" title="${springMacroRequestContext.getMessage("label.topic.count",[tag.count])}" style='font-size: ${fontSize}pt;'>${tag.name}</a>&nbsp;
      	</#list>
      	</#if>
      </div>
    </div>
    <!--sidebox end -->

    <!--sidebox start -->
    <div id="recent-posts" class="dbx-box">
    	<h3 class="dbx-handle"><@spring.message "label.recent.posts"/></h3>
        <div class="dbx-content">
          <ul>
			<#if recentEntries?exists>
			<#list recentEntries as entry>
			<li><a href="<@blog.basePath/>entry<#if entry.name?exists>/${entry.name}<#else>/id/${entry.id}</#if>.html" title="${entry.title}">${entry.title}</a></li>
			</#list>
			</#if>
          </ul>
        </div>
    </div>
    <!--sidebox end -->
    
    <!--sidebox start -->
    <div id="recent-comments" class="dbx-box">
    	<h3 class="dbx-handle"><@spring.message "label.recent.comments"/></h3>
        <div class="dbx-content">
          <ul>
			<#if recentComments?exists>
			<#list recentComments as comment>
			<li><a href="<@blog.basePath/>entry<#if comment.entry.name?exists>/${comment.entry.name}<#else>/id/${comment.entry.id}</#if>.html#c_${comment.id}" title="${comment.authorName} says:"><#if (comment.content?length>20)>${comment.content?substring(0,20)?html}<#else>${comment.content}</#if></a></li>
			</#list>
			</#if>
          </ul>
        </div>
    </div>
    <!--sidebox end -->
    
	<!--sidebox start -->
    <div id="categories" class="dbx-box">
    	<h3 class="dbx-handle"><@spring.message "label.category"/></h3>
        <div class="dbx-content">
        	<ul>
				<#if categories?exists>
				<#list categories as category>
				<#assign args = [category.name]>
				<li><a href="<@blog.basePath/>category/${category.name}/" title="${springMacroRequestContext.getMessage("label.view.all.entry",args)}">${category.name}(${category.count})</a></li>
				</#list>
				</#if>
          	</ul>
        </div>
    </div>
    <!--sidebox end -->
    
    <!--sidebox start -->
	<div id="archives" class="dbx-box">
		<h3 class="dbx-handle"><@spring.message "label.archives"/></h3>
		<div class="dbx-content">
		  <#if archiveMonthList??>
		  <ul>
		  <#list archiveMonthList as month>
			<li><a href="<@blog.basePath/>archive/${month?string("yyyyMM")}/" title="${month?string("MMMM")} ${month?string("yyyy")}">${month?string("MMMM")} ${month?string("yyyy")}</a></li>
		  </#list>
		  </ul>
		  </#if>
		</div>
	</div>
    <!--sidebox end -->
      
    <!--sidebox start -->
    <div id="recommendLinks" class="dbx-box">
    	<h3 class="dbx-handle"><@spring.message "label.links"/></h3>
        <div class="dbx-content">
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
    <!--sidebox end -->
    
    <!--sidebox start -->
    <div id="meta" class="dbx-box">
        <h3 class="dbx-handle">Meta</h3>
        <div class="dbx-content">
          <ul>
              <li class="rss"><a href="${siteConfig.siteURL}/feed/atom/" title="Get posts delivered to your inbox">Subscribe</a></li>
              <li class="rss"><a href="http://code.google.com/p/myblog" title="Powered by Myblog">Myblog</a></li>
              <li class="login"><#if _myblog_authuser?exists><a href="<@blog.basePath/>admin/index.jspx" id="siteAdmin" title="<@spring.message "label.site.admin"/>"><@spring.message "label.site.admin"/></a><#else><a href="<@blog.basePath/>admin/login.jspx" id="login" title="<@spring.message "label.login"/>"><@spring.message "label.login"/></a></#if></li>
		      <#if _myblog_authuser?exists><li class="login"><a href="<@blog.basePath/>admin/logout.jspx" id="about" title="<@spring.message "menu.logout"/>"><@spring.message "menu.logout"/></a></li></#if>
          </ul>
        </div>
    </div>
    <!--sidebox end -->
</div><!--/sidebar -->
</#macro>

<#macro blogNav current>
<#assign entryCategoryId = ""/>
<#if entry?exists>
	<#assign entryCategoryId = entry.categories[0].id/>
</#if>
      <div id="nav">
        <ul>
          <li class="page_item<#if current=='home'> current_page_item</#if>"><a href="<@blog.basePath/>" id="home" title="Return to the home page."><@spring.message "label.home"/></a></li>
		  <#if categories?exists>
		  <#list categories as category>
		  <#assign args = [category.name]>
		  <li class="page_item<#if (id?exists&&category.id==id)||(name?exists&&category.name==name)> current_page_item</#if><#if entryCategoryId?exists&&category.id==entryCategoryId> current_page_item</#if>"><a href="<@blog.basePath/>category/${category.name}/" id="c_${category.id}" title="${springMacroRequestContext.getMessage("label.view.all.entry",args)}">${category.name}</a></li>
		  </#list>
		  </#if>
		  <#if pages?exists>
		  <#list pages as p>
		  <li class="page_item<#if page?exists&&(page.name==p.name)> current_page_item</#if>"><a href="<@blog.basePath/>page/${p.name}.html" id="p_${p.id}" title="${p.title}">${p.title}</a></li>
		  </#list>
		  </#if>
		  <li class="page_item<#if current=='tags'> current_page_item</#if>"><a href="<@blog.basePath/>tags.html" id="tags" title="<@spring.message "label.tags"/>"><@spring.message "label.tags"/></a></li>
		  <li class="page_item<#if current=='search'> current_page_item</#if>"><a href="<@blog.basePath/>search.html" id="search" title="Search this blog">Search</a></li>
		  <li class="page_item<#if current=='links'> current_page_item</#if>"><a href="<@blog.basePath/>links.html" id="links" title="Blog Links">Links</a></li>
		  <#if _myblog_authuser?exists><li class="page_item"><a href="<@blog.basePath/>admin/index.jspx" id="siteAdmin" title="<@spring.message "label.site.admin"/>"><@spring.message "label.site.admin"/></a></li></#if>
        </ul>
      </div><!-- /nav -->
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