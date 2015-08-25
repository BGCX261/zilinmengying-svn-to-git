/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		MailService.java
 * Class:			MailService
 * Date:			2006-9-21
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| 2006-9-21   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.service;

import java.util.List;

import com.jdkcn.domain.Mail;



/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since 2006-9-21
 * @version $Id MailService.java$
 */
public interface MailService {
	/**
	 * 发送邮件
	 * @param mail 邮件
	 */
	public void send(Mail mail);
	
	/**
	 * 活动失败队列里的信件
	 * @return Mail的List
	 */
	public List<Mail> getFailedMails();
	
	/**
	 * 察看队列中邮件的数目
	 * @return 邮件数目
	 */
	public int getQueueSize();
	
	/**
	 * 定时从队列中取邮件发送
	 * @return 发送条数
	 */
	public int schedulerSend();

}
