/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.service;

import com.crownexponent.booktest.entity.PasswordReset;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ISSAH OJIVO
 */
@Stateless
public class PasswordResetFacade extends AbstractFacade<PasswordReset> {

    @PersistenceContext(unitName = "com.crownexponent_BookTest_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PasswordResetFacade() {
        super(PasswordReset.class);
    }
    
    
    public PasswordReset findByToken(String token){
         PasswordReset aaa = new PasswordReset();
        Query query = em.createNamedQuery("PasswordReset.findByToken");
        query.setParameter("token", token );
        return (PasswordReset) query.getSingleResult();
        
    }
}
