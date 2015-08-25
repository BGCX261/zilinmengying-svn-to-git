<#import "/spring.ftl" as spring/>
<#import "inc/common.ftl" as blog/>
<#assign title = springMacroRequestContext.getMessage("admin.title")>
<@blog.header title,"EntryMenu"/>
<body>
    <div id="page">
<@blog.sysHeader/>
        <div id="content" class="clearfix">
            <div id="main">
                <h1><@spring.message "menu.link.management"/></h1>
<@blog.message/>
<@blog.error/>
<#if links?exists&(links?size>0)>
<table cellpadding="0" cellspacing="0" class="table" id="link">
<thead>
<tr><th class="sortable"><@spring.message "label.link.name"/></th><th class="sortable"><@spring.message "label.link.description"/></th><th class="sortable"><@spring.message "label.link.url"/></th><th class="sortable"><@spring.message "label.link.order"/></th><th class="sortable"><@spring.message "label.link.operation"/></th></tr>
</thead>
<tbody>
<#list links as link>
<tr class="<#if (link_index%2==0)>odd<#else>even</#if>">
	<td><#if link.imgURL?exists><img alt="Logo url" title="${link.imgURL}" src="<@blog.basePath/>img/image.png"/></#if>&nbsp;${link.name}</td>
	<td title="${link.description}"><#if (link.description?length>10)>${link.description?substring(0,10)?html}<#else>${link.description}</#if></td>
	<td>${link.URL}</td>
	<td>${link.order!}</td>
	<td><a href="<@blog.basePath/>admin/editLink.jspx?id=${link.id}"><img alt="Edit this link" title="Edit this link" src="<@blog.basePath/>img/page_white_edit.png"/></a>&nbsp;<a href="editLink.jspx?id=${link.id}&amp;save=remove" onclick="return confirmDel('${link.name?html}')"><img alt="Delete this link" title="Delete this link" src="<@blog.basePath/>img/delete.png"></a></td>
</tr>
</#list>
</tbody>
</table>
</#if>
            </div>
		<@blog.menu "admin_content"/>
        </div>
        <@blog.footer/>
        </div>
    </div>
</body>
</html>
