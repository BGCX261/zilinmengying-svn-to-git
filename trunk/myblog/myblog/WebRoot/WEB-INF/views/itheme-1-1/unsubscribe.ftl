<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<@blog.blogHeader siteConfig.siteName,"",siteConfig.siteName,""/>
    <div id="left-col">
	 <@blog.blogNav ""/>
      <div id="content">
      <div class="post">
        <h2>Unsubscribe</h2>
		<div class="entry">
		<#if errors?exists>
		<div class="error">
			<#list errors as err>
				${err}<br />
			</#list>
		</div>
		</#if>
		<#if msg??>
		<div class="message">
			${msg}<br />
		</div>
		</#if>
		</div><!--/entry -->
	  </div><!--/post -->
  </div><!--/content -->
  <@blog.blogFooter/>
</div><!--/left-col -->
    <@blog.blogSidebar/>
<@blog.blogBottom/>