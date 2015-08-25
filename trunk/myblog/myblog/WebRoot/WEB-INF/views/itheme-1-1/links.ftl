<#import "/spring.ftl" as spring/>
<#import "inc/myblog.ftl" as blog/>
<@blog.blogHeader siteConfig.siteName,"Blog Links","Blog Links " +siteConfig.siteName,""/>
    <div id="left-col">
	 <@blog.blogNav "links"/>
      <div id="content">
		<p>
		${siteConfig.linkUnitsAds?if_exists}
		<ul id="allLinks">
			<li id="linkcat"><h2><@spring.message "label.links"/></h2></li>
			<#if links?exists && (links?size>0)>
		  	<ul>
		  	<#list links as link>
		  		<li><a href="${link.URL}" title="${link.description!}"><#if link.imgURL?exists><img src="${link.imgURL}" alt="${link.name}"/><#else>${link.name}</#if></a>${link.description!}</li>
		  	</#list>
		  	</ul>
		  	<#else>
		  	<li id="linkcat"><h2>No links found!</h2></li>
		  	</#if>
		</ul>
		</p>
  </div><!--/content -->
  <@blog.blogFooter/>
</div><!--/left-col -->
    <@blog.blogSidebar/>
<@blog.blogBottom/>