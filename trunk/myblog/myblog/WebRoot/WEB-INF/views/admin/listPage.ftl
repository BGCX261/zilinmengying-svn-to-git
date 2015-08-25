<#import "/spring.ftl" as spring/>
<#import "inc/common.ftl" as blog/>
<#assign title = springMacroRequestContext.getMessage("admin.title")>
<@blog.header title,"EntryMenu"/>
<body>
    <div id="page">
<@blog.sysHeader/>
        <div id="content" class="clearfix">
            <div id="main">
                <h1><@spring.message "menu.page.management"/></h1>
<@blog.message/>
<@blog.error/>
<div class="pages"><#if ps?exists&&ps.pages?exists&&(ps.pages?size>0)><p><span>Pages(${ps.totalPage}):</span><#if (ps.currentPage>1)><a href="admin_listEntry.jhtml?p=${page.currentPage-1}">&#171; previous</a></#if><#list ps.pages as p><#if p==ps.currentPage><span class="current">${p}</span><#elseif p==0><span>...</span><#else><a href="admin_listEntry.jhtml?p=${p}">${p}</a></#if></#list><#if (ps.currentPage>0)&&(ps.currentPage<ps.totalPage)><a href="admin_listPage.jhtml?p=${ps.currentPage+1}">next &#187;</a></#if></p></#if></div>
<div style="float: left;">
<#if ps?exists&&(ps.items?size>0)>
<table cellpadding="0" cellspacing="0" class="table" id="entry">
<thead>
	<th class="sortable"><@spring.message "label.entry.title"/></th><th class="sortable"><@spring.message "label.entry.name"/></th><th class="sortable"><@spring.message "label.entry.posttime"/></th><th class="sortable"><@spring.message "label.entry.operation"/></th>
</thead>
<tbody>
<#list ps.items as entry>
<tr class="<#if (entry_index%2==0)>odd<#else>even</#if>">
	<td><#if entry.entryStatus=='draft'><img src="img/stop.png" alt="draft page" title="This page is draft"/></#if>${entry.title}</td>
	<td>${entry.name}</td>
	<td>${entry.postTime?datetime}</td>
	<td><a href="<@blog.basePath/>admin/editPage.jspx?id=${entry.id}"><img alt="Edit this page" title="Edit this page" src="<@blog.basePath/>img/page_white_edit.png"/></a>&nbsp;<a href="removePage.jspx?ids=${entry.id}<#if p?exists>&amp;p=${p}</#if>" onclick="return confirmDel('${entry.title}')"><img alt="Delete this page" title="Delete this page" src="<@blog.basePath/>img/delete.png"></a></td>
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
