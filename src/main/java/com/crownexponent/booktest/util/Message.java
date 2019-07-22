/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author ISSAH OJIVO
 */



public class Message {
   FacesContext context;

    public Message() {
        context = FacesContext.getCurrentInstance();
    }
    
    public void addSuccessMessage(String msg){
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
    }
    
    public void addFailureMessage(String msg){
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
    }
   
   
}
