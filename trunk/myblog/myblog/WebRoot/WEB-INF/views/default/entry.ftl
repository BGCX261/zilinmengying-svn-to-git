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
<@blog.blogHeader entrytitle + "&raquo;" + siteConfig.siteName,keywords,entrySummary,""/>
<body>
<div id="wp-container">
	<div id="wp-header">
		<h1><a href="<@blog.basePath/>" title="Return to the home page.">${siteConfig.siteName}</a></h1>
		<dfn>${siteConfig.siteSubName}</dfn>
	</div>
	<@blog.blogNav ""/>
	<div id="wp-content">
		<div id="wp-content-pri">
			<#if entry?exists>
			<div id="post-navigation-top">
				<div class="prev"><#if previous?exists><a href="<@blog.basePath/>entry<#if previous.name?exists>/${previous.name}<#else>/id/${previous.id}</#if>.html">${previous.title}</a> &raquo;</#if></div>
				<div class="next"><#if next?exists>&laquo; <a href="<@blog.basePath/>entry<#if next.name?exists>/${next.name}<#else>/id/${next.id}</#if>.html">${next.title}</a></#if></div>
			</div>
			<div class="post" id="post-${entry.id}">
				<h2>${entry.title}</h2>
				${siteConfig.imageAds?if_exists}
				<p>${entry.content}</p>
				<p id="post-meta-data">
				This entry was posted on <strong>${entry.postTime?string("EEEE, MMMM dd, yyyy, hh:mm:ss a '('zzz')'")}</strong> and is filed under 
				<#list entry.categories as category><#assign args = [category.name]><a href="<@blog.basePath/>category/${category.name}/" title="${springMacroRequestContext.getMessage("label.view.all.entry",args)}" rel="category tag">${category.name}</a></#list>
				.
				You can follow any responses to this entry through the <a href='#'>RSS 2.0</a> feed.
								You can <a href="#respond">leave a response</a>, or <a href="<@blog.basePath/>trackback/${entry.id}" rel="trackback">trackback</a> from your own site.
				</p>
			</div>
            <#if relatedEntries??>
            <strong><@spring.message "label.related.posts"/></strong>
            <ul>
            <#list relatedEntries as en>
            	<li><a href="<@blog.basePath/>entry<#if entry.name?exists>/${entry.name}<#else>/id/${entry.id}</#if>.html">${en.title}</a></li>
            </#list>
            </ul>
            </#if>
			<div id="comments">
				<#if comments?exists&&(comments?size>0)>
				<a name="view-comments"></a><h3><#if comments?exists&&(comments?size=1)>One<#elseif comments?exists&&(comments?size>1)>${comments?size}</#if> Response to &#8220;${entry.title}&#8221;</h3> 
				<#list comments as comment>
				<div class="<#if (comment_index%2==0)>comment-entry-alt<#else>comment-entry</#if>">
					<div class="comment-entry-meta">
						<cite>
							<a name="c_${comment.id}" class="content-comment-entry-meta-no">${comment_index+1}.</a>
							${comment.postTime?string("EEEE, MMMM dd, yyyy, hh:mm:ss a '('zzz')'")} <#if comment.authorSite?exists &&(comment.authorSite?length>0)><a href="${comment.authorSite}">${comment.authorName}</a><#else>${comment.authorName}</#if> wrote:
						</cite>
					</div>
					<div class="comment-entry-content">
						<p>
						${comment.content}
						</p>
					</div>
				</div>
				</#list>
				<#else>
				<p class="nocomments">Be the first to comment.</p>
				</#if>
				<div id="comments-form">
					<a name="respond"></a>
					<#if errors?exists>
					<div class="error">
						<#list errors as err>
							${err}<br />
						</#list>
					</div>
					</#if>
					<h3>Have your say</h3>
					<form action="<@blog.basePath/>postComment.jhtml" method="post" id="commentform">
					<#if !_myblog_authuser?exists>
					<label for="authorName" class="required"><@spring.message "comment.authorName"/></label>
					<input id="authorName" name="authorName" type="text" value="${authorName?if_exists}" size="20" maxlength="20" /><br />
					<label for="authorMail" class="required"><@spring.message "comment.authorMail"/></label>
					<input id="authorMail" name="authorMail" type="text" value="${authorMail?if_exists}" size="20" maxlength="50" /><br />
					<label for="authorSite"><@spring.message "comment.website"/></label>
					<input type="text" id="authorSite" name="authorSite" value="${authorSite?if_exists}" /><br />
					</#if>
					<label for="code" class="required"><@spring.message "comment.code"/></label>
					<input id="code" name="code" type="text" value=""/><img src="<@blog.basePath/>identifyingCode" alt="Identifying Code" onclick="this.src='<@blog.basePath/>identifyingCode?now=' + new Date().getTime()" /><br />
					<textarea id="content" name="content" rows="15">${content?if_exists}</textarea><br />
					<input type="submit" id="submit" name="submit" value="Post Comment" class="button" />
					<input type="hidden" name="entryId" value="${entry.id}" />
					</form>
					<div id="comments-guidelines">
						<p>Fields in <strong style="color:#000;">bold</strong> are required. Email addresses are <strong>never</strong> published or distributed.</p>
						<p>Please keep comments relevant. Off-topic, offensive or inappropriate comments <em>may</em> be edited or removed.</p>
					</div>
				</div>
			</div>
			<#else>
			<div class="post" id="post">
				<h2>Not Found</h2>
				<p>Sorry, no posts matched your criteria.</p>
			</div>
			</#if>
		</div>
	<@blog.blogSidebar/>
	</div>
<@blog.blogFooter/>
</div>
</body>
</html>