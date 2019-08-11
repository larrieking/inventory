/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.web;

import com.crownexponent.booktest.entity.NewProduct;
import com.crownexponent.booktest.service.NewProductFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author ISSAH OJIVO
 */
@Named(value = "dashboard")
@SessionScoped
public class Dashboard implements Serializable {

    /**
     * Creates a new instance of Dashboard
     */
    @EJB
    private NewProductFacade productFacade;
    private List<NewProduct> products;
    private List<NewProduct> overstock, understock, optimal;
    private List<NewProduct> levels = new ArrayList<>();
    
    public Dashboard() {
    }

    /**
     * @return the stock
     */
    public  NewProductFacade getProductFacade() {
        return productFacade;
    }

    /**
     * @return the products
     */
    public List<NewProduct> getProducts() {
        return getProductFacade().findAll();
       
    }
    
    
    public int getNoOfItems(){
        return getProductFacade().count();
    }
    
    public List<NewProduct>  getOverStocked(){
        products = getProducts();
        overstock = new ArrayList<>();
       for(NewProduct p : products )
           if(p.getOpeningStock() >= p.getOverstock())
               overstock.add(p);
       return overstock;
    }
    
     public List<NewProduct>  getUnderStocked(){
        products = getProducts();
        understock = new ArrayList<>();
       for(NewProduct p : products )
           if(p.getOpeningStock() <= p.getUnderstock())
               understock.add(p);
       return understock;
    }
     
     public List<NewProduct>  getOeptimal(){
        products = getProducts();
        optimal = new ArrayList<>();
       for(NewProduct p : products )
           if(p.getOpeningStock() == p.getOptimal())
               optimal.add(p);
       return optimal;
    }
     
     

    /**
     * @return the levelReport
     */
    public String levelReport() {
        FacesContext fc = FacesContext.getCurrentInstance();
        String data = fc.getExternalContext().getRequestParameterMap().get("id");
       
        if(data.equalsIgnoreCase("noofitems"))
            levels = getProducts();
        else if (data.equalsIgnoreCase("overstock"))
            levels = getOverStocked();
           
         else if (data.equalsIgnoreCase("underStocked"))
            levels = getUnderStocked();
        else if (data.equalsIgnoreCase("optimal"))
            levels = getOeptimal();
        
        return "stockLevel?faces-redirect=true";
    }

    /**
     * @param params the params to set
     */
    

    /**
     * @return the levels
     */
    public List<NewProduct> getLevels() {
        return levels;
    }
    
    
    
    
    
    
    
    
    
    

    /**
     * @param levelReport the levelReport to set
     */
    
     
     
     
}
