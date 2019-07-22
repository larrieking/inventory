/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.web;

import com.crownexponent.booktest.entity.NewProduct;
import com.crownexponent.booktest.entity.StockAdjustment;
import com.crownexponent.booktest.service.StockAdjustmentFacade;
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

/**
 *
 * @author ISSAH OJIVO
 */
@Named(value = "adjustStock")
@SessionScoped
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
        String typeOfAdjustment = getStockAdjustment().getAdjustmenttype();
        LocalDate ldt = LocalDate.now();
        getStockAdjustment().setAdjustedby(product.getCreatedBy());
        getStockAdjustment().setDate(ldt.toString());
        if (typeOfAdjustment.equalsIgnoreCase("negative")) {
            getStockAdjustment().setNewquantity(-1 * getStockAdjustment().getNewquantity());
        }

        getFacade().create(stockAdjustment);
        for (NewProduct editStock : productList) {
            if (editStock.getItemName().equalsIgnoreCase(getStockAdjustment().getItemname())) {

                editStock.setOpeningStock(editStock.getOpeningStock() + getStockAdjustment().getNewquantity());

                getProduct().editStockQuantity(editStock);
            }
        }
        return "itemMovement";
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

}
