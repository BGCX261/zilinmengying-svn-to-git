<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<@blog.header siteConfig.siteName,"Search","Search" +siteConfig.siteName,""/>
<div id="page">
<div id="content">
	<div class="content">
    	<div class="post" id="search">
        	<h1>Search</h1>
        	<div class="mypost">
        	<@blog.error/>
		<form method="get" action="<@blog.basePath/>/search.html">
			<input type="text" size="50" name="query" value="${query?if_exists}" />
			<input type="submit" id="submit" class="button" value="Search"/>
		</form>
		<#if searchResults?exists && (searchResults.hits?size>0)>
		<ol>
		<#list searchResults.hits as hit>
			<li>
			<#if hit.alias=='entry'>
				<a href="<@blog.basePath/>entry<#if hit.data.name?exists>/${hit.data.name}<#else>/id/${hit.data.id}</#if>.html">${hit.data.title}</a> (score:${hit.score})<br/>
			</#if>
			<#if hit.alias=='comment'>
				<a href="<@blog.basePath/>entry<#if hit.data.entry.name?exists>/${hit.data.entry.name}<#else>/id/${hit.data.entry.id}</#if>.html#c_${hit.data.id}">${hit.data.authorName}'s comment</a> (score:${hit.score})<br/>
			</#if>
			<p>
				${hit.getHighlightedText("content")}
			</p>
			</li>
		</#list>
		</ol>
		Search took ${searchResults.searchTime}ms.<br/>
		<#else>
		<div><a href="<@blog.basePath/>search/spring/">spring</a> <a href="<@blog.basePath/>search/java/">java</a> <a href="<@blog.basePath/>search/ubuntu/">ubuntu</a> <a href="<@blog.basePath/>search/hibernate/">hibernate</a></div>
		</#if>
				</p>
			</div>
        </div>
	</div>
	<#if searchResults??&&searchResults.pages??&&(searchResults.pages?size>0)>
	<div id="navi">
		<div class="alignleft"><#if (p>1)><a href="<@blog.basePath/>/search.html?query=${query}&amp;p=${p-1}">&laquo; Previous Entries</a></#if></div>
		<div class="alignright"><#if (p>0)&&(p<searchResults.pages?size)><a href="<@blog.basePath/>/search.html?query=${query}&amp;p=${p+1}">Next Entries &raquo;</a></#if></div>
	</div>
	</#if>
</div><!-- content --> 
<@blog.sidebar false/>
</div><!-- page -->
<@blog.footer/>
</body>
</html>