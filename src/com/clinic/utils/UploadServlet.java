package com.clinic.utils;

import java.io.*;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
 
/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String filePath;
	
   /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	   response.setContentType("text/html;charset=UTF-8");

	    final PrintWriter writer = response.getWriter();
	    String fileName;
	    String[] selfiles = request.getParameterValues("file");
	    Map<String,String[]> rm = request.getParameterMap();
	    for (Map.Entry<String, String[]> entry : rm.entrySet())
	    {
	    	writer.print(entry.getKey() + "/[");
	    	String[] sa = entry.getValue();
	    	for (String ss : sa) {
	    		writer.print(ss+",");
	    	}
	        writer.println("]<br>");
	    }
	    Part filePart;
		try {

		    if (selfiles == null) {
			    filePart = request.getPart("cntrfile");
			    FileAcceptor.acceptFile(filePart);
			    fileName = getFileName(filePart);
		        writer.println("New file " + fileName + " created at " + filePath+"<br>");
		        String[] ns = new String[1];
		        ns[0] = fileName;
		        rm.put("cntrfile", ns);
		    }
		    else{
			    for (String sf: selfiles) {
	
				    filePart = request.getPart("file"+sf);
				    FileAcceptor.acceptFile(filePart);
				    fileName = getFileName(filePart);
			        writer.println("New file " + fileName + " created at " + filePath+"<br>");
			        String[] ns = new String[1];
			        ns[0] = fileName;
			        rm.put("file"+sf, ns);
			    }
		    }
		    for (Map.Entry<String, String[]> entry : rm.entrySet())
		    {
		    	writer.print(entry.getKey() + "/[");
		    	String[] sa = entry.getValue();
		    	for (String ss : sa) {
		    		writer.print(ss+",");
		    	}
		        writer.println("]<br>");
		    }
	    } catch (FileNotFoundException fne) {
	        writer.println("You either did not specify a file to upload or are "
	                + "trying to upload a file to a protected or nonexistent "
	                + "location.");
	        writer.println("<br/> ERROR: " + fne.getMessage());

	    }

	    finally {
	        if (writer != null) {
	            writer.close();
	        }
	    }    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private String getFileName(final Part part) {
	    //final String partHeader = part.getHeader("content-disposition");
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
}
