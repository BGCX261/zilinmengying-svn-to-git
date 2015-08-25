package com.jdkcn.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import com.jdkcn.domain.Entry;
import com.jdkcn.util.Constants;
import com.jdkcn.util.MyblogUtil;
import com.jdkcn.util.PaginationSupport;

public class UpdateController extends BaseController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//update the database.
		PaginationSupport<Entry> ps = blogFacade.getEntryPage(new Entry(), Constants.DEFAULT_PAGE_SIZE, 0, null, null);
		int totalPage = ps.getTotalPage();
		int count = 0;
		for (int i = 2; i <= totalPage; i++) {
			for (int j = 0; j < ps.getItems().size(); j++) {
				Entry entry = ps.getItems().get(j);
				if (StringUtils.isBlank(entry.getSummary())) {
					entry.setSummary(MyblogUtil.truncateNicely(MyblogUtil.removeHTML(entry.getContent()), blogFacade.getDatabaseSiteConfig().getLimitLength(), blogFacade.getDatabaseSiteConfig().getLimitLength()+20, "..."));
					blogFacade.saveOrUpdateEntry(entry);
					count++;
				}
			}
			ps = blogFacade.getEntryPage(new Entry(), Constants.DEFAULT_PAGE_SIZE, (i-1)*Constants.DEFAULT_PAGE_SIZE, null, null);
		}
		saveMessage(request, "update successful," + count +  " entries updated.");
		return new ModelAndView(getSuccessView()).addObject(Constants.DO_NOT_INTERCEPT, Boolean.TRUE);
	}

}
