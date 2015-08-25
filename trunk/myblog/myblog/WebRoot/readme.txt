myblog 

the newest version is 1.8 National-Day-Final edition.

using: spring, hibernate,dwr,freemarker,compass,etc..

Introduction
============
It's a powerful web 2.0 blog.Use many ajax and more user experience

Getting Started
===============
1.首先下载程序包 eg:myblog-1.8.war,下载后放到tomcat,resin的webapps目录下面启动tomcat,resin之后就可以体验myblog的功能了。
默认的用户名是admin,密码也是admin.注意这个时候您做的所有操作都是没有保存到数据的库的,是保存在内在中的.

2.接下来你应该配置myblog连接您的数据库。打开myblog的WEB-INF/classes/config.properties文件.找到您的数据库的相应配置.
比如如果您使用mysql5.x的数据库您的数据库配置应该看起来是这个样子的

hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
datasource.driverClassName=com.mysql.jdbc.Driver
datasource.url=jdbc:mysql://127.0.0.1:3306/myblog?useUnicode=true&characterEncoding=utf8
datasource.username=root
datasource.password=root

请注意修改成您的url,username和password.
如果您使用其它数据库请参考config.properties里面注释了的一些配置做相应修改即可.

3.关于后台登录帐号的配置,您需要修改
system.username=admin
system.password=admin
system.email=example@example.com
这三个配置。这里你可以修改后台登录用户的用户名,密码以及email地址。密码也可以安装后在后台管理里面修改.

4.关于javamail的配置,您需要修改
mail.host=localhost
mail.username=
mail.password=
mail.from=example@example.com
mail.personal=Example.com
这里是为评论邮件通告设置的。如果设置不正常,将不会出现评论的邮件通告.

5.启动tomcat,resin安装myblog.设置好上面的这些之后您可以再次启动tomcat,resin了。启动之后myblog就安装成功了。ok all done..

6.注意的问题
 (I)数据库编码的问题。比如采用mysql,应该使用utf-8编码.可能您安装mysql的时候默认的编码不是使用的utf-8编码,您建立好数据库之后请执行下面语句
 alter database myblog DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci; 
 (II)url编码的问题。您会发现安装好之后中文的分类,和中文的标签都不能正常的取到日志.这是因为url的编码问题。如果您使用的是tomcat您应该修改处理请求的那个connector
 添加两个属性 URIEncoding="UTF-8" UseBodyEncodingForURI="true"
 <Connector port="8080" maxHttpHeaderSize="8192"
               maxThreads="150" minSpareThreads="25" maxSpareThreads="75"
               enableLookups="false" redirectPort="8443" acceptCount="100"
               connectionTimeout="20000" disableUploadTimeout="true" URIEncoding="UTF-8" UseBodyEncodingForURI="true" />
  如果您使用的是apache和tomcat集成的方式,请不要忘了要在ajp的connector加上上面的两个属性.
  <!-- Define an AJP 1.3 Connector -->
    <Connector port="8009" 
               enableLookups="false" redirectPort="8443" protocol="AJP/1.3" URIEncoding="UTF-8" UseBodyEncodingForURI="true" />
 
 (III)关于initialization参数,initialization参数是初始化的开关,第一次安装的时候是必须为true的.
  初始化之后这个参数也可以为true.Myblog会检查是否已经初始化安装过了.如果安装过了就不需要安装了.
  不过这个判断是在您每次重启Myblog的时候都会进行的。所以建议您安装成功之后将这个参数设置成false.

Upgrade from old versions
=========================
从1.5,1.6升级到1.8您需要做一下几个步骤.
1.首先请备份旧版本的myblog,以及您的数据库.停止您的Myblog
2.更新数据库结构,请执行一下数据库脚本
 update `MYBLOG_TAG` tag set `count` = (select count(1) from MYBLOG_ENTRY_TAGS where tagId=tag.id);
 update `MYBLOG_CATEGORY` category set `count` = (select count(1) from MYBLOG_ENTRY_CATEGORY where categoryId=category.id);
3.修改config.properties里面的initialization参数为true.
4.启动服务后,登录后台执行一下update. http://localhost:8080/myblog/admin/update.jspx,请替换成您的确切链接.
   比如jdkcn.com的update链接应该是这样的http://jdkcn.com/admin/update.jspx
 OK. all done.您可以将initialization参数修改回false了.
5.注意,升级的时候最好先删除原来的文件,保留WEB-INF/classes/config.properties和WEB-INF/classes/siteConfig.xml即可
 Good, luck. :) 2007-10-30 23:50

others
=========================
<object type="application/x-shockwave-flash" data="http://jdkcn.com/img/player.swf" width="290" height="24" id="audioplayer1"><param name="movie" value="http://jdkcn.com/img/player.swf" /><param name="FlashVars" value="playerID=1&amp;bg=0x111111&amp;leftbg=0x222222&amp;lefticon=0x999999&amp;rightbg=0x222222&amp;rightbghover=0x444444&amp;righticon=0x999999&amp;righticonhover=0xcccccc&amp;text=0x999999&amp;slider=0x999999&amp;track=0x111111&amp;border=0x333333&amp;loader=0x222222&amp;soundFile=http%3A%2F%2Fdemo.utombox.com%2Fmusic%2FKatharine_McPhee_-_Somewhere_Over_The_Rainbow%28single%29.mp3" /><param name="quality" value="high" /><param name="menu" value="false" /><param name="bgcolor" value="#000000" /></object>

有任何问题请到http://groups.google.com/group/myblogdev