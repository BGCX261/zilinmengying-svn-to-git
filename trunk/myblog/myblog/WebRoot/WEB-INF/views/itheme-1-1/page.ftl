<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<#assign title=""/>
<#if page?exists>
<#assign title=page.title/>
</#if>
<@blog.blogHeader siteConfig.siteName,title,title +siteConfig.siteName,""/>
    <div id="left-col">
	 <@blog.blogNav ""/>
      <div id="content">
      <#if page?exists>
      <div class="post" id="${page.name}">
        <h2>${page.title}</h2>
		<div class="entry">
		<p>
		${page.content}
		</p>
		</div><!--/entry -->
	  </div><!--/post -->
	  <#else>
	  <h2>No page found!</h2>
	  </#if>
  </div><!--/content -->
  <@blog.blogFooter/>
</div><!--/left-col -->
    <@blog.blogSidebar/>
<@blog.blogBottom/>