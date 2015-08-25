package com.jdkcn.util;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


public class HttpUtil {

	private HttpUtil(){}
	
    @SuppressWarnings("unchecked")
	public static String buildOriginalURL(HttpServletRequest request) {
        StringBuffer originalURL = request.getRequestURL();
        Map<String,String[]> parameters = request.getParameterMap();
        if (parameters != null && parameters.size() > 0) {
            originalURL.append("?");
            for (Iterator<String> iter = parameters.keySet().iterator(); iter.hasNext();) {
                String key =  iter.next();
                String[] values = parameters.get(key);
                for (int i = 0; i < values.length; i++) {
                    originalURL.append(key).append("=").append(values[i]).append("&");
                }
            }
        }
        return originalURL.toString();
    }
    
    @SuppressWarnings("unchecked")
	public static String buildOriginalGETURL(HttpServletRequest request) {
        StringBuffer originalURL = request.getRequestURL();
        if(request.getMethod().equalsIgnoreCase("POST")){
        	return originalURL.toString();
        }
        Map<String,String[]> parameters = request.getParameterMap();
        if (parameters != null && parameters.size() > 0) {
            originalURL.append("?");
            for (Iterator<String> iter = parameters.keySet().iterator(); iter.hasNext();) {
                String key =  iter.next();
                String[] values =  parameters.get(key);
                for (int i = 0; i < values.length; i++) {
                    originalURL.append(key).append("=").append(values[i]).append("&");
                }
            }
        }
        return originalURL.toString();
    }

    public static void printCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                System.out.print("cookie name = [" + cookies[i].getName() + "]\t\t");
                System.out.println("cookie value = [" + cookies[i].getValue());
            }
        }
    }

}
