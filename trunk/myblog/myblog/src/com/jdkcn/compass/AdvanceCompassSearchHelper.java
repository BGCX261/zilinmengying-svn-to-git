/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		AdvanceCompassSearchHelper.java
 * Class:			AdvanceCompassSearchHelper
 * Date:			Aug 23, 2007 2:04:19 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Aug 23, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.compass;

import org.compass.core.Compass;
import org.compass.core.CompassHits;
import org.compass.core.CompassSession;
import org.compass.core.support.search.CompassSearchCommand;
import org.compass.core.support.search.CompassSearchHelper;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Aug 23, 2007 2:04:19 PM
 * @version $Id AdvanceCompassSearchHelper.java$
 */
public class AdvanceCompassSearchHelper extends CompassSearchHelper {

	private String[] highlightFields;

	public String[] getHighlightFields() {
		return highlightFields;
	}

	public void setHighlightFields(String[] highlightFields) {
		this.highlightFields = highlightFields;
	}

	/**
	 * @param compass
	 */
	public AdvanceCompassSearchHelper(Compass compass) {
		super(compass);
	}
	

	/* (non-Javadoc)
	 * @see org.compass.core.support.search.CompassSearchHelper#doProcessBeforeDetach(org.compass.core.support.search.CompassSearchCommand, org.compass.core.CompassSession, org.compass.core.CompassHits, int, int)
	 */
	@Override
	protected void doProcessBeforeDetach(CompassSearchCommand searchCommand,
			CompassSession session, CompassHits hits, int from, int size) {
		if (from < 0) {
			from = 0;
			size = hits.getLength();
		}

		if (highlightFields == null) {
			return;
		}
		// highlight fields
		for (int i = from; i < size; i++) {
			for (String highlightField : highlightFields) {
				hits.highlighter(i).fragment(highlightField);
			}
		}
	}
	
}
