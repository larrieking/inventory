package com.crownexponent.booktest.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-27T11:21:00")
@StaticMetamodel(StockAdjustment.class)
public class StockAdjustment_ { 

    public static volatile SingularAttribute<StockAdjustment, String> date;
    public static volatile SingularAttribute<StockAdjustment, String> reason;
    public static volatile SingularAttribute<StockAdjustment, String> adjustedby;
    public static volatile SingularAttribute<StockAdjustment, String> itemname;
    public static volatile SingularAttribute<StockAdjustment, String> adjustmenttype;
    public static volatile SingularAttribute<StockAdjustment, Integer> oldquantity;
    public static volatile SingularAttribute<StockAdjustment, Integer> id;
    public static volatile SingularAttribute<StockAdjustment, Integer> newquantity;

}