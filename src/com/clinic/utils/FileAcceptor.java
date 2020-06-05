package com.clinic.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Part;

public class FileAcceptor {

	public static String filePath = null;
	
	public FileAcceptor() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unused")
	public static void acceptFile(Part filePart) throws IOException {
		String fileName = getFileName(filePart);
		acceptFileHelper(filePart, fileName);
	}
	
	@SuppressWarnings("unused")
	public static String acceptFile(Part filePart, String fileName) throws IOException {
		return acceptFileHelper(filePart, fileName);
	}
	
	@SuppressWarnings("unused")
	private static String acceptFileHelper(Part filePart, String fileName) throws IOException {
		
		System.out.println("fileName before: "+fileName);
		
		int id = fileName.indexOf('.');
		
		System.out.println("id before: "+id);
		
		int nid = (id < 0)?fileName.length():id;
		
		System.out.println("id after: "+nid);
		
		String flnm = fileName.substring(0, nid).replaceAll("[^a-zA-Z0-9_-]", "");
				
		fileName = flnm + ((id < 0)?"":("."+fileName.substring(nid+1)));
		//fileName = fileName.replaceAll("[^a-zA-Z0-9_-]", "");
		
		System.out.println("fileName after: "+fileName);
		
	    OutputStream out = null;
	    InputStream filecontent = null;
	    //String fileName;
		try {
			if (filePath == null) {
				filePath = System.getenv("OPENSHIFT_DATA_DIR");
				if(filePath == null) {
					Context env = (Context)new InitialContext().lookup("java:comp/env");				
					filePath = (String)env.lookup("fileuploadpath");
				}
			}
		    //fileName = getFileName(filePart);
	        out = new FileOutputStream(new File(filePath + File.separator
	                + fileName));
	        filecontent = filePart.getInputStream();

	        int read = 0;
	        final byte[] bytes = new byte[1024];

	        while ((read = filecontent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
		}
		catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	    finally {
	        if (out != null) {
	            out.close();
	        }
	        if (filecontent != null) {
	            filecontent.close();
	        }
	    }
		
		return fileName;
	}
	
	public static String getFileName(final Part part) {
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
