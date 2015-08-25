<#import "/spring.ftl" as spring/>

<#--
 *
 * The contextPath
 -->
<#macro basePath><#if springMacroRequestContext.getContextPath()=="/">/<#else>${springMacroRequestContext.getContextPath()}/</#if></#macro>


<#macro sysHeader>
<#assign contextPath ="">
<#if springMacroRequestContext.getContextPath()=="/">
	<#assign contextPath="/">
<#else>
	<#assign contextPath=springMacroRequestContext.getContextPath()+"/">
</#if>
        <div id="header" class="clearfix">
			<div id="branding">
	    		<h1><a href="${contextPath}"><@spring.message "app.name"/></a></h1>
	    		<p>Powerful system</p>
			</div>
			<hr />
        </div>
</#macro>

<#--
 * head
 *
 * Macro to render the html's header.
 -->
<#macro header title,menuMeta>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="menu" content="${menuMeta}"/>
    <link rel="stylesheet" type="text/css" media="all" href="<@basePath/>styles/simplicity/theme.css" />
    <link rel="stylesheet" type="text/css" media="print" href="<@basePath/>styles/simplicity/print.css" />
	<script type="text/javascript" src="<@basePath/>js/prototype.js"></script>
	<script type="text/javascript" src="<@basePath/>js/admin.js"></script>
    <title>${title}</title>
</head>
</#macro>

<#--
 * head for login
 *
 * Macro to render the html's header.
 -->
<#macro loginHeader title,menuMeta>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="menu" content="${menuMeta}"/>
    <link rel="stylesheet" type="text/css" media="all" href="<@basePath/>styles/simplicity/theme.css" />
    <link rel="stylesheet" type="text/css" media="print" href="<@basePath/>styles/simplicity/print.css" />
	<link rel="stylesheet" type="text/css" media="all" href="<@basePath/>styles/simplicity/layout-1col.css" />
	<script type="text/javascript" src="<@basePath/>js/prototype.js"></script>
    <title>${title}</title>
</head>
</#macro>

<#--
 * footer
 *
 * Macro to render the html's footer.
 -->
<#macro footer>
        <div id="footer" class="clearfix">
    		<div id="divider"><div>
    	</div>
    </div>
	<span class="left">欢迎光临</span>
</#macro>


<#assign xhtmlCompliant = true in spring>

<#macro menu current>
            <div id="nav">
                <div class="wrapper">
                    <h2 class="accessibility">Navigation</h2>
					<ul id="primary-nav" class="menuList">
    					<li class="pad">&nbsp;</li>
                        <li><a href="<@basePath/>admin/index.jspx" <#if current=='admin_index'>class="current"</#if> title="<@spring.message "menu.main"/>"><@spring.message "menu.main"/></a></li>
                        <li class="menubar">
                        	<a href="<@basePath/>admin/editProfile.jspx<#if _myblog_authuser?exists>?id=${_myblog_authuser.id}</#if>" <#if current=='admin_user'>class="current"</#if> title="<@spring.message "menu.user.management"/>" style="width: 120px"><@spring.message "menu.user.management"/></a>
                        	<ul>
                        		<li class="last"><a href="<@basePath/>admin/editProfile.jspx<#if _myblog_authuser?exists>?id=${_myblog_authuser.id}</#if>" title="<@spring.message "menu.edit.profile"/>" ><@spring.message "menu.edit.profile"/></a></li>
                        	</ul>
                        </li>
						<li class="menubar">
                        	<a href="<@basePath/>admin/addEntry.jspx" <#if current=='admin_content'>class="current"</#if> title="<@spring.message "menu.content.management"/>" style="width: 120px"><@spring.message "menu.content.management"/></a>
             				<ul>
             					<li><a href="<@basePath/>admin/editSiteConfig.jspx?save=edit" title="<@spring.message "menu.site.config"/>"><@spring.message "menu.site.config"/></a></li>
             					<li><a href="<@basePath/>admin/addEntry.jspx" title="<@spring.message "menu.add.entry"/>" ><@spring.message "menu.add.entry"/></a></li>
                                <li><a href="<@basePath/>admin/listEntry.jspx" title="<@spring.message "menu.entry.management"/>" ><@spring.message "menu.entry.management"/></a></li>
                                <li><a href="<@basePath/>admin/addCategory.jspx" title="<@spring.message "menu.add.category"/>" ><@spring.message "menu.add.category"/></a></li>
                                <li><a href="<@basePath/>admin/listCategory.jspx" title="<@spring.message "menu.category.management"/>" ><@spring.message "menu.category.management"/></a></li>
                                <li><a href="<@basePath/>admin/addLink.jspx" title="<@spring.message "menu.add.link"/>" ><@spring.message "menu.add.link"/></a></li>
                                <li><a href="<@basePath/>admin/listLink.jspx" title="<@spring.message "menu.link.management"/>" ><@spring.message "menu.link.management"/></a></li>
                                <li><a href="<@basePath/>admin/addPage.jspx" title="<@spring.message "menu.add.page"/>" ><@spring.message "menu.add.page"/></a></li>
                                <li><a href="<@basePath/>admin/listPage.jspx" title="<@spring.message "menu.page.management"/>" ><@spring.message "menu.page.management"/></a></li>
                                <li class="last"><a href="<@basePath/>admin/listComment.jspx" title="<@spring.message "menu.comment.management"/>" ><@spring.message "menu.comment.management"/></a></li>
            				</ul>
                        </li>
                        <li class="menubar">
                        	<a href="<@basePath/>admin/mail.jspx" <#if current='admin_other'>class="current"</#if> title="<@spring.message "menu.other.management"/>" style="width: 120px"><@spring.message "menu.other.management"/></a>
                        	<ul>
                        		<li><a href="<@basePath/>admin/mail.jspx" title="<@spring.message "menu.send.email"/>"><@spring.message "menu.send.email"/></a></li>
                        		<li><a href="<@basePath/>admin/mail.jspx?save=schedulerSend" title="<@spring.message "menu.send.email.queue"/>"><@spring.message "menu.send.email.queue"/></a></li>
                        		<li class="last"><a href="<@basePath/>admin/indexView.jspx" title="<@spring.message "menu.rebuild.index"/>"><@spring.message "menu.rebuild.index"/></a></li>
                        	</ul>
                        </li>
                        <li><a href="<@basePath/>admin/logout.jspx" title="<@spring.message "menu.logout"/>" ><@spring.message "menu.logout"/></a></li>
                     </ul>
                </div>
                <hr />
            </div><!-- end nav -->
</#macro>
 
<#--
 *
 * show the messages if exists.
 -->
<#macro message>
<#if messages?exists>
<div class="message">
	<#list messages as msg>
		${msg}<br />
	</#list>
</div>
</#if>
</#macro>

<#--
 *
 * show the errors if exists.
 -->
<#macro error>
<#if errors??>
<div class="error">
<#list errors as error>${error}<br /></#list>
</div>
</#if>
</#macro>