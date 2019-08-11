/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.web;

import com.crownexponent.booktest.entity.Account;
import com.crownexponent.booktest.entity.NewProduct;
import com.crownexponent.booktest.entity.StockAdjustment;
import com.crownexponent.booktest.service.AccountFacade;
import com.crownexponent.booktest.service.Mail;
import com.crownexponent.booktest.service.NewProductFacade;
import com.crownexponent.booktest.service.StockAdjustmentFacade;
import com.crownexponent.booktest.util.Message;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.LocalDate;
import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author ISSAH OJIVO
 */
@Named(value = "productController")
@RequestScoped
public class ProductController {

    /**
     * Creates a new instance of ProductController
     */
    private String no, name, category, createdBy, date, uses, uom;
    private Integer understock, overstock, optimal;

    public Integer getUnderstock() {
        return understock;
    }

    public void setUnderstock(Integer understock) {
        this.understock = understock;
    }

    public Integer getOverstock() {
        return overstock;
    }

    public void setOverstock(Integer overstock) {
        this.overstock = overstock;
    }

    public Integer getOptimal() {
        return optimal;
    }

    public void setOptimal(Integer optimal) {
        this.optimal = optimal;
    }
    private Integer openinStock;

    @EJB
    private NewProductFacade facade;
    @EJB
    private StockAdjustmentFacade stockFacade;
    @Inject
    private ValidateUser authenticatedUser;
    @EJB
    private AccountFacade accountFacade;
    @EJB
    private Mail mailSession;

    public ProductController() {
    }

    public NewProductFacade getFacade() {
        return facade;
    }

    public String createProduct() {
        try {
            NewProduct product = new NewProduct();
            product.setItemName(getName());
            product.setItemClass(getCategory());
            product.setDate(getDate());
            product.setOpeningStock(getOpeninStock());
            product.setUom(getUom());
            product.setUses(getUses());
            product.setItemNo(getNo());
            product.setCreatedBy(getCreatedBy());
            product.setOptimal(getOptimal());
            product.setUnderstock(getUnderstock());
            product.setOverstock(getOverstock());
            getFacade().create(product);
            StockAdjustment stock = new StockAdjustment();
            stock.setAdjustedby(getCreatedBy());
            stock.setAdjustmenttype("Incoming");
            stock.setDate(LocalDate.now().toString());
            stock.setItemname(getName());
            stock.setReason("Opening Stock");
            stock.setNewquantity(getOpeninStock());

            getStockFacade().create(stock);

            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", "Item " + getName().toUpperCase() + " Created Successfully!!!");
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
                    + "<h2></h2>\n"
                    + "\n"
                    + "<p> Item <strong>" + getName().toUpperCase() + "</strong> added to inventory by <strong>" + getAuthenticatedUser().getLoggedUser().getFirstname().toUpperCase() + ".</strong><br /></p><h3>Pls see details below:</h3>"
                    + "<br />"
                    + "<table id=\"t01\">\n"
                    + "  <tr>\n"
                    + "    <th>ITEM NAME</th>\n"
                    + "    <th>CATEGORY</th> \n"
                    + "    <th>OPENING STOCK</th>\n"
                    + "    <th>UOM</th>\n"
                    + "    <th>USES</th>\n"
                    + "  </tr>\n"
                    + "  <tr>\n"
                    + "    <td>" + getName().toUpperCase() + "</td>\n"
                    + "    <td>" + getCategory().toUpperCase() + "</td>\n"
                    + "    <td>" + getOpeninStock() + "</td>\n"
                    + "    <td>" + getUom().toUpperCase() + "</td>\n"
                    + "    <td>" + getUses().toUpperCase() + "</td>\n"
                    + "  </tr>\n"
                    + "</table>\n<br />"
                    + "<p>Regards,<br />INVENTORY ADMIN</p>"
                    + "\n"
                    + "</body>\n"
                    + "</html>";

            List<String> to = getMailSession().getAccountGroup("admin");
            //System.out.print("Address: "+to.get(0));
            getMailSession().sendMail(to, "NEW ITEM ADDED TO INVENTORY", message);

        } catch (Exception e) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("failure", e.getMessage());
        }
        return "addProduct?faces-redirect=true";

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
        return LocalDateTime.now().toString();
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
    public Integer getOpeninStock() {
        return openinStock;
    }

    /**
     * @param openinStock the openinStock to set
     */
    public void setOpeninStock(Integer openinStock) {
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

    public List<NewProduct> getAllStock() {
        return getFacade().findAll();
    }

    public String deleteProduct(NewProduct product) {
        getFacade().remove(product);
        return "currentStock?faces-redirect=true";
    }

    public void editStockQuantity(NewProduct product) {
        getFacade().edit(product);

    }

    /**
     * @return the stockFacade
     */
    public StockAdjustmentFacade getStockFacade() {
        return stockFacade;
    }

    /**
     * @return the accountFacade
     */
    public AccountFacade getAccountFacade() {
        return accountFacade;
    }

    /**
     * @return the mailSession
     */
    public Mail getMailSession() {
        return mailSession;
    }

}
