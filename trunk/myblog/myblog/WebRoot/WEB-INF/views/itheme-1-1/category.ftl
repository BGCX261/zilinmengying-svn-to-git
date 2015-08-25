<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<#assign categoryName = ""/>
<#assign des=""/>
<#if category?exists>
<#assign categoryName="&raquo;" + category.name/>
</#if>
<#if page?exists&&page.items?exists>
<#list page.items as entry>
<#if des!=""><#assign des = des+","/></#if>
<#assign des = des + entry.title/>
</#list>
</#if>
<#assign cn = ""/>
<#if category?exists>
   <#assign cn = category.name/>
</#if>
<@blog.blogHeader siteConfig.siteName + categoryName,cn,des,""/>
    <div id="left-col">
	 <@blog.blogNav ""/>
      <div id="content">
      <div class="page-nav"><#if ps?exists&&ps.pages?exists&&(ps.pages?size>0)><#if (ps.currentPage>1)><span class="previous-entries"><a href="<@blog.basePath/>category/${cn}/page/${ps.currentPage-1}/">Previous Entries</a></span><#else><span class="previous-entries"></span></#if><#if (ps.currentPage>0)&&(ps.currentPage<ps.totalPage)><span class="next-entries"><a href="<@blog.basePath/>category/${cn}/page/${ps.currentPage+1}/">Next Entries</a></span><#else><span class="next-entries"></span></#if></#if></div><!-- /page nav -->
	  <#if ps?exists&&(ps.items?size>0)>
      <#list ps.items as entry>
        <div class="post" id="post-${entry.id}">
		  <div class="date"><span>${entry.postTime?string("MMM")}</span> ${entry.postTime?string("dd")}</div>
		  <div class="title">
          <h2><a href="<@blog.basePath/>entry<#if entry.name?exists>/${entry.name}<#else>/id/${entry.id}</#if>.html" rel="bookmark" title="Permanent Link to ${entry.title}">${entry.title}</a></h2>
          <div class="postdata">
            <span class="category"><#list entry.categories as category><#assign args = [category.name]><#if (category_index>0)> ,</#if><a href="<@blog.basePath/>category/${category.name}/" title="${springMacroRequestContext.getMessage("label.view.all.entry",args)}" rel="category tag">${category.name}</a></#list></span>
            <span class="tag"><#list entry.tags as tag><#assign args = [tag.name]><#if (tag_index>0)> ,</#if><a href="<@blog.basePath/>tag/${tag.name}/" title="${springMacroRequestContext.getMessage("label.view.all.entry",args)}" rel="category tag">${tag.name}</a></#list></span>
            <span class="comments"><a href="<@blog.basePath/>entry<#if entry.name?exists>/${entry.name}<#else>/id/${entry.id}</#if>.html<#if (entry.commentSize>0)>#comments<#else>#respond</#if>" title="Comment on ${entry.title}"><#if (entry.commentSize>0)><#assign args = [entry.commentSize]>${springMacroRequestContext.getMessage("label.comment.count",args)}<#else><@spring.message "label.comment"/>&#187;</#if></a>&nbsp;&nbsp;Hits:${entry.hits}</span>
          </div>
		  </div>
          <div class="entry">
			<#if (entry_index==1) && siteConfig.linkUnitsAds?exists &&(siteConfig.linkUnitsAds?length>0)>
			${siteConfig.linkUnitsAds}<br/>
			</#if>
			<#if (entry_index==0) && siteConfig.textAds?exists && (siteConfig.textAds?length>0)>
			${siteConfig.textAds}<br/>
			</#if>
            ${entry.summary!}
            <p>
            <a href="<@blog.basePath/>entry<#if entry.name?exists>/${entry.name}<#else>/id/${entry.id}</#if>.html" rel="bookmark" title="Permanent Link to ${entry.title}">[more..]</a>
            </p>
          </div><!--/entry -->
        </div><!--/post -->
        </#list>
        <#else>
        	<h2>No entry found!</h2>
        </#if>
        <div class="page-nav"><#if ps?exists&&ps.pages?exists&&(ps.pages?size>0)><#if (ps.currentPage>1)><span class="previous-entries"><a href="<@blog.basePath/>category/${cn}/page/${ps.currentPage-1}/">Previous Entries</a></span><#else><span class="previous-entries"></span></#if><#if (ps.currentPage>0)&&(ps.currentPage<ps.totalPage)><span class="next-entries"><a href="<@blog.basePath/>category/${cn}/page/${ps.currentPage+1}/">Next Entries</a></span><#else><span class="next-entries"></span></#if></#if></div><!-- /page nav -->
      </div><!--/content -->
      <@blog.blogFooter/>
    </div><!--/left-col -->
    <@blog.blogSidebar/>
<@blog.blogBottom/>