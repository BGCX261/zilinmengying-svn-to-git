<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<#assign des=""/>
<#if page?exists&&page.items?exists>
<#list page.items as entry>
<#if des!=""><#assign des = des+","/></#if>
<#assign des = des + entry.title/>
</#list>
</#if>
<@blog.blogHeader siteConfig.siteName,"Tags",des,""/>
<body>
<div id="wp-container">
	<div id="wp-header">
		<h1><a href="<@blog.basePath/>" title="Return to the home page.">${siteConfig.siteName}</a></h1>
		<dfn>${siteConfig.siteSubName}</dfn>
	</div>
	<@blog.blogNav "tags"/>
	<div id="wp-content">
		<div id="wp-content-pri">
		${siteConfig.linkUnitsAds?if_exists}
	  	<#if tags?exists && (tags?size>0)>
	  	<p id="tagsCloud" class="noIndent">
	  	<#list tags as tag>
	  		<#assign fontSize=9/>
	  		<#if (totalCount>0)>
	  		<#assign fontSize=9 + (tag.count/totalCount)*280/>
	  		</#if>
	  		<#if (fontSize>22)>
        			<#assign fontSize=22/>
    		</#if>
	  		<a href="<@blog.basePath/>tag/${tag.name}/" title="${tag.name}(${tag.count})" style="font-size: ${fontSize}pt;">${tag.name}</a>
	  	</#list>
	  	</p>
	  	<#else>
	  	<h2>No tags found!</h2>
	  	</#if>
		</div>
	<@blog.blogSidebar/>
	</div>
<@blog.blogFooter/>
</div>
</body>
</html>