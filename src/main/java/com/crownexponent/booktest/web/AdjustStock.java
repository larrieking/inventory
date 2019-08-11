/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.web;

import com.crownexponent.booktest.entity.NewProduct;
import com.crownexponent.booktest.entity.StockAdjustment;
import com.crownexponent.booktest.service.Mail;
import com.crownexponent.booktest.service.NewProductFacade;
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
    @EJB
    private NewProductFacade productFacade;
    //@Inject
    // private ProductController product;
    private StockAdjustment stockAdjustment;
    @Inject
    private ValidateUser authenticatedUser;
    @EJB
    private Mail mailSession;

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
    // public ProductController getProduct() {
    //    return product;
    // }
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
            getStockAdjustment().setAdjustedby(authenticatedUser.getLoggedUser().getEmail());
            getStockAdjustment().setDate(ldt.toString());
            if (typeOfAdjustment.equalsIgnoreCase("outgoing")) {
                getStockAdjustment().setNewquantity(-1 * getStockAdjustment().getNewquantity());
            }

            getFacade().create(stockAdjustment);

            for (NewProduct editStock : productList) {
                if (editStock.getItemName().equalsIgnoreCase(getStockAdjustment().getItemname())) {

                    editStock.setOpeningStock(editStock.getOpeningStock() + getStockAdjustment().getNewquantity());

                    getProductFacade().edit(editStock);
                }
            }
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", "SUCCESS");
            sendNotification();
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
        productList = getProductFacade().findAll();
        return productList;
    }

    public List<StockAdjustment> getMovementHistory() {
        movementHistory = getFacade().findAll();
        return movementHistory;
    }

    public List<StockAdjustment> viewSingleItem() {
        FacesContext context = FacesContext.getCurrentInstance();
        String param = context.getExternalContext().getRequestParameterMap().get("id");
        //System.out.println(param);
        return getFacade().findByName(param);
    }

    public void sendNotification() {
        NewProduct product1 = getProductFacade().findByItemName(getStockAdjustment().getItemname());
        int oldQuantity = product1.getOpeningStock() - getStockAdjustment().getNewquantity();
        String message = "<html>\n"
                + "<head>\n"
                + "<style>\n"
                + "table {\n"
                + "  width:100%;\n"
                + "}\n"
                + "p {\n"
                + "  font-family: \"Times New Roman\", Times, Serif;\n"
                + "}\n"
                + "table, th, td {\n"
                + "  border: 1px solid black;\n"
                + "  border-collapse: collapse;\n"
                + "}\n"
                + "th, td {\n"
                + "  padding: 15px;\n"
                + "  text-align: left;\n"
                + "}\n"
                + "table#t01 tr:nth-child(even) {\n"
                + "  background-color: #eee;\n"
                + "}\n"
                + "table#t01 tr:nth-child(odd) {\n"
                + " background-color: #fff;\n"
                + "}\n"
                + "table#t01 th {\n"
                + "  background-color: black;\n"
                + "  color: white;\n"
                + "}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "\n"
               
               
                + "<p>This is a transaction notification on item <strong>" + getStockAdjustment().getItemname().toUpperCase() + "</strong> carried out by <strong>" + getAuthenticatedUser().getLoggedUser().getFirstname().toUpperCase() + ".</strong><br /></p><h3>Pls see details below:</h3>"
                + "<br />"
                + "<table id=\"t01\">\n"
                + "  <tr>\n"
                + "    <th>ITEM </th>\n"
                + "    <th>ADJ. TYPE</th> \n"
                + "    <th>REASON</th>\n"
                + "    <th>OLD QTY</th>\n"
                + "    <th>QTY ADJUSTED</th>\n"
                + "    <th>BALANCE</th>\n"
                + "  </tr>\n"
                + "  <tr>\n"
                + "    <td>" + getStockAdjustment().getItemname().toUpperCase() + "</td>\n"
                + "    <td>" + getStockAdjustment().getAdjustmenttype().toUpperCase() + "</td>\n"
                + "    <td>" + getStockAdjustment().getReason().toUpperCase() + "</td>\n"
                + "    <td>" + oldQuantity + "</td>\n"
                + "    <td>" + getStockAdjustment().getNewquantity() + "</td>\n"
                + "    <td>" + product1.getOpeningStock() + "</td>\n"
                + "  </tr>\n"
                + "</table>\n<br />"
                + "<p>Regards,<br />INVENTORY ADMIN</p>"
                + "\n"
                + "</body>\n"
                + "</html>";

        List<String> to = getMailSession().getAccountGroup("admin");
        //System.out.print("Address: "+to.get(0));
        getMailSession().sendMail(to, "Inventory Alert on Item " + getStockAdjustment().getItemname().toUpperCase(), message);
    }

    /**
     * @return the authenticatedUser
     */
    public ValidateUser getAuthenticatedUser() {
        return authenticatedUser;
    }

    /**
     * @return the productFacade
     */
    public NewProductFacade getProductFacade() {
        return productFacade;
    }

    /**
     * @return the mailSession
     */
    public Mail getMailSession() {
        return mailSession;
    }
}
