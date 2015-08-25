/**
 * <pre>
 * Copyright:		Copyright(C) 2002-2006, jdkcn.com
 * Filename:		IdentifyingCodeImageServlet.java
 * Class:			IdentifyingCodeImageServlet
 * Date:			Oct 10, 2006
 * Author:			<a href="mailto:rory.cn@gmail.com">somebody</a>
 * Description:		
 *
 *
 * ======================================================================
 * Change History Log
 * ----------------------------------------------------------------------
 * Mod. No.	| Date		| Name			| Reason			| Change Req.
 * ----------------------------------------------------------------------
 * 			| Oct 10, 2006   | Rory Ye	    | Created			|
 *
 * </pre>
 **/
package com.jdkcn.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jdkcn.util.Constants;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @author <a href="mailto:rory.cn@gmail.com">somebody</a>
 * @since Oct 10, 2006
 * @version $Id IdentifyingCodeImageServlet.java$
 */
public class IdentifyingCodeImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5382766069139170499L;
	
    private static final String CONTENT_TYPE = "image/jpeg";
    
    private static final Log log = LogFactory.getLog(IdentifyingCodeImageServlet.class);
    
    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType(CONTENT_TYPE);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        
        String checkCode = RandomStringUtils.randomAlphanumeric(4);
        checkCode = checkCode.toLowerCase();
        
        BufferedImage image = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        Font font = new Font("Bookman Old Style", Font.BOLD, 25);
        g2d.setFont(font);
        g2d.setColor(Color.white);
        g2d.fill(new Rectangle(image.getWidth(), image.getHeight()));
        g2d.setColor(Color.BLUE);

        for (int i = 0; i < ((int) (Math.random() * 60) + 20); ++i) {

            g2d.fill(new Rectangle((int) (Math.random() * image.getWidth()),
                    (int) (Math.random() * image.getHeight()), 1, 1));
        }

        g2d.setPaint(Color.black);
        g2d.drawString(checkCode, 3, 23); // (int) (Math.random() * 15) + 5
        g2d.dispose();
        
        HttpSession session = request.getSession();
        session.setAttribute(Constants.IDENTIFYING_CODE_SESSION_NAME,checkCode);
        try {

            // ImageIO.write(image, "gif", response.getOutputStream());
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response.getOutputStream());
            encoder.encode(image);
        } catch (Exception ex) {

            log.info(ex);
        }
    }
}
