/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		EditProfileController.java
 * Class:			EditProfileController
 * Date:			Dec 22, 2006 6:59:31 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Dec 22, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.domain.User;
import com.jdkcn.util.Constants;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Dec 22, 2006 6:59:31 PM
 * @version $Id EditProfileController.java$
 */
public class EditProfileController extends BaseController {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)) {
			return new ModelAndView(getIndexView());
		}
		String save = request.getParameter("save");
		User user = blogFacade.getUser(id);
		ModelAndView mv = new ModelAndView();
		if(StringUtils.isBlank(save)||!"save".equals(save)) {
			mv.addObject("user", user);
			mv.setViewName(getFormView());
			return mv;
		}else{
//			List<String> errors = new ArrayList<String>();
//			String username = request.getParameter("username");
			String newPassword = request.getParameter("newPassword");
			String nickname = request.getParameter("nickname");
//			String birthday = request.getParameter("birthday");
//			String gender = request.getParameter("gender");
			String site = request.getParameter("site");
			user.setNickname(nickname);
			user.setSite(site);
			if(StringUtils.isNotBlank(newPassword)) {
				user.setPassword(newPassword);
				saveMessage(request, getText("msg.edit.password.success"));
				blogFacade.saveOrUpdateUser(user);
			}else{
				blogFacade.updateUserWithoutEncryptPassword(user);
			}
			mv.addObject("id", id);
			saveMessage(request, getText("msg.edit.profile.success"));
			mv.addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
			mv.setViewName(getSuccessView());
			return mv;
		}
	}

}
