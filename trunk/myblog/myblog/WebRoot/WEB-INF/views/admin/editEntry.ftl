<#import "/spring.ftl" as spring/>
<#import "inc/common.ftl" as blog/>
<#assign ti = springMacroRequestContext.getMessage("admin.title")>
<@blog.header ti,"EntryMenu"/>
<body>
    <div id="page">
<@blog.sysHeader/>
        <div id="content" class="clearfix">
            <div id="main">
                <h1><@spring.message "menu.edit.entry"/></h1>
<@blog.message/>
<@blog.error/>
<form id="entryForm" method="post" action="editEntry.jspx">
<ul>
    <li class="buttonBar right">
    	<input type="hidden" name="save" value="save"/>
    	<input type="hidden" name="id" value="<#if entry?exists>${entry.id}<#elseif id?exists>${id}</#if>"/>
        <input type="submit" class="button" name="save" value="<@spring.message "button.save"/>"/>
        <#if entry?exists&&entry.entryStatus?exists && entry.entryStatus=='draft'>
        <input type="submit" class="button" name="publish" value="<@spring.message "button.publish"/>"/>
        </#if>
    </li>
    <li>
        <label for="title" class="desc"><@spring.message "entry.title"/> <span class="req">*</span></label>
        <input id="title" name="title" class="text large" type="text" value="<#if title?exists>${title}<#elseif entry?exists>${entry.title}</#if>"/>
    </li>
    <li>
        <label for="tags" class="desc"><@spring.message "entry.tags"/></label>
        <input id="tags" name="tags" class="text large" type="text" value="<#if tags?exists>${tags}<#elseif entry?exists><#list entry.tags as tag><#if (tag_index>0)>,</#if>${tag.name}</#list></#if>"/>
    </li>
    <li>
        <label for="name" class="desc"><@spring.message "entry.name"/></label>
        <input id="name" name="name" class="text large" type="text" value="<#if name?exists>${name}<#elseif entry?exists>${entry.name?if_exists}</#if>"/>
    </li>
    <li>
        <label for="category" class="desc"><@spring.message "entry.category"/> <span class="req">*</span></label>
        <#list categories as category>
        <input type="checkbox" name="cates"<#if cates?exists&&(cates?seq_contains(category.id))> checked="checked"<#elseif entry?exists&&(entry.categories?seq_contains(category))> checked="checked"</#if> value="${category.id}"/>${category.name}&nbsp;<#if ((category_index+1)%6==0)><br/></#if>
        </#list>
    </li>
    <div id="xToolbar"></div>
    <li class="editor">
        <label for="content" class="desc"><@spring.message "entry.content"/> <span class="req">*</span></label>
        <textarea id="content" name="content" rows="20" cols="70"><#if content??>${content?html}<#elseif entry??>${entry.content?html}</#if></textarea>
    </li>
    <li class="editor">
        <label for="summary" class="desc"><@spring.message "entry.summary"/></label>
        <textarea id="summary" name="summary" rows="8" cols="70"><#if summary??>${summary?html}<#elseif entry??&&entry.summary??>${entry.summary?html}</#if></textarea>
    </li>
    <li class="buttonBar bottom">
        <input type="submit" class="button" name="save" value="<@spring.message "button.save"/>"/>
        <#if entry??&&entry.entryStatus?? && entry.entryStatus=='draft'>
        <input type="submit" class="button" name="publish" value="<@spring.message "button.publish"/>"/>
        </#if>
    </li>
</ul>
</form>
            </div>
		<@blog.menu "admin_content"/>
        </div>
        <@blog.footer/>
        </div>
    </div>
</body>
<script type="text/javascript" src="<@blog.basePath/>FCK/fckeditor.js"></script>
<script type="text/javascript">
window.onload = function (){
  var oFCKeditor = new FCKeditor('content');
  oFCKeditor.BasePath = "<@blog.basePath/>FCK/";
  oFCKeditor.Config["CustomConfigurationsPath"] = "<@blog.basePath/>FCK/adminconfig.js"  ;
  oFCKeditor.Config[ 'ToolbarLocation' ] = 'Out:xToolbar' ;
  oFCKeditor.Height = 500;
  oFCKeditor.CheckBrowser = true ;
  oFCKeditor.ReplaceTextarea() ;
  
  oFCKeditor = new FCKeditor('summary');
  oFCKeditor.BasePath = "<@blog.basePath/>FCK/";
  oFCKeditor.Config["CustomConfigurationsPath"] = "<@blog.basePath/>FCK/adminconfig.js"  ;
  oFCKeditor.Config[ 'ToolbarLocation' ] = 'Out:xToolbar' ;
  oFCKeditor.Height = 100 ;
  oFCKeditor.CheckBrowser = true ;
  oFCKeditor.ReplaceTextarea() ;
}
</script>
</html>
