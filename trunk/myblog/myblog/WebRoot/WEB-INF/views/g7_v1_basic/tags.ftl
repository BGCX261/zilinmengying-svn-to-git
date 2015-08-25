<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<@blog.header siteConfig.siteName,"Tags","",""/>
<div id="page">
<div id="content">
	<div class="content">
    	<div class="post" id="links">
        	<h1><@spring.message "label.tags"/></h1>
        	<div class="mypost">
        	<#if tags?exists && (tags?size>0)>
				<p id="tagsCloud">
				<#list tags as tag>
	  			<#assign fontSize=9/>
	  			<#if (totalCount>0)>
	  			<#assign fontSize=9 + (tag.count/totalCount)*280/>
	  			</#if>
	  			<#if (fontSize>22)>
        			<#assign fontSize=22/>
    			</#if>
				<a href="<@blog.basePath/>tag/${tag.name}/" title="${tag.name}(${tag.count})" style="font-size: ${fontSize}pt;">${tag.name}</a>&nbsp;
				</#list>
				</p>
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