/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.web;

import com.crownexponent.booktest.entity.Account;
import com.crownexponent.booktest.entity.Role;
import com.crownexponent.booktest.service.AccountFacade;
import com.crownexponent.booktest.service.RoleFacade;
import com.crownexponent.booktest.util.GenerateSHA56;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISSAH OJIVO
 */
@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable {
    
    private String username, password, firstname, lastname;
    @EJB
    private AccountFacade accountFacade;
    
    @EJB
    private RoleFacade roleFacade;
    

    /**
     * Creates a new instance of UserController
     */
    public UserController() {
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
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the accountFacade
     */
    public AccountFacade getAccountFacade() {
        return accountFacade;
    }

    /**
     * @return the roleFacade
     */
    public RoleFacade getRoleFacade() {
        return roleFacade;
    }
    
    
    public void create_user(){
        Role role = new Role();
        role.setRoleName("user");
        
        Account account = new Account();
        try {
            account = new Account(getUsername(),new GenerateSHA56().generateSha256(getPassword()), getFirstname(), getLastname());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        account.getRole().add(role);
        role.getAccount().add(account);
        
        getAccountFacade().create(account);
        //getRoleFacade().create(role);
        
        System.out.println(getUsername() + getPassword()+getFirstname()+getLastname());
    }
    
   
    
}
