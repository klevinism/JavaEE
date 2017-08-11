package utils;

import java.util.*;

import javax.faces.context.FacesContext;
import javax.mail.*;  
import javax.mail.internet.*;
import javax.servlet.http.HttpServletRequest;  

public class EmailSender {

	public static void send(String senderEmailAddress, String senderPassword, String recipientEmail) {

	      final String username = senderEmailAddress;
		  final String password = senderPassword;
		  final String to = recipientEmail;  
		  
		  final HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();

		  String scheme = request.getScheme();
		  String host = request.getHeader("Host");        // includes server name and server port
		  String contextPath = request.getContextPath();  // includes leading forward slash

		  String resultPath = scheme + "://" + host + contextPath + "/signupVerification.xhtml";
		  
		  //Get the session object  
   		  Properties props = new Properties();
		  props.put("mail.smtp.auth", "true");
		  props.put("mail.smtp.starttls.enable", "true");
		  props.put("mail.smtp.host", "smtp.gmail.com");
		  props.put("mail.smtp.port", "587");

		  Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
			  		protected PasswordAuthentication getPasswordAuthentication() {
			  			return new PasswordAuthentication(username, password);
			  		}
		  		  });
	  
	      //compose the message  
	      try{  
	         MimeMessage message = new MimeMessage(session);  
	         message.setFrom(new InternetAddress(senderEmailAddress));  
	         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	         message.setSubject("JSF email verification ");  
	         message.setContent("Hello, click <a href='"+resultPath+"?e="+senderEmailAddress+"'>here</a> to validate your account. <br/> Thank you! ", "text/html; charset=utf-8");  
	         
	         // Send message  
	         Transport.send(message);  
	  
	      }catch (MessagingException mex) {
	    	  mex.printStackTrace();
	      }  
	}

}