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

<#import "/spring.ftl" as spring/>

<#--
 *
 * The contextPath
 -->
<#macro basePath><#if springMacroRequestContext.getContextPath()=="/"><#else>${springMacroRequestContext.getContextPath()}</#if></#macro>

<#--
 *
 * The blog's header.
 -->
<#macro header title,keywords,description,other>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN" xml:lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="application/xhtml+xml; charset=utf-8" />
	<meta http-equiv="Content-Language" content="zh-CN" />
	<meta content="all" name="robots" />
	<meta name="author" content="莫多泡泡(somebody)" />
	<meta name="Copyright" content="jdkcn.com,All Rights Reserved,2001-2007" />
	<meta name="MSSmartTagsPreventParsing" content="true" />
	<meta name="keywords"  content="<#if keywords?exists && (keywords?length>0)>${keywords}<#else>${siteConfig.siteSimpleAbout}</#if>" />
	<meta name="description" content="<#if description?exists &&(description?length>0)>${description}<#else>${siteConfig.siteSimpleAbout}</#if>" />
	<link rel="made" rev="made" href="mailto:rory.cn(at)gmail.com" />
	<link rel="alternate" type="application/rss+xml" href="<@basePath/>/feed/rss2/" title="订阅 ${siteConfig.siteName} 所有文章(rss2)" />
	<link rel="alternate" type="application/atom+xml" href="<@basePath/>/feed/atom/" title="订阅 ${siteConfig.siteName} 所有文章(atom)" />
	<link rel="stylesheet" href="<@basePath/>/styles/g7_v1_basic/style.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="<@basePath/>/styles/g7_v1_basic/pagenavi-css.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="<@basePath/>/styles/messages.css" type="text/css" media="all" />
	<link rel="icon" href="<@basePath/>/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="<@basePath/>/favicon.ico" type="image/x-icon" />
	<script type="text/javascript" src="<@basePath/>/js/prototype.js"></script>
	<#if other?exists>
	${other}
	</#if>
	<title>${title}</title>
	<style type="text/css">.recentcomments a{display:inline !important;padding: 0 !important;margin: 0 !important;}</style>
</head>
<body>
<div id="header">
  <div id="ing">
	<div id="ing_info">
	  <div id="home">
	   <a href="<@basePath/>/" title="${siteConfig.siteName}"><img src="<@basePath/>/styles/g7_v1_basic/images/myblog_logo.gif" alt="${siteConfig.siteName}"/></a>
	  </div>
	  <strong><a href="<@basePath/>/">${siteConfig.siteName}</a></strong>  [${siteConfig.siteSubName}]
	</div>
  <div>
  <form method="get" id="searchform" action="<@basePath/>/search.html">
	<div>
	<input type="text" value="<#if query??>${query}</#if>" name="query" id="query"/>
	<input type="submit" id="searchsubmit" value="Search" />
	</div>
  </form>
</div>
</div>
</div>
<!-- header -->
</#macro>

<#--
 -- The blog's footer.
 -->
<#macro footer>
<div id="footer">
        <div class="ftr">Powered by <a href="http://code.google.com/p/myblog" title="Myblog">Myblog[${siteConfig.getAppVersion()}]</a> , G7 v1 Theme produced by <a href="http://www.underone.com/" title="Theme by underone" >underone</a> @2007 , <a href="http://creativecommons.org/licenses/by-nc-sa/2.5/cn/deed.zh" title="知识共享署名-非商业性使用-相同方式共享 2.5 中国大陆许可协议">Creative Commons</a> , <#if siteConfig.getICPNumber()?exists && (siteConfig.getICPNumber()?length>0)>
		&nbsp;
		<a href="http://www.miibeian.gov.cn"><b>${siteConfig.getICPNumber()}</b></a>
		</#if>
 </div>
 </div><!-- footer -->  
</#macro>

<#--
 -- the sidebar
 -->
