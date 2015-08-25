<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<#assign title=""/>
<@blog.blogHeader siteConfig.siteName,title,title +siteConfig.siteName,""/>
<body>
<div id="wp-container">
	<div id="wp-header">
		<h1><a href="<@blog.basePath/>" title="Return to the home page.">${siteConfig.siteName}</a></h1>
		<dfn>${siteConfig.siteSubName}</dfn>
	</div>
	<@blog.blogNav ""/>	
	<div id="wp-content">
		<div id="wp-content-pri">
			<div class="post">
			<h2>Unsubscribe</h2>
			<p>
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
			</p>
			</div>
		</div>
	<@blog.blogSidebar/>
	</div>
<@blog.blogFooter/>
</div>
</body>
</html>