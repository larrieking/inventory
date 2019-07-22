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
import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author ISSAH OJIVO
 */
@Named(value = "productController")
@SessionScoped
public class ProductController implements Serializable {

    /**
     * Creates a new instance of ProductController
     */
   private String no, name, category, createdBy, date, uses, uom;
   private int openinStock;

    
    @EJB
    private NewProductFacade facade ;
    @Inject
    private ValidateUser authenticatedUser;
    
    public ProductController() {
    }
    
     public NewProductFacade getFacade() {
        return facade;
    }
     
     public String createProduct(){
         NewProduct product = new NewProduct();
         product.setItemName(getName());
         product.setItemClass(getCategory());
         product.setDate(getDate());
         product.setOpeningStock(getOpeninStock());
         product.setUom(getUom());
         product.setUses(getUses());
         product.setItemNo(getNo());
         product.setCreatedBy(getCreatedBy());
         getFacade().create(product);
         return null;
         
     }

    /**
     * @return the no
     */
   
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return getAuthenticatedUser().getLoggedUser().getEmail();
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the uses
     */
    public String getUses() {
        return uses;
    }

    /**
     * @param uses the uses to set
     */
    public void setUses(String uses) {
        this.uses = uses;
    }

    /**
     * @return the uom
     */
    public String getUom() {
        return uom;
    }

    /**
     * @param uom the uom to set
     */
    public void setUom(String uom) {
        this.uom = uom;
    }

    /**
     * @return the openinStock
     */
    public int getOpeninStock() {
        return openinStock;
    }

    /**
     * @param openinStock the openinStock to set
     */
    public void setOpeninStock(int openinStock) {
        this.openinStock = openinStock;
    }

    /**
     * @return the no
     */
    public String getNo() {
        return no;
    }

    /**
     * @param no the no to set
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * @return the admin
     */
   

    /**
     * @return the authentuicatedUser
     */
    public ValidateUser getAuthenticatedUser() {
        return authenticatedUser;
    }
    
    public List<NewProduct> getAllStock(){
        return getFacade().findAll();
    }
    
    public String deleteProduct(NewProduct product){
        getFacade().remove(product);
        return null;
    }
    
    public void editStockQuantity(NewProduct product){
        getFacade().edit(product);
        
    }
    
    
    public int getNoOfItems(){
        return getFacade().count();
    }
    
}
