/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ISSAH OJIVO
 */
@Entity
@Table(name = "stock_adjustment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StockAdjustment.findAll", query = "SELECT s FROM StockAdjustment s"),
    @NamedQuery(name = "StockAdjustment.findById", query = "SELECT s FROM StockAdjustment s WHERE s.id = :id"),
     @NamedQuery(name = "StockAdjustment.findByItemname", query = "SELECT s FROM StockAdjustment s WHERE s.itemname = :itemname"),
    @NamedQuery(name = "StockAdjustment.findByReason", query = "SELECT s FROM StockAdjustment s WHERE s.reason = :reason"),
    @NamedQuery(name = "StockAdjustment.findByAdjustmenttype", query = "SELECT s FROM StockAdjustment s WHERE s.adjustmenttype = :adjustmenttype"),
    @NamedQuery(name = "StockAdjustment.findByOldquantity", query = "SELECT s FROM StockAdjustment s WHERE s.oldquantity = :oldquantity"),
    @NamedQuery(name = "StockAdjustment.findByNewquantity", query = "SELECT s FROM StockAdjustment s WHERE s.newquantity = :newquantity"),
    @NamedQuery(name = "StockAdjustment.findByAdjustedby", query = "SELECT s FROM StockAdjustment s WHERE s.adjustedby = :adjustedby")})
public class StockAdjustment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "itemname", unique = true)
    private String itemname;
    
    @Size(max = 200)
    @Column(name = "date")
    private String date;
    @Size(max = 500)
    @Column(name = "reason")
    private String reason;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "adjustmenttype")
    private String adjustmenttype;
    @Basic(optional = false)
    @NotNull
    @Column(name = "oldquantity")
    private int oldquantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "newquantity")
    private Integer newquantity;
    @Size(max = 200)
    @Column(name = "adjustedby")
    private String adjustedby;

    public StockAdjustment() {
    }

    public StockAdjustment(Integer id) {
        this.id = id;
    }

    public StockAdjustment(Integer id, String adjustmenttype, int oldquantity, int newquantity) {
        this.id = id;
        this.adjustmenttype = adjustmenttype;
        this.oldquantity = oldquantity;
        this.newquantity = newquantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAdjustmenttype() {
        return adjustmenttype;
    }

    public void setAdjustmenttype(String adjustmenttype) {
        this.adjustmenttype = adjustmenttype;
    }

    public int getOldquantity() {
        return oldquantity;
    }

    public void setOldquantity(int oldquantity) {
        this.oldquantity = oldquantity;
    }

    public Integer getNewquantity() {
        return newquantity;
    }

    public void setNewquantity(Integer newquantity) {
        this.newquantity = newquantity;
    }

    public String getAdjustedby() {
        return adjustedby;
    }

    public void setAdjustedby(String adjustedby) {
        this.adjustedby = adjustedby;
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
        if (!(object instanceof StockAdjustment)) {
            return false;
        }
        StockAdjustment other = (StockAdjustment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.muellerbruehl.books.entities.StockAdjustment[ id=" + id + " ]";
    }

    /**
     * @return the itemname
     */
    public String getItemname() {
        return itemname;
    }

    /**
     * @param itemname the itemname to set
     */
    public void setItemname(String itemname) {
        this.itemname = itemname;
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
    
}
