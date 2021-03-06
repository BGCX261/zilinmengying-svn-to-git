<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<#assign categoryName = ""/>
<#assign des=""/>
<#assign cn = ""/>
<#if category?exists>
<#assign categoryName="&raquo;" + category.name/>
</#if>
<#if ps?exists&&ps.items?exists>
<#list ps.items as entry>
<#if des!=""><#assign des = des+","/></#if>
<#assign des = des + entry.title/>
</#list>
</#if>
<#if category?exists>
   <#assign cn = category.name/>
</#if>
<@blog.blogHeader siteConfig.siteName + categoryName,cn,des,""/>
<body>
<div id="wp-container">
	<div id="wp-header">
		<h1><a href="<@blog.basePath/>" title="Return to the home page.">${siteConfig.siteName}</a></h1>
		<dfn>${siteConfig.siteSubName}</dfn>
	</div>
	<@blog.blogNav ""/>
	<div id="wp-content">
		<div id="wp-content-pri">
			<div class="pages"><#if ps?exists&&ps.pages?exists&&(ps.pages?size>0)><p><span>Pages(${ps.totalPage}):</span><#if (ps.currentPage>1)><a href="<@blog.basePath/>category/${category.name}/page/${ps.currentPage-1}/">&#171; previous</a></#if><#list ps.pages as p><#if p==ps.currentPage><span class="current">${p}</span><#elseif p==0><span>...</span><#else><a href="<@blog.basePath/>category/${category.name}/page/${p}/">${p}</a></#if></#list><#if (ps.currentPage>0)&&(ps.currentPage<ps.totalPage)><a href="<@blog.basePath/>category/${category.name}/page/${ps.currentPage+1}/">next &#187;</a></#if></p></#if></div>
<#if ps?exists&&(ps.items?size>0)>
<#list ps.items as entry>
			<div class="post" id="post-${entry.id}">
				<h2><a href="<@blog.basePath/>entry<#if entry.name?exists>/${entry.name}<#else>/id/${entry.id}</#if>.html" rel="bookmark" title="Permanent Link to ${entry.title}">${entry.title}</a></h2>
				<div class="post-meta">
					<p>
					<span class="post-meta-date">${entry.postTime?date}</span>
					<span class="post-meta-time">${entry.postTime?time}</span>
					<@spring.message "label.category"/>
					<#list entry.categories as category>
					<#assign args = [category.name]>
					<span class="post-meta-category"><a href="<@blog.basePath/>category/${category.name}/" title="${springMacroRequestContext.getMessage("label.view.all.entry",args)}" rel="category tag">${category.name}</a></span>
					</#list>
					<span class="post-meta-comments"><a href="<@blog.basePath/>entry<#if entry.name?exists>/${entry.name}<#else>/id/${entry.id}</#if>.html#respond" title="Comment on ${entry.title}"><@spring.message "label.comment"/></a></span>
					<#if entry.tags?exists&&(entry.tags?size>0)>
					<@spring.message "label.tag"/>
					<#list entry.tags as tag>
					<#assign args = [tag.name]>
					<span class="post-meta-tag"><a href="<@blog.basePath/>tag/${tag.name}/" title="${springMacroRequestContext.getMessage("label.view.all.entry",args)}" rel="category tag">${tag.name}</a></span>
					</#list></#if>
					<span>Views:${entry.hits}</span>
					</p>
				</div>
				<p>
			<#if (entry_index==1) && siteConfig.linkUnitsAds?exists &&(siteConfig.linkUnitsAds?length>0)>
			${siteConfig.linkUnitsAds}<br/>
			</#if>
			<#if (entry_index==0) && siteConfig.textAds?exists && (siteConfig.textAds?length>0)>
			${siteConfig.textAds}<br/>
			</#if>
				${entry.summary!}
				</p>
				<a href="<@blog.basePath/>entry<#if entry.name?exists>/${entry.name}<#else>/id/${entry.id}</#if>.html" rel="bookmark" title="Permanent Link to ${entry.title}">[more..]</a>
			</div>
</#list>
<#else>
  <h2>No entry found!</h2>
</#if>			
			<div class="pages"><#if ps?exists&&ps.pages?exists&&(ps.pages?size>0)><p><span>Pages(${ps.totalPage}):</span><#if (ps.currentPage>1)><a href="<@blog.basePath/>category/${category.name}/page/${ps.currentPage-1}/">&#171; previous</a></#if><#list ps.pages as p><#if p==ps.currentPage><span class="current">${p}</span><#elseif p==0><span>...</span><#else><a href="<@blog.basePath/>category/${category.name}/page/${p}/">${p}</a></#if></#list><#if (ps.currentPage>0)&&(ps.currentPage<ps.totalPage)><a href="<@blog.basePath/>category/${category.name}/page/${ps.currentPage+1}/">next &#187;</a></#if></p></#if></div>
		</div>

	<@blog.blogSidebar/>
	</div>
<@blog.blogFooter/>
</div>
</body>
</html>