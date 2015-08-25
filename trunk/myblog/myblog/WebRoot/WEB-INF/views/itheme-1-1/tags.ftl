<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<@blog.blogHeader siteConfig.siteName,"Tags","",""/>
    <div id="left-col">
	 <@blog.blogNav "tags"/>
      <div id="content">
      <div class="post" id="tags">
        <h2><@spring.message "label.tags"/></h2>
		<div class="entry">
		<p>
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
		</p>
		</div><!--/entry -->
	</div><!--/post -->
  </div><!--/content -->
  <@blog.blogFooter/>
</div><!--/left-col -->
    <@blog.blogSidebar/>
<@blog.blogBottom/>