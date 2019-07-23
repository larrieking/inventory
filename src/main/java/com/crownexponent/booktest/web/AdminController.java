/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.web;

import com.crownexponent.booktest.entity.Account;
import com.crownexponent.booktest.entity.Role;
import com.crownexponent.booktest.service.AccountFacade;
import com.crownexponent.booktest.util.GenerateSHA56;
import com.crownexponent.booktest.util.Message;
import com.crownexponent.booktest.util.SendMail;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author ISSAH OJIVO
 */
@Named(value = "adminController")
@SessionScoped
public class AdminController implements Serializable {
    
 

    private String username, password, firstName, lastName, role;
    
    @EJB
    private AccountFacade accountFacade;
    /**
     * Creates a new instance of AdminController
     */
    public AdminController() {
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void createAdmin(){
        //context =  FacesContext.getCurrentInstance();
        Role tableRole = new Role();
        tableRole.setRoleName(getRole());
        
        Account account = new Account();
        try {
            account = new Account(getUsername(),new GenerateSHA56().generateSha256(getPassword()), getFirstName(), getLastName());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        account.getRole().add(tableRole);
        tableRole.getAccount().add(account);
        try{
        getAccountFacade().create(account);
        List<String>list = new ArrayList<>();
        list.add(username);
       // new SendMail().sendMail(list, "Account Creation At BUPOWER", "Pls Click here your acct have bn created...", null);
        new Message().addSuccessMessage("Success");
        reset();
        }
        catch(ConstraintViolationException  e){
            new Message().addFailureMessage(e.getMessage());
        }
        //getRoleFacade().create(role);
        
        //System.out.println(getUsername() + getPassword()+getFirstName()+getLastName());
    }

    private AccountFacade getAccountFacade() {
        return accountFacade;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
    
    public void reset(){
        setUsername("");
        setPassword("");
        setFirstName("");
        setLastName("");
        
                
    }
    
    
    
    
    
}
