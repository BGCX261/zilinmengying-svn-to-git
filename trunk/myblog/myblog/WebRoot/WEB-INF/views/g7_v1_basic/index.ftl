<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<@blog.header siteConfig.siteName,"","",""/>
<div id="page">
<div id="content">
	<#if ps?exists&&(ps.items?size>0)>
	<#list ps.items as entry>
	<div class="content">
    	<div class="post" id="post-${entry.id}">
        	<h1><a href="<@blog.basePath/>/entry<#if entry.name?exists>/${entry.name}<#else>/id/${entry.id}</#if>.html" rel="bookmark">${entry.title}</a></h1>
        </div>
		<div class="entry">
			<@spring.message "label.category"/>
			<#list entry.categories as category><#assign args = [category.name]><a href="<@blog.basePath/>/category/${category.name}/" title="${springMacroRequestContext.getMessage("label.view.all.entry",args)}" rel="category tag" >${category.name}</a><#if category_has_next>,</#if></#list>&#124; <span class="comments"><a href="<@blog.basePath/>/entry<#if entry.name?exists>/${entry.name}<#else>/id/${entry.id}</#if>.html<#if (entry.commentSize>0)>#comments<#else>#respond</#if>" title="Comment on ${entry.title}" ><#if (entry.commentSize>0)><#assign args = [entry.commentSize]>${springMacroRequestContext.getMessage("label.comment.count",args)}<#else><@spring.message "label.comment"/></#if></a></span>
		</div>
		<div class="date">
			Posted on <span class="postdate">${entry.postTime?string("MMMM dd,yyyy HH:mm")} Views:${entry.hits}</span>
		</div>
		<div class="mypost">
				<p>${entry.summary!}</p>
				<a href="<@blog.basePath/>/entry<#if entry.name?exists>/${entry.name}<#else>/id/${entry.id}</#if>.html" rel="bookmark" title="Permanent Link to ${entry.title}">[more..]</a>
		</div>					
		<div class="tag_tb">Tags : <#list entry.tags as tag><#assign args = [tag.name]><a href="<@blog.basePath/>/tag/${tag.name}/" rel="tag" >${tag.name}</a><#if tag_has_next>,</#if></#list><br /></div>
	</div>
	</#list>
	<#else>
	<div class="content">
	<h3>No entry found!</h3>
	</div>
	</#if>
<div id="pagenavi">
<div class="wp-pagenavi">
<#if ps?exists&&ps.pages?exists&&(ps.pages?size>0)>
<@blog.indexPageNav ps/>
</#if>
</div>
</div>
</div><!-- content --> 
<@blog.sidebar true/>
</div><!-- page -->
<@blog.footer/>
</body>
</html>