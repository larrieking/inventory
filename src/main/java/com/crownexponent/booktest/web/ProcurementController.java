/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.web;

import com.crownexponent.booktest.entity.ProcurementMemo;
import com.crownexponent.booktest.service.ProcurementMemoFacade;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 *
 * @author golan
 */
@Named(value = "procurementController")
@RequestScoped
public class ProcurementController {

    /**
     * Creates a new instance of ProcurementController
     */
    
    @EJB
    private ProcurementMemoFacade facade;
    private ProcurementMemo memo;

    @PostConstruct
    public void init(){
        memo = new ProcurementMemo();
    }
    
    public ProcurementController() {
        
    }
    
    public ProcurementMemoFacade getFacade() {
        return facade;
    }

    /**
     * @return the memo
     */
    public ProcurementMemo getMemo() {
        return memo;
    }

    public void setMemo(ProcurementMemo memo) {
        this.memo = memo;
    }
    
    
    
    
    public String create(){
        try{
            getFacade().create(memo);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", "Success!!");
        }
        catch(Exception e){
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("failure", e.getMessage());
        }
        return "procurement?faces-redirect=true";
    }
    
    public String edit(){
        try{
            getFacade().edit(getMemo());
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", "Success!!");
        }
        catch(Exception e){
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("failure", e.getMessage());
        }
        return "viewProcurementMemo?faces-redirect=true";
    }
    
    
    public String delete(ProcurementMemo editMemo){
        try{
            getFacade().remove(editMemo);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", "Success!!");
        }
        catch(Exception e){
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("failure", e.getMessage());
        }
        return "viewProcurementMemo?faces-redirect=true";
    }
    
    
    
     public List<ProcurementMemo>findAll(ProcurementMemo editMemo){
        return getFacade().findAll();
    
}
     
     public String prepareEdit(ProcurementMemo toEdit){
         //setMemo(toEdit);
         FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", toEdit);
         return"editMemo.xhtml";
     }
}