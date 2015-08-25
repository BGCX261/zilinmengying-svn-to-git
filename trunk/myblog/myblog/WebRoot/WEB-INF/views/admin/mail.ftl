<#import "/spring.ftl" as spring/>
<#import "inc/common.ftl" as blog/>
<#assign title = springMacroRequestContext.getMessage("admin.title")>
<@blog.header title,"EntryMenu"/>
<body>
    <div id="page">
<@blog.sysHeader/>
        <div id="content" class="clearfix">
            <div id="main">
                <h1><@spring.message "menu.send.email"/></h1>
<@blog.message/>
<@blog.error/>
<form id="mailForm" method="post" action="mail.jspx">
<fieldset>
<ul>
    <li>
        <label for="from" class="desc"><@spring.message "mail.from"/> <span class="req">*</span></label>
        <input id="from" name="from" class="text large" type="text" value="${from?if_exists}"/>
    </li>

    <li>
        <label for="to" class="desc"><@spring.message "mail.to"/> <span class="req">*</span></label>
        <input id="to" name="to" class="text large" type="text" value="${to?if_exists}"/>
    </li>
    
    <li>
        <label for="subject" class="desc"><@spring.message "mail.subject"/> <span class="req">*</span></label>
        <input id="subject" name="subject" class="text large" type="text" value="${subject?if_exists}"/>
    </li>
    
    <li>
        <label for="content" class="desc"><@spring.message "mail.content"/> <span class="req">*</span></label>
        <textarea rows="4" cols="50" name="content">${content?if_exists}</textarea>
    </li>
    
    <li class="buttonBar bottom">
    	<input type="hidden" name="save" value="send"/>
        <input type="submit" class="button" name="save" value="<@spring.message "button.save"/>"/>
        <input type="button" class="button" name="cancel" value="<@spring.message "button.cancel"/>"/>
    </li>
</ul>
</fieldset>
</form>
<script type="text/javascript">
<!--
-->
</script>
            </div>
		<@blog.menu "admin_other"/>
        </div>
        <@blog.footer/>
        </div>
    </div>
</body>
</html>
