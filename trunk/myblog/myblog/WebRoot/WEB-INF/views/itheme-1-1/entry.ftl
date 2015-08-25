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
    <div id="left-col">
	 <@blog.blogNav ""/>
<div id="content">
  <#if entry?exists>
  <div class="post-nav"><span class="previous"><#if previous?exists><a href="<@blog.basePath/>entry<#if previous.name?exists>/${previous.name}<#else>/id/${previous.id}</#if>.html">${previous.title}</a></#if></span> <span class="next"><#if next?exists><a href="<@blog.basePath/>entry<#if next.name?exists>/${next.name}<#else>/id/${next.id}</#if>.html">${next.title}</a></#if></span></div>
  <div class="post" id="post-${entry.id}">
  	<div class="date"><span>${entry.postTime?string("MMM")}</span> ${entry.postTime?string("dd")}</div>
	<div class="title">
      <h2><a href="<@blog.basePath/>entry<#if entry.name?exists>/${entry.name}<#else>/id/${entry.id}</#if>.html" rel="bookmark" title="Permanent Link to ${entry.title}">${entry.title}</a></h2>
          <div class="postdata"><span class="category"><#list entry.categories as category><#assign args = [category.name]><#if (category_index>0)> ,</#if><a href="<@blog.basePath/>category/${category.name}/" title="${springMacroRequestContext.getMessage("label.view.all.entry",args)}" rel="category tag">${category.name}</a></#list></span>
            <span class="tag"><#list entry.tags as tag><#assign args = [tag.name]><#if (tag_index>0)> ,</#if><a href="<@blog.basePath/>tag/${tag.name}/" title="${springMacroRequestContext.getMessage("label.view.all.entry",args)}" rel="category tag">${tag.name}</a></#list></span>
            <span class="right mini-add-comment"><a href="#respond">Add comments</a>&nbsp;&nbsp;Hits:${entry.hits}</span></div>
		  </div>
          <div class="entry">
            ${siteConfig.imageAds?if_exists}
            <p>${entry.content}</p>
            <br />
            <#if relatedEntries??&&(relatedEntries?size>0)>
            <strong><@spring.message "label.related.posts"/></strong>
            <ul>
            <#list relatedEntries as en>
            	<li><a href="<@blog.basePath/>entry<#if entry.name?exists>/${entry.name}<#else>/id/${entry.id}</#if>.html">${en.title}</a></li>
            </#list>
            </ul>
            </#if>
		  </div><!--/entry -->
		  
		  <div id="commingReqs">
		  </div>
		  <div>Trackback:<a href="<@blog.basePath/>entry<#if entry.name?exists>/${entry.name}<#else>/id/${entry.id}</#if>.html" rel="bookmark" title="Permanent Link to ${entry.title}"><#if entry.name?exists>${entry.name}<#else>${entry.id}</#if></a></div>
		<!-- You can start editing here. -->
	<#if comments?exists&&(comments?size>0)>
	<h3 id="comments"><#if comments?exists&&(comments?size=1)>One<#elseif comments?exists&&(comments?size>1)>${comments?size}</#if> Responses to &#8220;${entry.title}&#8221;</h3> 
	<ol class="commentlist">
	    <#list comments as comment>
		<li<#if (comment_index%2==0)> class="alt"</#if> id="comment-${comment.id}">
			<cite><#if comment.authorSite?exists &&(comment.authorSite?length>0)><a href="${comment.authorSite}" rel="external nofollow">${comment.authorName}</a><#else>${comment.authorName}</#if></cite> Says:
			<br />
			<small class="commentmetadata"><a name="c_${comment.id}">${comment.postTime?string("EEEE, MMMM dd, yyyy, hh:mm:ss a '('zzz')'")}</a> </small>
			<p>${comment.content}</p>
		</li>
		</#list>
	</ol>
    </#if>
	<#if errors?exists>
	<div class="error">
		<#list errors as err>
			<li>${err}</li>
		</#list>
	</div>
	</#if>
    <h3 id="respond">Leave a Reply</h3>
	<form action="<@blog.basePath/>postComment.jhtml" method="post" id="commentform">
	<#if !_myblog_authuser?exists>
	<p><input type="text" name="authorName" id="authorName" value="${authorName?if_exists}" size="22" tabindex="1" />
	<label for="authorName"><strong><@spring.message "comment.authorName"/></strong> (required)</label></p>
	<p><input type="text" name="authorMail" id="authorMail" value="${authorMail?if_exists}" size="22" tabindex="2" />
	<label for="authorMail"><strong><@spring.message "comment.authorMail"/></strong> (required)</label></p>
	<p><input type="text" name="authorSite" id="authorSite" value="${authorSite?if_exists}" size="22" tabindex="3" />
	<label for="authorSite"><strong><@spring.message "comment.website"/></strong></label></p>
	<#else>
	<p>Logged in as <a href="<@blog.basePath/>admin/editProfile.jspx?id=${_myblog_authuser.id}">${_myblog_authuser.username}</a>. <a href="<@blog.basePath/>admin/logout.jspx" title="Log out of this account">Logout &raquo;</a></p>
	</#if>
	<p><input type="text" name="code" id="code" value="" size="22" tabindex="4" /><img src="<@blog.basePath/>identifyingCode" alt="Identifying Code" onclick="this.src='<@blog.basePath/>identifyingCode?now=' + new Date().getTime()" />
	<label for="code"><strong><@spring.message "comment.code"/></strong></label></p>
	<p><textarea name="content" id="commentContent" cols="100%" rows="10" tabindex="5">${content?if_exists}</textarea></p>
	<p><input name="submit" type="submit" id="submit" tabindex="5" value="Submit Comment" />
	<input type="hidden" name="entryId" value="${entry.id}" />
	</p>
	</form>
	</div><!--/post -->
	<#else>
	<h2>No entry Found!</h2>
	</#if>
  </div><!--/content -->
  <@blog.blogFooter/>
  <script type='text/javascript' src='<@blog.basePath/>dwr/interface/BlogFacade.js'></script>
  <script type='text/javascript' src='<@blog.basePath/>dwr/engine.js'></script>
  <script type="text/javascript">
  	window.onload = function(){
  		var url = document.URL;
  		BlogFacade.getRequestListByUri(url,10,function(cReqs){
  			var str;
  			str = "<div><ul>"
  			for(var i=0; i<cReqs.length; i++){
  				str += "<li><a href='" + cReqs[i].referer +"'>" + cReqs[i].referer + "</a></li>"
  			}
  			str += "</ul></div>"
  			$("commingReqs").innerHTML=str;
  		});
  	}
  </script>
</div><!--/left-col -->
<@blog.blogSidebar/>
<@blog.blogBottom/>