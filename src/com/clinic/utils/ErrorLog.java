package com.clinic.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.clinic.utils.exception.ErrorLogException;

public class ErrorLog {

	public static String filePath = null;

	public ErrorLog() {
		// TODO Auto-generated constructor stub
	}

	public static void LogError(String classname, Exception e) throws ErrorLogException {
		
		 StackTraceElement[] stea = e.getStackTrace();
		 String s = e.getMessage()+"\r\n";
		 for (StackTraceElement ste : stea) {
			 s += ste.toString()+"\r\n";
		 }
		 
		 LogError(classname, s);
	}
	public static void LogError(String classname, String msg) throws ErrorLogException {
		
	    OutputStream out = null;
		try {
			if(filePath == null) {
				Context env = (Context)new InitialContext().lookup("java:comp/env");				
				filePath = (String)env.lookup("fileuploadpath");
			}
		    //fileName = getFileName(filePart);
			
			//String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			Date cdt =  Calendar.getInstance().getTime();
			String fts = new SimpleDateFormat("dd-MM-yyyy").format(cdt);
			String mts = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(cdt);;
			
	        out = new FileOutputStream(new File(filePath + File.separator
	                + "ErrorLog-"+fts+".txt"), true);
	        String fllmsg = mts+"|"+classname+"|"+msg;
	        
	        byte[] bytes = fllmsg.getBytes();
	        out.write(bytes, 0, fllmsg.length());
		}
		catch (NamingException|IOException e) {
			// TODO Auto-generated catch block
			
			throw new ErrorLogException("ErrorLog.LogError: "+e.getMessage());
		}
		catch (Exception e) {
			
			throw new ErrorLogException("ErrorLog.LogError: "+e.getMessage());
		}
	    finally {
	        if (out != null) {
	            try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					
					throw new ErrorLogException("ErrorLog.LogError: "+e.getMessage());
				}
	        }
	    }
	}
}