<#macro sidebar displayTag>
  <#if displayTag>
  <div id="sidebar_tag">
    <#if hotTags??>
	<#assign totalCount = 0/>
	<#list hotTags as t>
	<#assign totalCount = totalCount + t.count/>
	</#list>
	<#list hotTags as tag>
	<#assign fontSize=12/>
	<#if (totalCount>0)>
	<#assign fontSize=12 + (tag.count/totalCount)*100/>
	</#if>
	<#if (fontSize>20)>
        <#assign fontSize=20/>
    </#if>
	<a href="<@basePath/>/tag/${tag.name}/" title="${springMacroRequestContext.getMessage("label.topic.count",[tag.count])}" rel="tag" style="font-size:${fontSize}px; color:#999999;">${tag.name}</a>
	</#list>
	&nbsp;&nbsp;<a href="<@basePath/>/tags.html">[more..]</a>
	</#if>
  </div>
  </#if>
   <div id="sidebar">
        <div id="sidebar_1"><li class="feed"><h2>feed</h2>
        <ul>
          <li><a href="<@basePath/>/feed/rss2/">all feed</a></li>
          <li><a href="<@basePath/>/feed/atom/">all atom feed</a></li>
        </ul>
        </li>
		<li class="pagenav"><h2>pages</h2>
		<ul>
		<#if pages?exists>
		  <#list pages as p>
		  <li class="page_item"><a href="<@basePath/>/page/${p.name}.html" title="${p.title}">${p.title}</a></li>
		  </#list>
		  <li class="page_item"><a href="<@basePath/>/links.html" title="blogroll">Links</a></li>
		  <li class="page_item"><a href="<@basePath/>/tags.html" id="tags" title="<@spring.message "label.tags"/>"><@spring.message "label.tags"/></a></li>
		  <li class="page_item"><a href="<@basePath/>/search.html" id="search" title="Search this blog">Search</a></li>
		</#if>
		</ul>
		</li>
		<li class="categories">
			<h2><@spring.message "label.category"/></h2>
				<ul>
				<#if categories?exists>
				<#list categories as category>
				<#assign args = [category.name]>
					<li class="cat-item"><a href="<@basePath/>/category/${category.name}/" title="${springMacroRequestContext.getMessage("label.view.all.entry",args)}" >${category.name}</a> (${category.count})</li>
				</#list>
				</#if>
					<#-- 
					<li class="cat-item cat-item-3"><a href="#" title="没啥，软件，硬件，网站，都算上～">软硬一本</a> (290)
						<ul class='children'>
							<li class="cat-item cat-item-14"><a href="#" title="View all posts filed under 软件">软件</a> (117)</li>
							<li class="cat-item cat-item-15"><a href="#" title="View all posts filed under 硬件">硬件</a> (24)</li>
							<li class="cat-item cat-item-16"><a href="#" title="View all posts filed under 互联网" >互联网</a> (199)</li>
						</ul>
					</li>
					-->
				</ul>
		</li>
		<li class="newpost"><h2><@spring.message "label.recent.posts"/></h2>
			<ul>
			<#if recentEntries?exists>
			<#list recentEntries as entry>
				<li><a href="<@basePath/>/entry<#if entry.name?exists>/${entry.name}<#else>/id/${entry.id}</#if>.html" rel="bookmark" title="${entry.title}">${entry.title}</a></li>
   			</#list>
			</#if>
			</ul>
		</li>
		<li class="r_comments"><h2><@spring.message "label.recent.comments"/></h2>
			<ul>
			<#if recentComments?exists>
			<#list recentComments as comment>
				<li> <a href="<@basePath/>/entry<#if comment.entry.name?exists>/${comment.entry.name}<#else>/id/${comment.entry.id}</#if>.html#c_${comment.id}" title="${comment.authorName} says:"><#if (comment.content?length>20)>${comment.content?substring(0,20)?html}<#else>${comment.content}</#if></a></li>
			</#list>
			</#if>
			</ul>
		</li>
		</div>
  		<div id="sidebar_2">
			<li class="archives"><h2><@spring.message "label.archives"/></h2>
			<#if archiveMonthList??>
				<ul>
				<#list archiveMonthList as month>
					<li><a href="<@basePath/>/archive/${month?string("yyyyMM")}/" title="${month?string("MMMM")} ${month?string("yyyy")}">${month?string("MMMM")} ${month?string("yyyy")}</a></li>
				</#list>
				</ul>
			</#if>
			</li>
			<li class="linkcat"><h2><@spring.message "label.links"/></h2>
			<#if recommendLinks??>
				<ul>
				<#list recommendLinks as link>
					<li><a href="${link.URL}" title="${link.description}"><#if link.imgURL?exists><img src="${link.imgURL}" alt="${link.name}"/><#else>${link.name}</#if></a></li>
				</#list>
					<li><a href="<@blog.basePath/>/links.html" title="More links">More...</a></li>
				</ul>
			</#if>
			</li>
			<br />
			<br />
  		</div>
</div><!-- sidebar -->
</#macro>

<#--
 -- the frontpage's pages macro.
 -->
