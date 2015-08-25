<#import "/spring.ftl" as spring/>
<#import "inc/common.ftl" as blog/>
<#assign title = springMacroRequestContext.getMessage("admin.title")>
<@blog.header title,"EntryMenu"/>
<body>
    <div id="page">
<@blog.sysHeader/>
        <div id="content" class="clearfix">
            <div id="main">
                <h1><@spring.message "menu.category.management"/></h1>
<@blog.message/>
<@blog.error/>
<#if categories?exists&&(categories?size>0)>
<table cellpadding="0" cellspacing="0" class="table" id="category">
<thead>
	<th class="sortable"><@spring.message "label.category.name"/></th><th class="sortable"><@spring.message "label.category.description"/></th><th class="sortable"><@spring.message "label.category.operation"/></th>
</thead>
<tbody>
<#list categories as category>
<tr class="<#if (category_index%2==0)>odd<#else>even</#if>">
	<td>${category.name}</td>
	<td>${category.description?if_exists}</td>
	<td><a href="<@blog.basePath/>admin/editCategory.jspx?id=${category.id}"><img alt="Edit this Category" title="Edit this Category" src="<@blog.basePath/>img/page_white_edit.png"/></a>&nbsp;<a href="editCategory.jspx?id=${category.id}&amp;save=remove" onclick="return confirmDel('${category.name}')"><img alt="Delete this Category" title="Delete this Category" src="<@blog.basePath/>img/delete.png"></a></td>
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
