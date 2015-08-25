<#import "/spring.ftl" as spring/>
<#import "inc/common.ftl" as blog/>
<#assign xhtmlCompliant = true in spring>
<#assign title = springMacroRequestContext.getMessage("form.login")>
<@blog.loginHeader title,"Login"/>
<body id="login">
    <div id="page">
<@blog.sysHeader/>
    <div id="content" class="clearfix">
    	<div id="main">
<@blog.message/>
<@blog.error/>
             <h1><@spring.message "menu.login"/></h1>
		<form method="post" id="loginForm" action="<@blog.basePath/>admin/login.jspx">
			<fieldset>
			<ul>
			    <li>
			       <label for="username" class="desc">
			            <@spring.message "login.username"/> <span class="req">*</span>
			        </label>
			        <input type="text" class="text medium" name="username" id="username" tabindex="1" value="${username?if_exists}"/>
			    </li>
			
			    <li>
			        <label for="password" class="desc">
			            <@spring.message "login.password"/> <span class="req">*</span>
			        </label>
			        <input type="password" class="text medium" name="password" id="password" tabindex="2" value=""/>
			    </li>
			    
			    <li>
			        <label for="code" class="desc">
			            <@spring.message "login.code"/> <span class="req">*</span>
						<img src="<@blog.basePath/>identifyingCode" alt="Identifying Code" onclick="this.src='<@blog.basePath/>identifyingCode?now=' + new Date().getTime()" />
			        </label>
			        <input type="text" class="text medium" name="code" id="code" tabindex="3" />
			    </li>
			    
			    <li>
			        <input type="checkbox" class="checkbox" name="rememberme" id="rememberme" value="forever" tabindex="4"/>
			        <label for="rememberMe" class="choice"><@spring.message "login.remember"/></label>
			    </li>
			
			    <li>
			        <input type="submit" class="button" name="login" value="<@spring.message "login.submit"/>" tabindex="5" />
			    </li>
			</ul>
			</fieldset>
		</form>
        </div>
        <div id="nav">
             <div class="wrapper">
                <h2 class="accessibility">Navigation</h2>
				<ul id="primary-nav" class="menuList">
  					<li class="pad">&nbsp;</li>
  					<li><a href="login.jspx" class="current" title="<@spring.message "menu.login"/>"><@spring.message "menu.login"/></a></li>
				</ul>
             </div>
             <hr />
         </div>
        <!-- end nav -->
        </div>
<@blog.footer/>
   </div>
</div>
</body>
</html>