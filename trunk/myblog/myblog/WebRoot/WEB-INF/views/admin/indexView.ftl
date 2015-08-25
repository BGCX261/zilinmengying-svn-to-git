<#import "/spring.ftl" as spring/>
<#import "inc/common.ftl" as blog/>
<#assign title = springMacroRequestContext.getMessage("admin.title")>
<@blog.header title,"AdminMenu"/>
<body>
    <div id="page">
<@blog.sysHeader/>
        <div id="content" class="clearfix">
            <div id="main">
                <h1>欢迎！</h1>
				<div class="separator"></div>
				<p>
				Use the Index button to index the database using Compass::Gps. The operation will
				delete the current index and reindex the database based on the mappings and devices
				defined in the Compass::Gps configuration context.
				<form method="post" action="indexView.jspx">
					<@spring.bind "command.doIndex"/>
					<input type="hidden" name="doIndex" value="true" />
				    <input type="submit" value="Index"/>
				</form>
				<#if indexResults?exists>
					Indexing took: ${indexResults.indexTime}ms.
				</#if>
				<p>
            </div>
		 <@blog.menu "admin_other"/>
        </div>
        <@blog.footer/>
        </div>
    </div>
</body>
</html>
