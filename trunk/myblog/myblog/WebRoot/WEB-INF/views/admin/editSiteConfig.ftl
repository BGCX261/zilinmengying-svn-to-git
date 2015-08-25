<#import "/spring.ftl" as spring/>
<#import "inc/common.ftl" as blog/>
<#assign ti = springMacroRequestContext.getMessage("admin.title")>
<@blog.header ti,"SiteConfigMenu"/>
<body>
    <div id="page">
<@blog.sysHeader/>
        <div id="content" class="clearfix">
            <div id="main">
                <h1><@spring.message "menu.site.config"/></h1>
<@blog.message/>
<@blog.error/>
<form id="configForm" method="post" action="editSiteConfig.jspx">
<fieldset>
<ul>
    <li class="buttonBar right">
        <input type="submit" class="button" name="save" value="<@spring.message "button.save"/>"/>
    </li>
    <li>
        <label for="siteName" class="desc"><@spring.message "config.siteName"/> <span class="req">*</span></label>
        <input id="siteName" name="siteName" class="text large" type="text" value="${siteConfig.siteName}"/>
    </li>
    <li>
        <label for="siteURL" class="desc"><@spring.message "config.url"/> <span class="req">*</span></label>
        <input id="siteURL" name="siteURL" class="text large" type="text" value="${siteConfig.siteURL!}"/>
    </li>
    <li>
        <label for="siteSubName" class="desc"><@spring.message "config.siteSubName"/> <span class="req">*</span></label>
        <input id="siteSubName" name="siteSubName" class="text large" type="text" value="${siteConfig.siteSubName}"/>
    </li>
    <li>
        <label for="limitLength" class="desc"><@spring.message "config.limitLength"/> <span class="req">*</span></label>
        <input id="limitLength" name="limitLength" class="text large" type="text" value="${siteConfig.limitLength!}"/>
    </li>
	<li>
        <label for="theme" class="desc"><@spring.message "config.theme"/> <span class="req">*</span></label>
        <select name="theme" id="theme">
        	<#list themes as theme>
        	<option value="${theme}"<#if siteConfig.theme??&&siteConfig.theme==theme> selected="selected"</#if>>${theme}</option>
        	</#list>
        </select>
    </li>
    <li>
        <label for="ICPNumber" class="desc"><@spring.message "config.ICPNumber"/></label>
        <input id="ICPNumber" name="ICPNumber" class="text large" type="text" value="${siteConfig.ICPNumber!}"/>
    </li>
    <li>
        <label for="analyticsCode" class="desc"><@spring.message "config.analyticsCode"/></label>
        <textarea id="analyticsCode" name="analyticsCode" rows="2" cols="55">${siteConfig.analyticsCode!}</textarea>
    </li>
    <li>
        <label for="linkUnitsAds" class="desc"><@spring.message "config.linkUnitsAds"/></label>
        <textarea id="linkUnitsAds" name="linkUnitsAds" rows="5" cols="55">${siteConfig.linkUnitsAds!}</textarea>
    </li>
    <li>
        <label for="imageAds" class="desc"><@spring.message "config.imageAds"/></label>
        <textarea id="imageAds" name="imageAds" rows="5" cols="55">${siteConfig.imageAds!}</textarea>
    </li>
    <li>
        <label for="videoAds" class="desc"><@spring.message "config.videoAds"/></label>
        <textarea id="videoAds" name="videoAds" rows="5" cols="55">${siteConfig.videoAds!}</textarea>
    </li>
    <li>
        <label for="textAds" class="desc"><@spring.message "config.textAds"/></label>
        <textarea id="textAds" name="textAds" rows="5" cols="55">${siteConfig.textAds!}</textarea>
    </li>
    <li>
        <label for="referralsAds" class="desc"><@spring.message "config.referralsAds"/></label>
        <textarea id="referralsAds" name="referralsAds" rows="5" cols="55">${siteConfig.referralsAds!}</textarea>
    </li>
    <li>
        <label for="adSenseForSearch" class="desc"><@spring.message "config.adSenseForSearch"/></label>
        <textarea id="adSenseForSearch" name="adSenseForSearch" rows="5" cols="55">${siteConfig.adSenseForSearch!}</textarea>
    </li>
    <li class="editor">
        <label for="siteSimpleAbout" class="desc"><@spring.message "config.siteSimpleAbout"/> <span class="req">*</span></label>
        <textarea id="siteSimpleAbout" name="siteSimpleAbout" rows="5" cols="55">${siteConfig.siteSimpleAbout?html}</textarea>
    </li>
    <li class="editor">
        <label for="siteAbout" class="desc"><@spring.message "config.siteAbout"/> <span class="req">*</span></label>
        <textarea name="siteAbout" id="siteAbout" rows="5" cols="55">${siteConfig.siteAbout?html}</textarea>
    </li>
    <li class="buttonBar bottom">
        <input type="submit" class="button" name="save" value="<@spring.message "button.save"/>"/>
    </li>
</ul>
<fieldset>
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
  var oFCKeditor = new FCKeditor('siteAbout');
  oFCKeditor.BasePath = "<@blog.basePath/>FCK/";
  oFCKeditor.Config["CustomConfigurationsPath"] = "<@blog.basePath/>FCK/adminconfig.js"  ;
  oFCKeditor.Height = 500;
  oFCKeditor.CheckBrowser = true ;
  oFCKeditor.ReplaceTextarea() ;
}
</script>
</html>
