package com.epam.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> firstName;
	public static volatile SingularAttribute<User, String> lastName;
	public static volatile SetAttribute<User, Role> userRoles;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SetAttribute<User, Deal> deals;
	public static volatile SetAttribute<User, Bid> bids;
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, String> login;
	public static volatile SetAttribute<User, Item> items;

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String USER_ROLES = "userRoles";
	public static final String PASSWORD = "password";
	public static final String DEALS = "deals";
	public static final String BIDS = "bids";
	public static final String ID = "id";
	public static final String LOGIN = "login";
	public static final String ITEMS = "items";

}

