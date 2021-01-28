package com.epam.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Item.class)
public abstract class Item_ {

	public static volatile SetAttribute<Item, Deal> deals;
	public static volatile SingularAttribute<Item, String> name;
	public static volatile SingularAttribute<Item, Integer> id;
	public static volatile SingularAttribute<Item, String> descript;
	public static volatile SingularAttribute<Item, User> user;

	public static final String DEALS = "deals";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String DESCRIPT = "descript";
	public static final String USER = "user";

}

