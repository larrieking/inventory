/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.web;

import com.crownexponent.booktest.entity.ProcurementMemo;
import com.crownexponent.booktest.service.ProcurementMemoFacade;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 *
 * @author golan
 */
@Named(value = "editProcurement")
@SessionScoped
public class EditProcurement implements Serializable {

    /**
     * Creates a new instance of EditProcurement
     */
    
    @EJB
    private ProcurementMemoFacade facade;
    private ProcurementMemo memo;
    
    public EditProcurement() {
    }
    
     public String prepareEdit(ProcurementMemo toEdit){
         //setMemo(toEdit);
         setMemo(getFacade().find(toEdit.getSerialNo()));
         //FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", toEdit);
         return "editMemo";
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

    /**
     * @return the facade
     */
    public ProcurementMemoFacade getFacade() {
        return facade;
    }

    /**
     * @return the memo
     */
    public ProcurementMemo getMemo() {
        return memo;
    }

    /**
     * @param memo the memo to set
     */
    public void setMemo(ProcurementMemo memo) {
        this.memo = memo;
    }
    
}
