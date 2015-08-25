/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		MailServiceImpl.java
 * Class:			MailServiceImpl
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
package com.jdkcn.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.jdkcn.domain.Mail;
import com.jdkcn.service.MailService;
import com.jdkcn.util.VectorQueue;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since 2006-9-21
 * @version $Id MailServiceImpl.java$
 */
public class MailServiceImpl implements MailService {


	private Log log = LogFactory.getLog(MailServiceImpl.class);
	
	private String host;
	
	private String username;
	
	private String password;
	
	private VectorQueue<Mail> queue = new VectorQueue<Mail>();
	
	private VectorQueue<Mail> failedQueue = new VectorQueue<Mail>();
	
	private String defaultFrom = "service@jdkcn.com";
	
	private String defaultPersonal = "jdkcn.com";
	
	public void setDefaultFrom(String defaultFrom) {
		this.defaultFrom = defaultFrom;
	}

	public void setDefaultPersonal(String defaultPersonal) {
		this.defaultPersonal = defaultPersonal;
	}
	
	public void setHost(String host) {
		this.host = host;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/* (non-Javadoc)
	 * @see com.jdkcn.service.MailService#getFailedMails()
	 */
	public List<Mail> getFailedMails() {
		List<Mail> mails = new ArrayList<Mail>();
		mails.addAll(failedQueue);
		return mails;
	}

	/* (non-Javadoc)
	 * @see com.jdkcn.service.MailService#getQueueSize()
	 */
	public int getQueueSize() {
		return queue.size();
	}

	/* (non-Javadoc)
	 * @see com.jdkcn.service.MailService#schedulerSend()
	 */
	public int schedulerSend() {
		int success = 0;
		for(int i=0,n=queue.size(); i<n; i++) {
			Mail mail = (Mail)queue.poll();
			if(mail==null)
				continue;
			JavaMailSenderImpl sender = new JavaMailSenderImpl();
			sender.setHost(host);
			if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
				sender.setUsername(username);
				sender.setPassword(password);
				Properties props = new Properties();
				props.setProperty("mail.smtp.auth","true");
				props.setProperty("mail.smtp.timeout","25000");
				sender.setJavaMailProperties(props);
			}
			sender.setDefaultEncoding("UTF-8");
			try{
				MimeMessage message = sender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
				helper.setValidateAddresses(true);
				helper.setTo(mail.getTo());
				if(mail.getFrom()==null || mail.getFrom().length==0) {
					if (StringUtils.isNotBlank(defaultPersonal)) {
						helper.setFrom(defaultFrom, defaultPersonal);
					} else {
						helper.setFrom(defaultFrom);
					}
				}else if(mail.getPersonal()!=null && mail.getPersonal().length>0) {
					helper.setFrom(mail.getFrom()[0], mail.getPersonal()[0]);
				}else{
					helper.setFrom(mail.getFrom()[0]);
				}
				helper.setSentDate(new Date());
				helper.setSubject(mail.getSubject());
				helper.setText(mail.getContent(), true);
				sender.send(message);
				success ++;
			}catch(Exception e){
				failedQueue.offer(mail);
				log.error("When sending Mail,Some Error:"+e);
			}
		}
		return success;
	}

	/* (non-Javadoc)
	 * @see com.jdkcn.service.MailService#send(com.jdkcn.mail.Mail)
	 */
	public void send(Mail mail) {
		queue.offer(mail);
	}

}
