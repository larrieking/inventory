/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author golan
 */
@Entity
@Table(name = "procurement_memo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcurementMemo.findAll", query = "SELECT p FROM ProcurementMemo p")
    , @NamedQuery(name = "ProcurementMemo.findBySerialNo", query = "SELECT p FROM ProcurementMemo p WHERE p.serialNo = :serialNo")
    , @NamedQuery(name = "ProcurementMemo.findByItem", query = "SELECT p FROM ProcurementMemo p WHERE p.item = :item")
    , @NamedQuery(name = "ProcurementMemo.findByStockAtHand", query = "SELECT p FROM ProcurementMemo p WHERE p.stockAtHand = :stockAtHand")
    , @NamedQuery(name = "ProcurementMemo.findByUnderStockLevel", query = "SELECT p FROM ProcurementMemo p WHERE p.underStockLevel = :underStockLevel")
    , @NamedQuery(name = "ProcurementMemo.findByIdealQty", query = "SELECT p FROM ProcurementMemo p WHERE p.idealQty = :idealQty")
    , @NamedQuery(name = "ProcurementMemo.findByThisOder", query = "SELECT p FROM ProcurementMemo p WHERE p.thisOder = :thisOder")
    , @NamedQuery(name = "ProcurementMemo.findByVendor", query = "SELECT p FROM ProcurementMemo p WHERE p.vendor = :vendor")
    , @NamedQuery(name = "ProcurementMemo.findByPrice", query = "SELECT p FROM ProcurementMemo p WHERE p.price = :price")})
public class ProcurementMemo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "serial_no")
    private Integer serialNo;
    @Basic(optional = false)
    @Column(name = "item")
    private String item;
    @Basic(optional = false)
    @Column(name = "stock_at_hand")
    private Integer stockAtHand;
    @Basic(optional = false)
    @Column(name = "under_stock_level")
    private Integer underStockLevel;
    @Column(name = "ideal_qty")
    private Integer idealQty;
    @Basic(optional = false)
    @Column(name = "this_oder")
    private Integer thisOder;
    @Basic(optional = false)
    @Column(name = "vendor")
    private String vendor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "price")
    private BigDecimal price;

    public ProcurementMemo() {
    }

    public ProcurementMemo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public ProcurementMemo(Integer serialNo, String item, Integer stockAtHand, Integer underStockLevel, Integer thisOder, String vendor, BigDecimal price) {
        this.serialNo = serialNo;
        this.item = item;
        this.stockAtHand = stockAtHand;
        this.underStockLevel = underStockLevel;
        this.thisOder = thisOder;
        this.vendor = vendor;
        this.price = price;
    }

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getStockAtHand() {
        return stockAtHand;
    }

    public void setStockAtHand(Integer stockAtHand) {
        this.stockAtHand = stockAtHand;
    }

    public Integer getUnderStockLevel() {
        return underStockLevel;
    }

    public void setUnderStockLevel(Integer underStockLevel) {
        this.underStockLevel = underStockLevel;
    }

    public Integer getIdealQty() {
        return idealQty;
    }

    public void setIdealQty(Integer idealQty) {
        this.idealQty = idealQty;
    }

    public Integer getThisOder() {
        return thisOder;
    }

    public void setThisOder(Integer thisOder) {
        this.thisOder = thisOder;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serialNo != null ? serialNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcurementMemo)) {
            return false;
        }
        ProcurementMemo other = (ProcurementMemo) object;
        if ((this.serialNo == null && other.serialNo != null) || (this.serialNo != null && !this.serialNo.equals(other.serialNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crownexponent.page.ProcurementMemo[ serialNo=" + serialNo + " ]";
    }
    
}
