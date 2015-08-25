<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<@blog.blogHeader siteConfig.siteName,"Search","Search " +siteConfig.siteName,""/>
    <div id="left-col">
	 <@blog.blogNav "search"/>
      <div id="content">
      <div class="post" id="search">
        <h2>Search</h2>
		<div class="entry">
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
		</div><!--/entry -->
		<div class="page-nav"><#if searchResults?exists&&searchResults.pages?exists&&(searchResults.pages?size>0)><#if (p>1)><span class="previous-entries"><a href="<@blog.basePath/>search.html?query=${query}&amp;p=${p-1}">Previous Entries</a></span><#else><span class="previous-entries"></span></#if><#if (p>0)&&(p<searchResults.pages?size)><span class="next-entries"><a href="<@blog.basePath/>search.html?query=${query}&amp;p=${p+1}">Next Entries</a></span><#else><span class="next-entries"></span></#if></#if></div><!-- /page nav -->
	</div><!--/post -->
  </div><!--/content -->
  <@blog.blogFooter/>
</div><!--/left-col -->
    <@blog.blogSidebar/>
<@blog.blogBottom/>