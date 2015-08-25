<#import "/spring.ftl" as spring/>
<#import "inc/common.ftl" as blog/>
<#assign title = springMacroRequestContext.getMessage("admin.title")>
<@blog.header title,"EntryMenu"/>
<body>
    <div id="page">
<@blog.sysHeader/>
        <div id="content" class="clearfix">
            <div id="main">
                <h1><@spring.message "menu.entry.management"/></h1>
<@blog.message/>
<@blog.error/>
<div class="pages"><#if page?exists&&page.pages?exists&&(page.pages?size>0)><p><span>Pages(${page.totalPage}):</span><#if (page.currentPage>1)><a href="listEntry.jspx?p=${page.currentPage-1}">&#171; previous</a></#if><#list page.pages as p><#if p==page.currentPage><span class="current">${p}</span><#elseif p==0><span>...</span><#else><a href="listEntry.jspx?p=${p}">${p}</a></#if></#list><#if (page.currentPage>0)&&(page.currentPage<page.totalPage)><a href="listEntry.jspx?p=${page.currentPage+1}">next &#187;</a></#if></p></#if></div>
<div style="float: left;">
<#if page?exists&&(page.items?size>0)>
<table cellpadding="0" cellspacing="0" class="table" id="entry">
<thead>
	<th class="sortable"><@spring.message "label.entry.title"/></th><th class="sortable"><@spring.message "label.entry.posttime"/></th><th class="sortable"><@spring.message "label.entry.operation"/></th>
</thead>
<tbody>
<#list page.items as entry>
<tr class="<#if (entry_index%2==0)>odd<#else>even</#if>">
	<td><#if entry.entryStatus=='draft'><img src="<@blog.basePath/>img/stop.png" alt="draft entry" title="This entry is draft"/></#if>${entry.title}</td>
	<td>${entry.postTime?datetime}</td>
	<td><a href="<@blog.basePath/>admin/editEntry.jspx?id=${entry.id}"><img alt="Edit this entry" title="Edit this entry" src="<@blog.basePath/>img/page_white_edit.png"/></a>&nbsp;<a href="editEntry.jspx?id=${entry.id}&amp;save=remove<#if p?exists>&amp;p=${p}</#if>" onclick="return confirmDel('${entry.title}')"><img alt="Delete this entry" title="Delete this entry" src="<@blog.basePath/>img/delete.png"></a></td>
</tr>
</#list>
</tbody>
</table>
</#if>
</div>
            </div>
		<@blog.menu "admin_content"/>
        </div>
        <@blog.footer/>
        </div>
    </div>
</body>
</html>
