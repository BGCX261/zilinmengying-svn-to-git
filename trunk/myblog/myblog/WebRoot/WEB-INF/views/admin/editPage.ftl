<#import "/spring.ftl" as spring/>
<#import "inc/common.ftl" as blog/>
<#assign ti = springMacroRequestContext.getMessage("admin.title")>
<@blog.header ti,"EntryMenu"/>
<body>
    <div id="page">
<@blog.sysHeader/>
        <div id="content" class="clearfix">
            <div id="main">
                <h1><@spring.message "menu.edit.page"/></h1>
<@blog.message/>
<@blog.error/>
<form id="entryForm" method="post" action="editPage.jspx">
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
        <label for="title" class="desc"><@spring.message "page.title"/> <span class="req">*</span></label>
        <input id="title" name="title" class="text large" type="text" value="<#if title?exists>${title}<#elseif entry?exists>${entry.title}</#if>"/>
    </li>
    <li>
        <label for="name" class="desc"><@spring.message "page.name"/> <span class="req">*</span></label>
        <input id="name" name="name" class="text large" type="text" value="<#if name?exists>${name}<#elseif entry?exists>${entry.name?if_exists}</#if>"/>
    </li>
    <li>
        <label for="tags" class="desc"><@spring.message "page.tags"/></label>
        <input id="tags" name="tags" class="text large" type="text" value="<#if tags?exists>${tags}<#elseif entry?exists><#list entry.tags as tag><#if (tag_index>0)>,</#if>${tag.name}</#list></#if>"/>
    </li>
    <li>
        <label for="commentStatus" class="desc"><@spring.message "page.allow.comments"/></label>
        <#assign chk=false/>
        <#if commentStatus?exists&&("open"==commentStatus)>
        <#assign chk=true/>
        <#elseif entry?exists&&entry.commentStatus=="open">
        <#assign chk=true/>
        </#if>
        <input type="checkbox" name="commentStatus" value="open"<#if chk> checked="checked"</#if>/>
    </li>
    <li class="editor">
        <label for="content" class="desc"><@spring.message "page.content"/> <span class="req">*</span></label>
        <textarea name="content" rows="" cols=""><#if content?exists>${content}<#elseif entry?exists>${entry.content}</#if></textarea>
    </li>
    <li class="buttonBar bottom">
        <input type="submit" class="button" name="save" value="<@spring.message "button.save"/>"/>
        <#if entry?exists&&entry.entryStatus?exists && entry.entryStatus=='draft'>
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
  oFCKeditor.Height = 500;
  oFCKeditor.CheckBrowser = true ;
  oFCKeditor.ReplaceTextarea() ;
}
</script>
</html>
