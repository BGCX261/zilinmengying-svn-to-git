<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<@blog.blogHeader siteConfig.siteName,"404 Page Not Found","404 Page Not Found " +siteConfig.siteName,""/>
    <div id="left-col">
	 <@blog.blogNav "search"/>
      <div id="content">
      <div class="post" id="search">
      	<p>
      	<h2>
        <@spring.message "label.404"/> <a href="<@blog.basePath/>">[${siteConfig.siteName}]</a>
        </h2>
        </p>
	</div><!--/post -->
  </div><!--/content -->
  <@blog.blogFooter/>
</div><!--/left-col -->
    <@blog.blogSidebar/>
<@blog.blogBottom/>