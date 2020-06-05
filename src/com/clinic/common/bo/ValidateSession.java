package com.clinic.common.bo;

import javax.servlet.http.*;

import com.clinic.common.datarec.UserProfile;

public class ValidateSession {

	private static final String sessionExpMsg = "<p style=\"color:red\">Your session expired.</p><br><p>Please Logout and login agian.</p><br>";
	
	public ValidateSession() {
		// TODO Auto-generated constructor stub
	}

	public static boolean validate (HttpServletRequest request) {
		
		boolean valid_session = false;
        HttpSession session = request.getSession(false);
        UserProfile user = null;

        if(session != null)
        {
        	user = (UserProfile)session.getAttribute("userprofile");
        	if(user != null)
        	{
        		valid_session = true;
        	}
        }
        
        return valid_session;
	}
		
	public static String sessionExpMsg() {
		return sessionExpMsg;
	}
}
