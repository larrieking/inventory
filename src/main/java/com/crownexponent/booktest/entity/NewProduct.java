/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ISSAH OJIVO
 */
@Entity
@Table(name = "new_product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NewProduct.findAll", query = "SELECT n FROM NewProduct n"),
    @NamedQuery(name = "NewProduct.findById", query = "SELECT n FROM NewProduct n WHERE n.id = :id"),
    @NamedQuery(name = "NewProduct.findByItemNo", query = "SELECT n FROM NewProduct n WHERE n.itemNo = :itemNo"),
    @NamedQuery(name = "NewProduct.findByItemName", query = "SELECT n FROM NewProduct n WHERE n.itemName = :itemName"),
    @NamedQuery(name = "NewProduct.findByItemClass", query = "SELECT n FROM NewProduct n WHERE n.itemClass = :itemClass"),
    @NamedQuery(name = "NewProduct.findByDate", query = "SELECT n FROM NewProduct n WHERE n.date = :date"),
    @NamedQuery(name = "NewProduct.findByCreatedBy", query = "SELECT n FROM NewProduct n WHERE n.createdBy = :createdBy"),
    @NamedQuery(name = "NewProduct.findByUses", query = "SELECT n FROM NewProduct n WHERE n.uses = :uses"),
    @NamedQuery(name = "NewProduct.findByUom", query = "SELECT n FROM NewProduct n WHERE n.uom = :uom"),
    @NamedQuery(name = "NewProduct.findByOpeningStock", query = "SELECT n FROM NewProduct n WHERE n.openingStock = :openingStock"),
  @NamedQuery(name = "NewProduct.findByUnderStock", query = "SELECT n FROM NewProduct n WHERE n.understock = :understock"),
  @NamedQuery(name = "NewProduct.findByOverStock", query = "SELECT n FROM NewProduct n WHERE n.overstock = :overstock"),
  @NamedQuery(name = "NewProduct.findByOptimal", query = "SELECT n FROM NewProduct n WHERE n.optimal = :optimal"),})
public class NewProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
   
   
    @Size(min = 1, max = 100)
    @Column(name = "item_no")
    private String itemNo;
    @Basic(optional = true)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "item_name", unique = true)
    private String itemName;
    @Size(max = 100)
    @Column(name = "item_class")
    private String itemClass;
    @Size(max = 100)
    @Column(name = "date")
    private String date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "created_by")
    private String createdBy;
    @Size(max = 200)
    @Column(name = "uses")
    private String uses;
    @Basic(optional = false)
    @NotNull
    @Column(name = "uom")
    private String uom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "opening_stock")
    private int openingStock;
    
    @Basic(optional = false)
    @Column(name = "understock")
    private int understock;
    
    @Basic(optional = false)
    @Column(name = "overstock")
    private int overstock;
    
    
    @Basic(optional = false)
    @Column(name = "optimal")
    private int optimal;

    public int getUnderstock() {
        return understock;
    }

    public void setUnderstock(int understock) {
        this.understock = understock;
    }

    public int getOverstock() {
        return overstock;
    }

    public void setOverstock(int overstock) {
        this.overstock = overstock;
    }

    public int getOptimal() {
        return optimal;
    }

    public void setOptimal(int optimal) {
        this.optimal = optimal;
    }

    public NewProduct() {
    }

    public NewProduct(Integer id) {
        this.id = id;
    }

    public NewProduct(String itemNo, String itemName, String createdBy, String uom, int openingStock) {
        
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.createdBy = createdBy;
        this.uom = uom;
        this.openingStock = openingStock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUses() {
        return uses;
    }

    public void setUses(String uses) {
        this.uses = uses;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public int getOpeningStock() {
        return openingStock;
    }

    public void setOpeningStock(int openingStock) {
        this.openingStock = openingStock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NewProduct)) {
            return false;
        }
        NewProduct other = (NewProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crownexponent.booktest.entity.NewProduct[ id=" + id + " ]";
    }
    
}
