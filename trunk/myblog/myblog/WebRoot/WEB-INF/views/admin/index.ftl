<#import "/spring.ftl" as spring/>
<#assign xhtmlCompliant = true in spring>
<#import "inc/common.ftl" as blog/>
<#assign title = springMacroRequestContext.getMessage("admin.title")>
<@blog.header title,"AdminMenu"/>
<body>
    <div id="page">
<@blog.sysHeader/>
        <div id="content" class="clearfix">
            <div id="main">
                <h1><@spring.message "menu.main.heading"/></h1>
				<p><@spring.message "menu.main.message"/></p>
				<@blog.message/>
				<@blog.error/>
				<p id="check_up"></p>
				<div class="separator"></div>
				<ul class="glassList">
				    <li>
				        <a href="addEntry.jspx" title="<@spring.message "menu.add.entry"/>" ><@spring.message "menu.add.entry"/></a>
				    </li>
				    <li>
				    	<a href="listEntry.jspx" title="<@spring.message "menu.entry.management"/>" ><@spring.message "menu.entry.management"/></a>
				    </li>
				    <li>
				    	<a href="addPage.jspx" title="<@spring.message "menu.add.page"/>" ><@spring.message "menu.add.page"/></a>
				    </li>
                    <li>
                    	<a href="listPage.jspx" title="<@spring.message "menu.page.management"/>" ><@spring.message "menu.page.management"/></a>
                    </li>
				    <li>
				    	<a href="listComment.jspx" title="<@spring.message "menu.comment.management"/>" ><@spring.message "menu.comment.management"/></a>
				    </li>
				    <li>
				    	<a href="addLink.jspx" title="<@spring.message "menu.add.link"/>" ><@spring.message "menu.add.link"/></a>
				    </li>
				</ul>
            </div>
		 <@blog.menu "admin_index"/>
        </div>
        <@blog.footer/>
        </div>
    </div>
<script type="text/javascript">
/*
window.onload = function (){
	  	var url = 'up.jspx';
		var pars = 'up=1';
		var target = 'check_up';
		var myAjax = new Ajax.Updater(target, url, {method: 'get',	parameters: pars, evalScripts: 'yes'});
}*/
</script>
</body>
</html>
