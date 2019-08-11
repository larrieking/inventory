/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.service;

import com.crownexponent.booktest.entity.StockAdjustment;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ISSAH OJIVO
 */
@Stateless
public class StockAdjustmentFacade extends AbstractFacade<StockAdjustment> {

    @PersistenceContext(unitName = "com.crownexponent_BookTest_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StockAdjustmentFacade() {
        super(StockAdjustment.class);
    }
    
    
     public List<StockAdjustment> findByName(String name){
        //StockAdjustment stock = new StockAdjustment();
        Query query = em.createNamedQuery("StockAdjustment.findByItemname");
        query.setParameter("itemname", name);
       return query.getResultList();
        
    }
}
