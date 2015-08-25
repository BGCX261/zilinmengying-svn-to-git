<#import "/spring.ftl" as spring/>
<#import "inc/common.ftl" as blog/>
<#assign ti = springMacroRequestContext.getMessage("admin.title")>
<@blog.header ti,"EntryMenu"/>
<body>
    <div id="page">
<@blog.sysHeader/>
        <div id="content" class="clearfix">
            <div id="main">
                <h1><@spring.message "menu.add.page"/></h1>
<@blog.message/>
<@blog.error/>
<form id="entryForm" method="post" action="addPage.jspx">
<fieldset>
<ul>
    <li class="buttonBar right">
    	<input type="hidden" name="save" value="save"/>
        <input type="submit" class="button" name="save" value="<@spring.message "button.save"/>"/>
        <input type="submit" class="button" name="publish" value="<@spring.message "button.publish"/>"/>
    </li>
    <li>
        <label for="title" class="desc"><@spring.message "page.title"/> <span class="req">*</span></label>
        <input id="title" name="title" class="text large" type="text" value="${title!}"/>
    </li>
    <li>
        <label for="name" class="desc"><@spring.message "page.name"/> <span class="req">*</span></label>
        <input id="name" name="name" class="text large" type="text" value="${name!}"/>
    </li>
    <li>
        <label for="tags" class="desc"><@spring.message "page.tags"/></label>
        <input id="tags" name="tags" class="text large" type="text" value="${tags!}"/>
    </li>
    <li>
        <label for="commentStatus" class="desc"><@spring.message "page.allow.comments"/></label>
        <input type="checkbox" name="commentStatus" value="open"<#if commentStatus?exists&&("close"==commentStatus)><#else> checked="checked"</#if>/>
    </li>
    <li class="editor">
        <label for="content" class="desc"><@spring.message "page.content"/> <span class="req">*</span></label>
        <textarea name="content" rows="" cols=""><#if content??>${content?html}</#if></textarea>
    </li>
    <li class="buttonBar bottom">
        <input type="submit" class="button" name="save" value="<@spring.message "button.save"/>"/>
        <input type="submit" class="button" name="publish" value="<@spring.message "button.publish"/>"/>
    </li>
</ul>
</fieldset>
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
  oFCKeditor.Width = 700;
  oFCKeditor.CheckBrowser = true ;
  oFCKeditor.ReplaceTextarea() ;
}
</script>
</html>
