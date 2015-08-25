<#import "/spring.ftl" as spring/>
<#import "inc/common.ftl" as blog/>
<#assign title = springMacroRequestContext.getMessage("admin.title")>
<@blog.header title,"EntryMenu"/>
<body>
    <div id="page">
<@blog.sysHeader/>
        <div id="content" class="clearfix">
            <div id="main">
                <h1><@spring.message "menu.add.link"/></h1>
<@blog.message/>
<@blog.error/>
<form id="linkForm" method="post" action="addLink.jspx">
<fieldset>
<ul>
    <li>
        <label for="name" class="desc"><@spring.message "link.name"/> <span class="req">*</span></label>
        <input id="name" name="name" class="text large" type="text" value="${name?if_exists}"/>
    </li>
	
	<li>
        <label for="url" class="desc"><@spring.message "link.url"/> <span class="req">*</span></label>
        <input id="url" name="url" class="text large" type="text" value="${url?if_exists}"/>
    </li>
	
	<li>
        <label for="imgUrl" class="desc"><@spring.message "link.imgurl"/></label>
        <input id="imgUrl" name="imgUrl" class="text large" type="text" value="${imgUrl?if_exists}"/>
    </li>
	
	<li>
        <label for="order" class="desc"><@spring.message "link.order"/></label>
        <input id="order" name="order" class="text large" type="text" value="<#if order??>${order}<#else>0</#if>"/>
    </li>
	
	<li>
        <label for="recommend" class="desc"><@spring.message "link.recommend"/></label>
        <input type="checkbox" name="recommend" id="recommend" value="true" <#if recommend?exists && recommend=='true'>checked="checked"</#if> />
    </li>
	
	
    <li>
        <label for="description" class="desc"><@spring.message "link.description"/></label>
        <textarea rows="4" cols="50" name="description">${description?if_exists}</textarea>
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