<#macro indexPageNav ps>
<#assign start=1/>
<#assign end=ps.currentPage/>
<#if (ps.currentPage>5)>
<#assign start=ps.currentPage-4/>
</#if>
<#if (ps.totalPage<=10)>
<#assign end=ps.totalPage/>
<#elseif ((ps.currentPage+5)>=ps.totalPage)>
<#assign end=ps.totalPage/>
<#else>
<#assign end=ps.currentPage+5/>
</#if>
<span class="pages">Page ${ps.currentPage} of ${ps.totalPage}</span><a href="<@basePath/>/index/1.html" title="&laquo; First">&laquo; First</a><#if (start>1)><span class="extend">...</span></#if><#list start..end as i><#if i==ps.currentPage><span class="current">${i}</span><#else><a href="<@basePath/>/index/${i}.html" title="${i}" >${i}</a></#if></#list><#if (end<ps.totalPage)><span class="extend">...</span></#if><a href="<@basePath/>/index/${ps.totalPage}.html" title="Last &raquo;" >Last &raquo;</a>
</#macro>

<#--
 -- the category's pages macro.
 -->
<#macro categoryPageNav category,ps>
<#assign start=1/>
<#assign end=ps.currentPage/>
<#if (ps.currentPage>5)>
<#assign start=ps.currentPage-4/>
</#if>
<#if (ps.totalPage<=10)>
<#assign end=ps.totalPage/>
<#elseif ((ps.currentPage+5)>=ps.totalPage)>
<#assign end=ps.totalPage/>
<#else>
<#assign end=ps.currentPage+5/>
</#if>
<span class="pages">Page ${ps.currentPage} of ${ps.totalPage}</span><a href="<@basePath/>/category/${category.name}/page/1/" title="&laquo; First">&laquo; First</a><#if (start>1)><span class="extend">...</span></#if><#list start..end as i><#if i==ps.currentPage><span class="current">${i}</span><#else><a href="<@basePath/>/category/${category.name}/page/${i}/" title="${i}" >${i}</a></#if></#list><#if (end<ps.totalPage)><span class="extend">...</span></#if><a href="<@basePath/>/category/${category.name}/page/${ps.totalPage}/" title="Last &raquo;" >Last &raquo;</a>
</#macro>

<#--
 -- the tag's pages macro.
 -->
<#macro tagPageNav tag,ps>
<#assign start=1/>
<#assign end=ps.currentPage/>
<#if (ps.currentPage>5)>
<#assign start=ps.currentPage-4/>
</#if>
<#if (ps.totalPage<=10)>
<#assign end=ps.totalPage/>
<#elseif ((ps.currentPage+5)>=ps.totalPage)>
<#assign end=ps.totalPage/>
<#else>
<#assign end=ps.currentPage+5/>
</#if>
<span class="pages">Page ${ps.currentPage} of ${ps.totalPage}</span><a href="<@basePath/>/tag/${tag.name}/page/1/" title="&laquo; First">&laquo; First</a><#if (start>1)><span class="extend">...</span></#if><#list start..end as i><#if i==ps.currentPage><span class="current">${i}</span><#else><a href="<@basePath/>/tag/${tag.name}/page/${i}/" title="${i}" >${i}</a></#if></#list><#if (end<ps.totalPage)><span class="extend">...</span></#if><a href="<@basePath/>/tag/${tag.name}/page/${ps.totalPage}/" title="Last &raquo;" >Last &raquo;</a>
</#macro>

<#--
 -- the archive's pages macro.
 -->
<#macro archivePageNav month,ps>
<#assign start=1/>
<#assign end=ps.currentPage/>
<#if (ps.currentPage>5)>
<#assign start=ps.currentPage-4/>
</#if>
<#if (ps.totalPage<=10)>
<#assign end=ps.totalPage/>
<#elseif ((ps.currentPage+5)>=ps.totalPage)>
<#assign end=ps.totalPage/>
<#else>
<#assign end=ps.currentPage+5/>
</#if>
<span class="pages">Page ${ps.currentPage} of ${ps.totalPage}</span><a href="<@basePath/>/archive/${month}/page/1/" title="&laquo; First">&laquo; First</a><#if (start>1)><span class="extend">...</span></#if><#list start..end as i><#if i==ps.currentPage><span class="current">${i}</span><#else><a href="<@basePath/>/archive/${month}/page/${i}/" title="${i}" >${i}</a></#if></#list><#if (end<ps.totalPage)><span class="extend">...</span></#if><a href="<@basePath/>/archive/${month}/page/${ps.totalPage}/" title="Last &raquo;" >Last &raquo;</a>
</#macro>