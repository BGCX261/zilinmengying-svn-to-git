<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<@blog.header "404 Not Found","404","",""/>
<div id="page">
<div id="content">
<div class="content">
<p>
<h3>404,Not found this page.</h3>
<br />
</p>
</div>
</div><!-- content --> 
<@blog.sidebar false/>
</div><!-- page -->
<@blog.footer/>
</body>
</html>