<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<#assign title=""/>
<#if page?exists>
<#assign title=page.title/>
</#if>
<@blog.header siteConfig.siteName,title,title +siteConfig.siteName,""/>
<div id="page">
<div id="content">
	<#if page??>
	<div class="content">
    	<div class="post" id="post-${page.id}">
        	<h1>${page.title}</h1>
        	<div class="mypost">
				<p>${page.content}</p>
			</div>
        </div>
	</div>
	<#else>
	<div class="content">
	<h3>No Page found!</h3>
	</div>
	</#if>
</div><!-- content --> 
<@blog.sidebar false/>
</div><!-- page -->
<@blog.footer/>
</body>
</html>