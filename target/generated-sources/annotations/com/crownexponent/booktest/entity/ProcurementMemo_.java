package com.crownexponent.booktest.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-27T11:21:00")
@StaticMetamodel(ProcurementMemo.class)
public class ProcurementMemo_ { 

    public static volatile SingularAttribute<ProcurementMemo, String> item;
    public static volatile SingularAttribute<ProcurementMemo, Integer> underStockLevel;
    public static volatile SingularAttribute<ProcurementMemo, Integer> stockAtHand;
    public static volatile SingularAttribute<ProcurementMemo, String> vendor;
    public static volatile SingularAttribute<ProcurementMemo, BigDecimal> price;
    public static volatile SingularAttribute<ProcurementMemo, Integer> thisOder;
    public static volatile SingularAttribute<ProcurementMemo, Integer> idealQty;
    public static volatile SingularAttribute<ProcurementMemo, Integer> serialNo;

}