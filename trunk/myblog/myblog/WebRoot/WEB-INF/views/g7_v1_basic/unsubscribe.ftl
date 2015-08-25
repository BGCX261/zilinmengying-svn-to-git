<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<@blog.header siteConfig.siteName,"","",""/>
<div id="page">
<div id="content">
<div class="content">
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
</div>
</div><!-- content --> 
<@blog.sidebar false/>
</div><!-- page -->
<@blog.footer/>
</body>
</html>