/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.web;

import com.crownexponent.booktest.entity.Account;
import com.crownexponent.booktest.entity.PasswordReset;
import com.crownexponent.booktest.service.AccountFacade;
import com.crownexponent.booktest.service.Mail;
import com.crownexponent.booktest.service.PasswordResetFacade;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.crownexponent.booktest.util.GenerateSHA56;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author ISSAH OJIVO
 */
@Named(value = "emailSessionBean")
@RequestScoped
public class EmailSessionBean {

    /**
     * Creates a new instance of EmailSessionBean
     */
   
    @EJB
    private AccountFacade account;
    @EJB
    private PasswordResetFacade passwordReset;
    @EJB
    private Mail mailSession;

   

    public AccountFacade getAccount() {
        return account;
    }

    public PasswordResetFacade getPasswordReset() {
        return passwordReset;
    }

    

    private String to;

    public EmailSessionBean() {
    }

    
    
    public String sendMessage() {
         String token = new GenerateSHA56().generateToken();
        String add = "<a href = \"www.codershift.com/faces/newPassword.xhtml?id=" + token + "\">" + "click here to reset your password</a>";
         String content = "<h3>Hello,</h3><p>pls click on the link below to reset your password</p><br />" + add + "<br /><p>Pls note that the link above expires after 15 minutes</p><p>Warm Regards, </p><br />Codershift Team";

        try {
            Account email = getAccount().find(to);

            PasswordReset reset = getPasswordReset().find(to);
            //LocalDateTime date = LocalDateTime.now().plusMinutes(15);
            //String token = new GenerateSHA56().generateToken();
            if (email != null && reset == null) {
                PasswordReset pass = new PasswordReset(email.getEmail(), token, new GenerateSHA56().formatDate(LocalDateTime.now()));
                getPasswordReset().create(pass);
                List<String>toAdd = new ArrayList<>();
                toAdd.add(email.getEmail());
                getMailSession().sendMail(toAdd, "Password Reset for BUPPOWER Inventory", content);
                //sendMail(token, email.getEmail(), "Password Reset for BUPPOWER Inventory");
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", "Password Reset Instructions sent to " + email.getEmail());
            } else if (email != null && reset != null) {
                PasswordReset pass = new PasswordReset(email.getEmail(), token, new GenerateSHA56().formatDate(LocalDateTime.now()));
                getPasswordReset().edit(pass);
                 List<String>toAdd = new ArrayList<>();
                toAdd.add(email.getEmail());
                getMailSession().sendMail(toAdd, "Password Reset for BUPPOWER Inventory", content);
                //sendMail(token, email.getEmail(), "Password Reset for BUPPOWER Inventory");
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", "Password Reset Instructions sent to " + email.getEmail());
            } else {
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put("failure", "email  address not found");
            }

            //String token = new GenerateSHA56().generateToken();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("failure", e.getMessage());
        }
        return "forgotPassword?faces-redirect=true";
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return the mailSession
     */
    public Mail getMailSession() {
        return mailSession;
    }

   

}
