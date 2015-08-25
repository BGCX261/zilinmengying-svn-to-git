<#import "/spring.ftl" as spring/>
<#import "inc/common.ftl" as blog/>
<#assign title = springMacroRequestContext.getMessage("admin.title")>
<@blog.header title,"EntryMenu"/>
<body>
    <div id="page">
<@blog.sysHeader/>
        <div id="content" class="clearfix">
            <div id="main">
                <h1><@spring.message "menu.edit.category"/></h1>
<@blog.message/>
<@blog.error/>
<form id="categoryForm" method="post" action="editCategory.jspx">
<fieldset>
<ul>
    <li>
    	<input type="hidden" name="save" value="save"/>
    	<input type="hidden" name="id" value="<#if category?exists>${category.id}</#if>" />
        <label for="name" class="desc"><@spring.message "category.name"/> <span class="req">*</span></label>
        <input id="name" name="name" class="text large" type="text" value="<#if name?exists>${name}<#elseif category?exists>${category.name}</#if>"/>
    </li>

    <li>
        <label for="description" class="desc"><@spring.message "category.desc"/></label>
        <textarea rows="4" cols="50" name="description"><#if description?exists>${description}<#elseif category?exists>${category.description?if_exists}</#if></textarea>
    </li>
    
    <li class="buttonBar bottom">
        <input type="submit" class="button" name="save" onclick="bCancel=false" value="<@spring.message "button.save"/>"/>
        <input type="button" class="button" name="cancel" onclick="bCancel=true" value="<@spring.message "button.cancel"/>"/>
    </li>
</ul>
</fieldset>
</form>
<script type="text/javascript">
<!--
-->
</script>
            </div>
		<@blog.menu "admin_content"/>
        </div>
        <@blog.footer/>
        </div>
    </div>
</body>
</html>
