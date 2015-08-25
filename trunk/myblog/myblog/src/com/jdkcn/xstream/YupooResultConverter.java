/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		YupooResultConverter.java
 * Class:			YupooResultConverter
 * Date:			Jan 16, 2007 6:12:35 PM
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Jan 16, 2007   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.xstream;

import java.util.ArrayList;
import java.util.List;

import com.jdkcn.yupoo.YupooPhoto;
import com.jdkcn.yupoo.YupooResult;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Jan 16, 2007 6:12:35 PM
 * @version $Id YupooResultConverter.java$
 */
public class YupooResultConverter implements Converter {


	/* (non-Javadoc)
	 * @see com.thoughtworks.xstream.converters.Converter#marshal(java.lang.Object, com.thoughtworks.xstream.io.HierarchicalStreamWriter, com.thoughtworks.xstream.converters.MarshallingContext)
	 */
	public void marshal(Object obj, HierarchicalStreamWriter writer, MarshallingContext context) {
		// FIXME unfinish.
	}

	/* (non-Javadoc)
	 * @see com.thoughtworks.xstream.converters.Converter#unmarshal(com.thoughtworks.xstream.io.HierarchicalStreamReader, com.thoughtworks.xstream.converters.UnmarshallingContext)
	 */
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		YupooResult result = new YupooResult();
		result.setPage(new Integer(reader.getAttribute("page")));
		result.setPages(new Integer(reader.getAttribute("pages")));
		result.setPerpage(new Integer(reader.getAttribute("perpage")));
		result.setTotal(new Integer(reader.getAttribute("total")));
		reader.moveDown();
		List<YupooPhoto> photos = new ArrayList<YupooPhoto>();
		while(reader.hasMoreChildren()) {
			reader.moveDown();
			YupooPhoto photo = new YupooPhoto();
			photo.setDir(reader.getAttribute("dir"));
			photo.setFilename(reader.getAttribute("filename"));
			photo.setHost(reader.getAttribute("host"));
			photo.setId(reader.getAttribute("id"));
			photo.setOwner(reader.getAttribute("owner"));
			photo.setTitle(reader.getAttribute("title"));
			photos.add(photo);
			reader.moveUp();
		}
		result.setPhotos(photos);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.thoughtworks.xstream.converters.ConverterMatcher#canConvert(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public boolean canConvert(Class clazz) {
		return clazz.equals(YupooResult.class);
	}
	

}
