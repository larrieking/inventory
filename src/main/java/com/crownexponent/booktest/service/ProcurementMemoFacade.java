/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.service;

import com.crownexponent.booktest.entity.AbstractFacade;
import com.crownexponent.booktest.entity.ProcurementMemo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author golan
 */
@Stateless
public class ProcurementMemoFacade extends AbstractFacade<ProcurementMemo> {

    @PersistenceContext(unitName = "com.crownexponent_BookTest_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcurementMemoFacade() {
        super(ProcurementMemo.class);
    }
    
}
