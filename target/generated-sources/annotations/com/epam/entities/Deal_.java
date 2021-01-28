package com.epam.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Deal.class)
public abstract class Deal_ {

	public static volatile SingularAttribute<Deal, Item> item;
	public static volatile SingularAttribute<Deal, Timestamp> closeTime;
	public static volatile SetAttribute<Deal, Bid> bids;
	public static volatile SingularAttribute<Deal, BigDecimal> initPrice;
	public static volatile SingularAttribute<Deal, Integer> id;
	public static volatile SingularAttribute<Deal, Timestamp> openTime;
	public static volatile SingularAttribute<Deal, User> user;
	public static volatile SingularAttribute<Deal, Boolean> status;

	public static final String ITEM = "item";
	public static final String CLOSE_TIME = "closeTime";
	public static final String BIDS = "bids";
	public static final String INIT_PRICE = "initPrice";
	public static final String ID = "id";
	public static final String OPEN_TIME = "openTime";
	public static final String USER = "user";
	public static final String STATUS = "status";

}

