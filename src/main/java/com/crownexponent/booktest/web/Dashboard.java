/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.web;

import com.crownexponent.booktest.entity.NewProduct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
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
    @Inject 
    private AdjustStock stock;
    private List<NewProduct> products;
    public Dashboard() {
    }

    /**
     * @return the stock
     */
    public AdjustStock getStock() {
        return stock;
    }

    /**
     * @return the products
     */
    public List<NewProduct> getProducts() {
        return products;
    }
    
}
