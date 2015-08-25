<#import "/spring.ftl" as spring/>
<#import "inc/common.ftl" as blog/>
<#assign ti = springMacroRequestContext.getMessage("admin.title")>
<@blog.header ti,"Edit User Profile"/>
<body>
    <div id="page">
<@blog.sysHeader/>
        <div id="content" class="clearfix">
            <div id="main">
                <h1><@spring.message "menu.edit.profile"/></h1>
<@blog.message/>
<@blog.error/>
<form id="userForm" method="post" action="editProfile.jspx">
<fieldset>
<ul>
    <li class="buttonBar right">
    	<input type="hidden" name="save" value="save"/>
    	<input type="hidden" name="id" value="<#if user?exists>${user.id}</#if>"/>
        <input type="submit" class="button" name="save" value="<@spring.message "button.save"/>"/>
        <input type="button" class="button" name="cancel" value="<@spring.message "button.cancel"/>"/>
    </li>
    <li>
        <label for="username" class="desc"><@spring.message "user.username"/></label>
        <input id="username" name="username" class="text large" type="text" value="<#if username?exists>${username}<#elseif user?exists>${user.username?if_exists}</#if>" readonly="readonly"/>
    </li>
    <li>
        <label for="newPassword" class="desc"><@spring.message "user.newPassword"/></label>
        <input id="newPassword" name="newPassword" class="text large" type="password" value="<#if newPassword?exists>${newPassword}</#if>"/>
    </li>
    <li>
        <label for="nickname" class="desc"><@spring.message "user.nickname"/></label>
        <input id="nickname" name="nickname" class="text large" type="text" value="<#if nickname?exists>${nickname}<#elseif user?exists>${user.nickname?if_exists}</#if>"/>
    </li>
    <li>
        <label for="site" class="desc"><@spring.message "user.site"/></label>
        <input id="site" name="site" class="text large" type="text" value="<#if site?exists>${site}<#elseif user?exists>${user.site?if_exists}</#if>"/>
    </li>
    <li>
        <label for="mail" class="desc"><@spring.message "user.mail"/></label>
        <input id="mail" name="mail" class="text large" type="text" value="${user.mail?if_exists}" readonly="readonly"/>
    </li>
    <li class="buttonBar bottom">
        <input type="submit" class="button" name="save" value="<@spring.message "button.save"/>"/>
        <input type="button" class="button" name="cancel" value="<@spring.message "button.cancel"/>"/>
    </li>
</ul>
</fieldset>
</form>
            </div>
		<@blog.menu "admin_user"/>
        </div>
        <@blog.footer/>
        </div>
    </div>
</body>
</html>
