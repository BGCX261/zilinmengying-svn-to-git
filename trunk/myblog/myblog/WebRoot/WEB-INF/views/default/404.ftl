<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<@blog.blogHeader siteConfig.siteName,"404 Not Found","404 Not Found " +siteConfig.siteName,""/>
<body>
<div id="wp-container">
	<div id="wp-header">
		<h1><a href="<@blog.basePath/>" title="Return to the home page.">${siteConfig.siteName}</a></h1>
		<dfn>${siteConfig.siteSubName}</dfn>
	</div>
	<@blog.blogNav "search"/>
	<div id="wp-content">
		<div id="wp-content-pri">
		<p>
		<h2><@spring.message "label.404"/> <a href="<@blog.basePath/>">[${siteConfig.siteName}]</a></h2>
		</p>
		</div>
	<@blog.blogSidebar/>
	</div>
<@blog.blogFooter/>
</div>
</body>
</html>