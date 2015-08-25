<#import "/spring.ftl" as spring/>
<#import "inc/common.ftl" as blog/>
<#assign title = springMacroRequestContext.getMessage("admin.title")>
<@blog.header title,"CommentMenu"/>
<body onload="useLoadingMessage();">
  <script type='text/javascript' src='<@blog.basePath/>dwr/interface/BlogFacade.js'></script>
  <script type='text/javascript' src='<@blog.basePath/>dwr/engine.js'></script>
    <div id="page">
<@blog.sysHeader/>
        <div id="content" class="clearfix">
            <div id="main">
                <h1><@spring.message "menu.comment.management"/></h1>
<@blog.message/>
<@blog.error/>
<div class="pages"><#if ps?exists&&ps.pages?exists&&(ps.pages?size>0)><p><span>Pages(${ps.totalPage}):</span><#if (ps.currentPage>1)><a href="listComment.jspx?p=${ps.currentPage-1}">&#171; previous</a></#if><#list ps.pages as p><#if p==ps.currentPage><span class="current">${p}</span><#elseif p==0><span>...</span><#else><a href="listComment.jspx?p=${p}">${p}</a></#if></#list><#if (ps.currentPage>0)&&(ps.currentPage<ps.totalPage)><a href="listComment.jspx?p=${ps.currentPage+1}">next &#187;</a></#if></p></#if></div>
<br/>
<div style="float: left;">
<#if ps?exists&&(ps.items?size>0)>
<form action="removeComment.jspx" method="post">
<input type="hidden" name="p" value="${ps.currentPage}"/>
<table cellpadding="0" class="table" cellspacing="0" id="comment">
<thead>
<tr><th class="sortable"><input type="checkbox" onclick="checkAll(this.checked);"/></th><th class="sortable"><@spring.message "label.comment.commenter" /></th><th class="sortable"><@spring.message "label.comment.content" /></th><th><@spring.message "label.comment.ip"/></th><th class="sortable"><@spring.message "label.comment.operation"/></th></tr>
</thead>
<tbody>
<#list ps.items as comment>
<tr class="<#if (comment_index%2==0)>odd<#else>even</#if>">
	<td><input name="ids" value="${comment.id}" type="checkbox"/></td>
	<td><#if comment.type?exists&&(comment.type=="trackback")><img alt="This comment is trackback ping" title="This comment is trackback ping" src="img/bell.png"/></#if>${comment.authorName}<#if comment.authorMail?exists>(${comment.authorMail})</#if></td>
	<td title="${comment.content?html}"><#if (comment.content?length>10)>${comment.content?substring(0,10)?html}<#else>${comment.content}</#if></td>
	<td>${comment.postIP}</td>
	<td>&nbsp;<a href="editComment.jspx?id=${comment.id}&amp;save=remove<#if p?exists>&amp;p=${p}</#if>" onclick="return confirmDel('${comment.authorName}\'s comment ')"><img alt="Delete this comment" title="Delete this comment" src="<@blog.basePath/>img/delete.png"/></a><#if comment.status?exists&&(comment.status=="wait_for_approve")>&nbsp;<img id="img_${comment.id}" alt="Approve this comment" title="Approve this comment" src="<@blog.basePath/>img/not_accept.png" style="cursor: pointer" onclick="approveComment('${comment.id}');"/><#else>&nbsp;<img id="img_${comment.id}" alt="Against this comment" title="Against this comment" src="<@blog.basePath/>img/accept.png" style="cursor: pointer;" onclick="againstComment('${comment.id}');"/></#if></td>
</tr>
</#list>
</tbody>
</table>
<input type="submit" class="button" name="remove" value="<@spring.message "button.delete"/>"/>
</form>
</#if>
</div>
            </div>
		<@blog.menu "admin_content"/>
        </div>
        <@blog.footer/>
        </div>
    </div>
<script type="text/javascript">
<!--
function checkAll(checked){
		var elements = document.getElementsByName("ids");
	  	for(var i=0; i<elements.length; i++){
	  		elements[i].checked = checked;
	  	}
}
-->
</script>
</body>
</html>
