<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<@blog.header siteConfig.siteName,"Blog Links","Blog Links " +siteConfig.siteName,""/>
<div id="page">
<div id="content">
	<div class="content">
    	<div class="post" id="links">
        	<h1><@spring.message "label.links"/></h1>
        	<div class="mypost">
        	<#if links?exists && (links?size>0)>
				<p><#list links as link><a href="${link.URL}" title="${link.description!}"><#if link.imgURL?exists><img src="${link.imgURL}" alt="${link.name}"/><#else>${link.name}</#if></a> - ${link.description!}<br /><br /></#list></p>
			</#if>
			</div>
        </div>
	</div>
</div><!-- content --> 
<@blog.sidebar false/>
</div><!-- page -->
<@blog.footer/>
</body>
</html>