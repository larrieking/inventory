/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.web;

import com.crownexponent.booktest.entity.NewProduct;
import com.crownexponent.booktest.entity.StockAdjustment;
import com.crownexponent.booktest.service.StockAdjustmentFacade;
import com.crownexponent.booktest.util.Message;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.inject.Inject;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ISSAH OJIVO
 */
@Named(value = "adjustStock")
@RequestScoped
public class AdjustStock implements Serializable {

    /**
     * Creates a new instance of AdjustStock
     */
    private List<NewProduct> productList;
    private List<StockAdjustment> movementHistory;
    @EJB
    private StockAdjustmentFacade facade;

    @Inject
    private ProductController product;
    private StockAdjustment stockAdjustment;

    public AdjustStock() {
    }

    @PostConstruct
    public void init() {
        stockAdjustment = new StockAdjustment();
    }

    /**
     * @return the facade
     */
    public StockAdjustmentFacade getFacade() {
        return facade;
    }

    /**
     * @return the product
     */
    public ProductController getProduct() {
        return product;
    }

    /**
     * @return the stockAdjustment
     */
    public StockAdjustment getStockAdjustment() {
        //stockAdjustment = new StockAdjustment();
        return stockAdjustment;
    }

    public String createStokckAdjustment() {
        try {
            String typeOfAdjustment = getStockAdjustment().getAdjustmenttype();
            if (typeOfAdjustment.equalsIgnoreCase("outgoing")) {
                for (NewProduct editStock : productList) {
                    if (editStock.getItemName().equalsIgnoreCase(getStockAdjustment().getItemname())) {
                        if (editStock.getOpeningStock() <= getStockAdjustment().getNewquantity()) {
                            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("failure", "Item " + editStock.getItemName().toUpperCase() + " is not enough in inventory");
                            return "adjustStock?faces-redirect=true";
                        }
                    }
                }
            }
            LocalDate ldt = LocalDate.now();
            getStockAdjustment().setAdjustedby(product.getCreatedBy());
            getStockAdjustment().setDate(ldt.toString());
            if (typeOfAdjustment.equalsIgnoreCase("outgoing")) {
                getStockAdjustment().setNewquantity(-1 * getStockAdjustment().getNewquantity());
            }

            getFacade().create(stockAdjustment);

            for (NewProduct editStock : productList) {
                if (editStock.getItemName().equalsIgnoreCase(getStockAdjustment().getItemname())) {

                    editStock.setOpeningStock(editStock.getOpeningStock() + getStockAdjustment().getNewquantity());

                    getProduct().editStockQuantity(editStock);
                }
            }
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", "SUCCESS");
            // new Message().addSuccessMessage("Adjustment posted succesfully");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("failure", e.getMessage());
        }
        return "adjustStock?faces-redirect=true";
    }

    /**
     * @return the productList
     */
    public List<NewProduct> getProductList() {
        productList = product.getAllStock();
        return productList;
    }

    public List<StockAdjustment> getMovementHistory() {
        movementHistory = getFacade().findAll();
        return movementHistory;
    }

    public List<StockAdjustment> viewSingleItem() {
        FacesContext context = FacesContext.getCurrentInstance();
        String param = context.getExternalContext().getRequestParameterMap().get("id");
        System.out.println(param);
        return getFacade().findByName(param);
    }

}
