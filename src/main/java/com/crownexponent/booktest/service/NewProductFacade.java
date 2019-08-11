/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.service;

import com.crownexponent.booktest.entity.NewProduct;
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
public class NewProductFacade extends AbstractFacade<NewProduct> {

    @PersistenceContext(unitName = "com.crownexponent_BookTest_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NewProductFacade() {
        super(NewProduct.class);
    }
    
  public NewProduct findByItemName(String itemName){
         NewProduct aaa = new NewProduct();
        Query query = em.createNamedQuery("NewProduct.findByItemName");
        query.setParameter("itemName", itemName );
        return (NewProduct) query.getSingleResult();
        
    }
    
}
