/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.util;

/**
 *
 * @author ISSAH OJIVO
 */
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
   public static void main(String[] args) {
      // Recipient's email ID needs to be mentioned.
      String to = "larrie4christ@gmail.com";

      // Sender's email ID needs to be mentioned
      String from = "admin@codershift.com";
      final String username = "admin@codershift.com";//change accordingly
      final String password = "Elshadai1986$";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "smtp.zoho.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "25");

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
	});

      try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

   	   // Set From: header field of the header.
	   message.setFrom(new InternetAddress(from));

	   // Set To: header field of the header.
	   message.setRecipients(Message.RecipientType.TO,
              InternetAddress.parse(to));

	   // Set Subject: header field
	   message.setSubject("Testing Subject");

	   // Send the actual HTML message, as big as you like
	   message.setContent(
              "<h1>This is actual message embedded in HTML tags</h1>",
             "text/html");
      
	   // Send message
	   Transport.send(message); 
      } catch (AddressException ex) {
           Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
       } catch (MessagingException ex) {
           Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
       }
           

      }
}