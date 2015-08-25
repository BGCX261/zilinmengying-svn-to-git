<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<@blog.blogHeader siteConfig.siteName,"Search","Search " +siteConfig.siteName,""/>
<body>
<div id="wp-container">
	<div id="wp-header">
		<h1><a href="<@blog.basePath/>" title="Return to the home page.">${siteConfig.siteName}</a></h1>
		<dfn>${siteConfig.siteSubName}</dfn>
	</div>
	<@blog.blogNav "search"/>
	<div id="wp-content">
		<div id="wp-content-pri">
		<#if errors?exists>
		<div class="error">
		<#list errors as error>
			${error}<br />
		</#list>
		</div>
		</#if>
		<p>
		<form method="get" action="<@blog.basePath/>search.html">
			<input type="text" size="50" name="query" value="${query?if_exists}" />
			<input type="submit" value="Search" class="btn" />
		</form>
		<#if searchResults?exists && (searchResults.hits?size>0)>
		<#list searchResults.hits as hit>
			<#if hit.alias=='entry'>
				<a href="<@blog.basePath/>entry<#if hit.data.name?exists>/${hit.data.name}<#else>/id/${hit.data.id}</#if>.html">${hit.data.title}</a> (score:${hit.score})<br/>
			</#if>
			<#if hit.alias=='comment'>
				<a href="<@blog.basePath/>entry<#if hit.data.entry.name?exists><#else>/id/${hit.data.entry.id}</#if>.html#c_${hit.data.id}">${hit.data.authorName}'s comment</a> (score:${hit.score})<br/>
			</#if>
			<p>
				${hit.getHighlightedText("content")}
			</p>
		</#list>
		Search took ${searchResults.searchTime}ms.<br/>
		<#else>
		<div><a href="<@blog.basePath/>search/spring/">spring</a> <a href="<@blog.basePath/>search/java/">java</a> <a href="<@blog.basePath/>search/ubuntu/">ubuntu</a> <a href="<@blog.basePath/>search/hibernate/">hibernate</a></div>
		</#if>
		</p>
		<div class="pages"><#if searchResults?exists&&searchResults.pages?exists&&(searchResults.pages?size>0)><p><span>Pages(${searchResults.pages?size}):</span><#list searchResults.pages as page><a href="<@blog.basePath/>search.html?query=${query}&amp;p=${page_index+1}">${page_index+1}</a></#list></#if></div>
		</div>
	<@blog.blogSidebar/>
	</div>
<@blog.blogFooter/>
</div>
</body>
</html>