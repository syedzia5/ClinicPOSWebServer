package com.clinic.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
 
/**
 * A utility class for sending e-mail messages
 * @author www.codejava.net
 *
 */
public class EmailUtility {
	private static Properties properties = null;
	private static  String userName;
	private static  String password;
	public static String filePath = null;
	
	private static void initProperties() {
		Context env;
		try {
			env = (Context)new InitialContext().lookup("java:comp/env");
			
			filePath = (String)env.lookup("fileuploadpath");

	        // Get a single value
	        userName = (String)env.lookup("user");
	        password = (String)env.lookup("pass");
	        
	        properties = new Properties();
	        properties.put("mail.smtp.host", (String)env.lookup("host"));
	        properties.put("mail.smtp.port", (String)env.lookup("port"));
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    public static void sendEmail(String toAddress,
            String subject, String message) throws AddressException,
            MessagingException {
 
    	if (properties == null) {
    		initProperties();
    	}
        // sets SMTP server properties
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setText(message);
 
        // sends the e-mail
        Transport.send(msg);
 
    }
    public static void sendEmail(String toAddress, String ccAddress,
            String subject, String message) throws AddressException,
            MessagingException {
 
    	if (properties == null) {
    		initProperties();
    	}
        // sets SMTP server properties
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        InternetAddress[] ccAddresses = { new InternetAddress(ccAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setRecipients(Message.RecipientType.CC, ccAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setText(message);
 
        // sends the e-mail
        Transport.send(msg);
 
    }

    public static void sendEmailWithAttachment(String toAddress,
            String subject, String message, List<String> attachFiles) throws AddressException,
            MessagingException {
 
    	if (properties == null) {
    		initProperties();
    	}
        // sets SMTP server properties
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
        if (attachFiles != null && attachFiles.size() > 0) {
            for (String filename : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(filePath + File.separator + filename);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
 
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
 
    }
    public static void sendEmailWithAttachment(String toAddress, String ccAddress,
            String subject, String message, List<String> attachFiles) throws AddressException,
            MessagingException {
 
    	if (properties == null) {
    		initProperties();
    	}
        // sets SMTP server properties
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        InternetAddress[] ccAddresses = { new InternetAddress(ccAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setRecipients(Message.RecipientType.CC, ccAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
        if (attachFiles != null && attachFiles.size() > 0) {
            for (String filename : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(filePath + File.separator + filename);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
 
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
 
    }
}