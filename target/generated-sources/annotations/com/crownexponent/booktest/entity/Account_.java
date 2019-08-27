package com.crownexponent.booktest.entity;

import com.crownexponent.booktest.entity.Role;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-27T11:09:14")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, String> password;
    public static volatile SingularAttribute<Account, String> firstname;
    public static volatile SingularAttribute<Account, Role> role;
    public static volatile SingularAttribute<Account, String> email;
    public static volatile SingularAttribute<Account, String> lastname;

}