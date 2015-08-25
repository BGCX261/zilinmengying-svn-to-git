<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<#assign keywords=""/>
<#assign entrySummary=""/>
<#assign entrytitle=""/>
<#if entry?exists>
<#assign entrySummary=entry.summary/>
<#assign entrytitle=entry.title/>
<#list entry.tags as tag>
<#assign keywords= keywords + tag.name/><#if tag_has_next><#assign keywords= keywords + ","/></#if>
</#list>
</#if>
<@blog.header entrytitle + "&raquo;" + siteConfig.siteName,keywords,entrySummary,""/>
<div id="page">
<div id="content">
	<#if entry??>
	<div class="content">
    	<div class="post" id="post-${entry.id}">
        	<h1><a href="<@blog.basePath/>/entry<#if entry.name?exists>/${entry.name}<#else>/id/${entry.id}</#if>.html" rel="bookmark">${entry.title}</a></h1></div>
			<div class="entry">
				<@spring.message "label.category"/>
				<#list entry.categories as category><#assign args = [category.name]><a href="<@blog.basePath/>/category/${category.name}/" title="${springMacroRequestContext.getMessage("label.view.all.entry",args)}" rel="category tag" >${category.name}</a><#if category_has_next>,</#if></#list>&#124;
			</div>
			<div class="date">
				Posted on <span class="postdate">${entry.postTime?string("MMMM dd,yyyy HH:mm")} Views:${entry.hits}</span>
			</div>
			<div class="mypost">
				<p>${entry.content}</p>
			</div>
			<div class="tag_tb">
				Tags : <#list entry.tags as tag><#assign args = [tag.name]><a href="<@blog.basePath/>/tag/${tag.name}/" rel="tag" >${tag.name}</a><#if tag_has_next>,</#if></#list><br />
				Trackback url : u can <a href="<@blog.basePath/>/entry<#if entry.name?exists>/${entry.name}<#else>/id/${entry.id}</#if>.html" rel="trackback">trackback</a> from your own site
			</div>
		</div>
		<div id="comments">
			<div class="content">
			<#if comments?exists&&(comments?size>0)>
			<h1>${comments?size} Responses to &#8220;${entry.title}&#8221;</h1>
			<ol class="commentlist">
				<#list comments as comment>
				<li<#if (comment_index%2==0)> class="alt"</#if> id="comment-${comment.id}">
				<cite><#if comment.authorSite?exists &&(comment.authorSite?length>0)><a href="${comment.authorSite}" rel="external nofollow">${comment.authorName}</a><#else>${comment.authorName}</#if></cite> Says:<br />
				<small class="commentmetadata"><a name="c_${comment.id}">October 12th, 2007 at 1:01 am</a></small>
				<p>${comment.content}</p>
				</li>
				</#list>
			</ol>
			</#if>
			<h1>Leave a Reply</h1>
			<a name="respond"/>
			<form action="<@blog.basePath/>/postComment.jhtml" method="post" id="commentform">
			<#if _myblog_authuser??>
			<p>Logged in as <a href="<@blog.basePath/>/admin/editProfile.jspx?id=${_myblog_authuser.id}">${_myblog_authuser.username}</a>. <a href="<@blog.basePath/>/admin/logout.jspx" title="Log out of this account">Logout &raquo;</a></p>
			<#else>
			<p><input type="text" name="authorName" id="authorName" value="${authorName?if_exists}" size="24" tabindex="1" />
			<label for="authorName"><small><@spring.message "comment.authorName"/> (required)</small></label></p>
			<p><input type="text" name="authorMail" id="authorMail" value="${authorMail?if_exists}" size="24" tabindex="2" />
			<label for="authorMail"><small><@spring.message "comment.authorMail"/> (required)</small></label></p>
			<p><input type="text" name="authorSite" id="authorSite" value="${authorSite?if_exists}" size="24" tabindex="3" />
			<label for="authorSite"><small><@spring.message "comment.website"/></small></label></p>
			</#if>
			<p><input type="text" name="code" id="code" value="" size="24" tabindex="3" /><img src="<@blog.basePath/>/identifyingCode" alt="Identifying Code" onclick="this.src='<@blog.basePath/>/identifyingCode?now=' + new Date().getTime()" />
			<label for="code"><small><@spring.message "comment.code"/></small></label></p>
			<p><textarea name="content" id="comment" cols="60%" rows="10" tabindex="4"></textarea></p>
			<p><input name="submit" type="submit" id="submit"  tabindex="5" value="Submit Comment" />
			<input type="hidden" name="entryId" value="${entry.id}" />
			</p>
			</form>
		</div></div>
	<#else>
	<div class="content">
	<h3>No Entry found!</h3>
	</div>
	</#if>
</div><!-- content --> 
<@blog.sidebar false/>
</div><!-- page -->
<@blog.footer/>
</body>
</html>