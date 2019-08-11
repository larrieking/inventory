/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.web;

import com.crownexponent.booktest.entity.Account;
import com.crownexponent.booktest.entity.PasswordReset;
import com.crownexponent.booktest.service.AccountFacade;
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
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ISSAH OJIVO
 */
@Named(value = "newPassword")
@RequestScoped
public class NewPassword {

    /**
     * Creates a new instance of EmailSessionBean
     */
    private PasswordReset reset;
    @EJB
    private AccountFacade account;
    @EJB
    private PasswordResetFacade passwordReset;

    private String newPassword, confirm;
    private String hidden;

    public AccountFacade getAccount() {
        return account;
    }

    public PasswordResetFacade getPasswordReset() {
        return passwordReset;
    }

    private String to;

    public NewPassword() {

    }

    @PostConstruct
    public void init() {

        //return hidden;
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

    public String getHidden() {
        FacesContext fc = FacesContext.getCurrentInstance();
        String param = fc.getExternalContext().getRequestParameterMap().get("id");
        if (param != null) {
            hidden = param;
        }

        System.out.println("Request Parameter: " + hidden);
        return hidden;
    }

    public String newPass() {
        if (getNewPassword().equalsIgnoreCase(getConfirm()) == false) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("error", "Password Mismatch");
            System.out.println("Password Mismatch");
            return null;
        } //PasswordReset reset = getPasswordReset().findByToken(hidden);
        else {
            // FacesContext fc = FacesContext.getCurrentInstance();
            // setHidden(fc.getExternalContext().getRequestParameterMap().get("id"));

          //  System.out.println("Request Parameter: " + hidden);
            setReset(getPasswordReset().findByToken(getHidden()));
            String email = getReset().getEmail();

            try {
                Account account = getAccount().findByEmail(email);
                account.setPassword(new GenerateSHA56().generateSha256(getNewPassword()));
                getAccount().edit(account);
                 System.out.println("Account Edited Successfully");
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", "Password Changed");
            } catch (Exception ex) {
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put("error", ex.getMessage());
                System.out.println("An error occured in the edit");
            }
        }

        return null;

    }

    public boolean isElapsed() {
        try {

            setReset(getPasswordReset().findByToken(getHidden()));

            if (getReset() != null) {
                LocalDateTime time = new GenerateSHA56().convertStringToLocalDate(getReset().getDate());

                return LocalDateTime.now().isBefore(time.plusMinutes(15));
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the confirm
     */
    public String getConfirm() {
        return confirm;
    }

    /**
     * @param confirm the confirm to set
     */
    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    /**
     * @param hidden the hidden to set
     */
    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    /**
     * @return the reset
     */
    public PasswordReset getReset() {
        return reset;
    }

    /**
     * @param reset the reset to set
     */
    public void setReset(PasswordReset reset) {
        this.reset = reset;
    }

}
