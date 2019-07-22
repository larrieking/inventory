/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.web;

import com.crownexponent.booktest.entity.Account;
import com.crownexponent.booktest.entity.Role;
import com.crownexponent.booktest.service.AccountFacade;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ISSAH OJIVO
 */
@Named(value = "validateUser")
@SessionScoped
public class ValidateUser implements Serializable {

    /**
     * Creates a new instance of ValidateUser
     */
    
    @EJB
    private AccountFacade facade;
    private boolean admin;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    private String username, password;
    public ValidateUser() {
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
    
    public String login(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        try {
            request.login(username, password);
            for(Role role : getLoggedUser().getRole()){
                if(role.getRoleName().equalsIgnoreCase("User")){
                     setAdmin(false);
                     return "user/home?faces-redirect=true";
                }
                    
               
                else{
                    setAdmin(true);
                    return "admin/home?faces-redirect=true";
                }
            }
        } catch (ServletException ex) {
           // Logger.getLogger(ValidateUser.class.getName()).log(Level.SEVERE, null, ex);
           context.addMessage(null,new FacesMessage("Login error"));
        }
        
        return null;
        
    }
    
    public String logout(){
         FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
        
        try {
            request.logout();
            return "/index?faces-redirect=true";
        } catch (ServletException ex) {
            Logger.getLogger(ValidateUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
     public Account getLoggedUser(){
       return getFacade().findByEmail(username);
    }
     
     public List<Account> getAllUsersAndAdmins(){
         return getFacade().findAll();
     }
     
     public String  deleteUser(Account account){
         getFacade().remove(account);
         return null;
         
         
     }
     
     public String getRoleName(Set<Role>role){
         String outcome = null;
         for(Role rol : role){
             if(rol.getRoleName().equalsIgnoreCase("User"))
                outcome = "User";
             else if (rol.getRoleName().equalsIgnoreCase("Admin"))
                 outcome = "Admin";
                     
         }
         
         return outcome;
     }

    /**
     * @return the facade
     */
    public AccountFacade getFacade() {
        return facade;
    }

    /**
     * @param facade the facade to set
     */
    
    
}
