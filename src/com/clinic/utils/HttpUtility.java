package com.clinic.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.net.ssl.*;

import com.clinic.common.datarec.ClinicConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
* This class encapsulates methods for requesting a server via HTTP GET/POST and
* provides methods for parsing response from the server.
*
* @author www.codejava.net
*
*/

public class HttpUtility {

	
	static {
	    //for localhost testing only
	    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
	    new javax.net.ssl.HostnameVerifier(){

	        public boolean verify(String hostname,
	                javax.net.ssl.SSLSession sslSession) {
	        	System.out.println("Hostname: "+hostname);
	            if (hostname.equals("localhost") || hostname.equals("192.168.4.74")) {
	                return true;
	            }
	            return false;
	        }
	    });
	}
	
	public HttpUtility() {
		// TODO Auto-generated constructor stub
	}
	 
    /**
     * Represents an HTTP connection
     */
    private static HttpsURLConnection httpConn;
 
    /**
     * Makes an HTTP request using GET method to the specified URL.
     *
     * @param requestURL
     *            the URL of the remote server
     * @return An HttpURLConnection object
     * @throws IOException
     *             thrown if any I/O error occurred
     */
    public static HttpsURLConnection sendGetRequest(String requestURL)
            throws IOException {
    	SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        URL url = new URL(requestURL);
        httpConn = (HttpsURLConnection) url.openConnection();
        httpConn.setSSLSocketFactory(sslsocketfactory);
        httpConn.setUseCaches(false);
 
        httpConn.setDoInput(true); // true if we want to read server's response
        httpConn.setDoOutput(false); // false indicates this is a GET request
 
        return httpConn;
    }
 
    /**
     * Makes an HTTP request using POST method to the specified URL.
     *
     * @param requestURL
     *            the URL of the remote server
     * @param params
     *            A map containing POST data in form of key-value pairs
     * @return An HttpURLConnection object
     * @throws IOException
     *             thrown if any I/O error occurred
     */
    public static HttpsURLConnection sendPostRequest(String requestURL,
            Map<String, String> params) throws IOException {
    	SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        URL url = new URL(requestURL);
        httpConn = (HttpsURLConnection) url.openConnection();
        httpConn.setSSLSocketFactory(sslsocketfactory);
        httpConn.setUseCaches(false);
	     //dumpl all cert info
	     //print_https_cert(httpConn);
			
	     //dump all the content
	     //print_content(httpConn);

        //httpConn.setDoInput(true); // true indicates the server returns response
 
        StringBuffer requestParams = new StringBuffer();
 
        if (params != null && params.size() > 0) {
 
            //httpConn.setDoOutput(true); // true indicates POST request
 
            // creates the params string, encode them using URLEncoder
            Iterator<String> paramIterator = params.keySet().iterator();
            while (paramIterator.hasNext()) {
                String key = paramIterator.next();
                String value = params.get(key);
                requestParams.append(URLEncoder.encode(key, "UTF-8"));
                requestParams.append("=").append(
                        URLEncoder.encode(value, "UTF-8"));
                requestParams.append("&");
            }
 
            // sends POST data
            OutputStreamWriter writer = new OutputStreamWriter(
                    httpConn.getOutputStream());
            writer.write(requestParams.toString());
            writer.flush();
        }
 
        return httpConn;
    }
 
    /**
     * Returns only one line from the server's response. This method should be
     * used if the server returns only a single line of String.
     *
     * @return a String of the server's response
     * @throws IOException
     *             thrown if any I/O error occurred
     */
    public static String readSingleLineRespone(HttpsURLConnection conn) throws IOException {
        InputStream inputStream = null;
        if (conn != null) {
            inputStream = conn.getInputStream();
        } else {
            throw new IOException("Connection is not established.");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));
 
        String response = reader.readLine();
        reader.close();
 
        return response;
    }
 
    /**
     * Returns an array of lines from the server's response. This method should
     * be used if the server returns multiple lines of String.
     *
     * @return an array of Strings of the server's response
     * @throws IOException
     *             thrown if any I/O error occurred
     */
    public static String[] readMultipleLinesRespone(HttpsURLConnection conn) throws IOException {
        InputStream inputStream = null;
        if (conn != null) {
            inputStream = conn.getInputStream();
        } else {
            throw new IOException("Connection is not established.");
        }
 
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));
        List<String> response = new ArrayList<String>();
 
        String line = "";
        while ((line = reader.readLine()) != null) {
            response.add(line);
        }
        reader.close();
 
        return (String[]) response.toArray(new String[0]);
    }
    
    /**
     * Creates a file from the contents obtained from the URL specified as parameter
     *
      * @throws IOException
     *             thrown if any I/O error occurred
     */
    public static void getFile(String fileName) throws IOException {

		Map<String, String> params = new HashMap<String, String>();
		params.put("dwnfile", fileName);

		HttpsURLConnection conn = HttpUtility.sendPostRequest(ClinicConstants.VRTEHost+"FileSender", params);

	    OutputStream out = null;
		InputStream filecontent = null;
        if (conn != null) {
        	filecontent = conn.getInputStream();
        } else {
            throw new IOException("Connection is not established.");
        }

        out = new FileOutputStream(new File(System.getenv("OPENSHIFT_DATA_DIR") + File.separator
                + fileName));

        int read = 0;
        final byte[] bytes = new byte[1024];

        while ((read = filecontent.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        
        out.close();
    }
     
    /**
     * Closes the connection if opened
     */
    public static void disconnect(HttpsURLConnection conn) {
        if (conn != null) {
        	conn.disconnect();
        }
    }
    
    private static void print_https_cert(HttpsURLConnection con){
        
        if(con!=null){
    			
          try {
    				
    	System.out.println("Response Code : " + con.getResponseCode());
    	System.out.println("Cipher Suite : " + con.getCipherSuite());
    	System.out.println("\n");
    				
    	Certificate[] certs = con.getServerCertificates();
    	for(Certificate cert : certs){
    	   System.out.println("Cert Type : " + cert.getType());
    	   System.out.println("Cert Hash Code : " + cert.hashCode());
    	   System.out.println("Cert Public Key Algorithm : " 
                                        + cert.getPublicKey().getAlgorithm());
    	   System.out.println("Cert Public Key Format : " 
                                        + cert.getPublicKey().getFormat());
    	   System.out.println("\n");
    	}
    				
    	} catch (SSLPeerUnverifiedException e) {
    		e.printStackTrace();
    	} catch (IOException e){
    		e.printStackTrace();
    	}

         }
    	
       }
    	
       private static void print_content(HttpsURLConnection con){
    	if(con!=null){
    			
    	try {
    		
    	   System.out.println("****** Content of the URL ********");			
    	   BufferedReader br = 
    		new BufferedReader(
    			new InputStreamReader(con.getInputStream()));
    				
    	   String input;
    				
    	   while ((input = br.readLine()) != null){
    	      System.out.println(input);
    	   }
    	   br.close();
    				
    	} catch (IOException e) {
    	   e.printStackTrace();
    	}
    			
           }
    		
       }
}
