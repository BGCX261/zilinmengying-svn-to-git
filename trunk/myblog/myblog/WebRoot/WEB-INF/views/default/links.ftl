<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<@blog.blogHeader siteConfig.siteName,"Links","",""/>
<body>
<div id="wp-container">
	<div id="wp-header">
		<h1><a href="<@blog.basePath/>" title="Return to the home page.">${siteConfig.siteName}</a></h1>
		<dfn>${siteConfig.siteSubName}</dfn>
	</div>
	<@blog.blogNav "links"/>	
	<div id="wp-content">
		<div id="wp-content-pri">
		${siteConfig.linkUnitsAds?if_exists}
		<ul id="allLinks">
			<li id="linkcat"><h2><@spring.message "label.links"/></h2></li>
			<#if links?exists && (links?size>0)>
		  	<ul>
		  	<#list links as link>
		  		<li><a href="${link.URL}" title="${link.description!}"><#if link.imgURL?exists><img src="${link.imgURL}" alt="${link.name}"/><#else>${link.name}</#if></a>${link.description!}</li>
		  	</#list>
		  	</ul>
		  	<#else>
		  	<li id="linkcat"><h2>No links found!</h2></li>
		  	</#if>
		</ul>
		</div>
	<@blog.blogSidebar/>
	</div>
<@blog.blogFooter/>
</div>
</body>
</html>