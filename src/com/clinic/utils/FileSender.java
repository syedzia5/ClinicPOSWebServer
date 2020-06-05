package com.clinic.utils;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clinic.common.bo.ValidateSession;
import com.clinic.common.exception.DBErrorException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
 
/**
 * Servlet implementation class FileSender
 */
@WebServlet("/FileSender")
public class FileSender extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public static String filePath = null;

	public FileSender() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//response.setContentType("text/html");//setting the content type  
		//PrintWriter pw=response.getWriter();//get the stream to write the data  
		
        //if(!ValidateSession.validate(request))
        //{
            //pw.println(ValidateSession.sessionExpMsg());    
           // return;
       // }
        
        OutputStream outStream = null;
        FileInputStream inStream = null;

		try {
			
			if (filePath == null) {
				filePath = System.getenv("OPENSHIFT_DATA_DIR");
				if(filePath == null) {
					Context env = (Context)new InitialContext().lookup("java:comp/env");				
					filePath = (String)env.lookup("fileuploadpath");
				}
			}

			String filename = filePath + File.separator + request.getParameter("dwnfile");
			
	        File downloadFile = new File(filename);
	        inStream = new FileInputStream(downloadFile);
	        // if you want to use a relative path to context root:
	        /*
	        String relativePath = getServletContext().getRealPath("");
	        System.out.println("relativePath = " + relativePath);
	         */
	        
	        /**/
	        // obtains ServletContext
	        ServletContext context = getServletContext();
	         
	        // gets MIME type of the file
	        String mimeType = context.getMimeType(filename);
	        if (mimeType == null) {        
	            // set to binary type if MIME mapping not found
	            mimeType = "application/octet-stream";
	        }
	        System.out.println("MIME type: " + mimeType);
	        /**/
	        // modifies response
	        response.setContentType(mimeType);
	        response.setContentLength((int) downloadFile.length());
	         
	        // forces download
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
	        response.setHeader(headerKey, headerValue);
	         
	        // obtains response's output stream
	        outStream = response.getOutputStream();
	         
	        byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	        
	        
	        if(!ValidateSession.validate(request))
	        {
	            //pw.println(ValidateSession.sessionExpMsg()); 
	        	String a = "Invalid Request";
	        	buffer = a.getBytes();
	    
	        	
	        	outStream.write(buffer, 0, a.length());
	            return;
	        }

	        while ((bytesRead = inStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
		}
		catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception ef) {
			ef.printStackTrace();			
		}
		finally {
	        if(inStream != null) {inStream.close();}
	        if(outStream != null) {outStream.close();}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
