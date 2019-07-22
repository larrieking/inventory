/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.util;

import javax.annotation.Resource;
import javax.mail.Session;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import javax.activation.DataHandler;

/**
 *
 * @author ISSAH OJIVO
 */
public class SendMail {

    @Resource(lookup = "mail/mySession")
    private Session session;

    public boolean sendMail(List<String> recipients, String subject, String body, String... files) {
        try {
            MimeMessage message = composeMessage(recipients, subject, body, files);
            String user = session.getProperty("mail.user");
            String password = session.getProperty("mail.smtp.password");
            Transport.send(message, user, password);
            return true;
        } catch (MessagingException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private MimeMessage composeMessage(List<String> recipients, String subject, String body, String[] files) throws MessagingException {
        //To change body of generated methods, choose Tools | Templates.
        MimeMessage message = new MimeMessage(session);
        for (String recipient : recipients) {
            message.addRecipient(RecipientType.TO, new InternetAddress(recipient));
        }

        message.setSubject(subject);
        message.setContent(getMultipartBody(body, files));
        String from = session.getProperty("mail.from");
        message.setFrom(new InternetAddress(from));
        return message;
    }

    private Multipart getMultipartBody(String body, String[] files) throws MessagingException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(body);
        Multipart mp = new MimeMultipart();
        mp.addBodyPart(messageBodyPart);
        if (files != null) {
            for (String file : files) {
                addAttachment(mp, file);
            }
        }
        return mp;

    }

    private void addAttachment(Multipart mp, String file) throws MessagingException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (file.isEmpty()) {
            return;
        }
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        //File file1 = new File(file);
        messageBodyPart.setFileName(new File(file).getName());
        mp.addBodyPart(messageBodyPart);
    }

}
