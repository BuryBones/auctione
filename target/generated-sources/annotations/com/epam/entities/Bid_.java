package com.epam.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Bid.class)
public abstract class Bid_ {

	public static volatile SingularAttribute<Bid, BigDecimal> offer;
	public static volatile SingularAttribute<Bid, Deal> deal;
	public static volatile SingularAttribute<Bid, Timestamp> dateAndTime;
	public static volatile SingularAttribute<Bid, Integer> id;
	public static volatile SingularAttribute<Bid, User> user;

	public static final String OFFER = "offer";
	public static final String DEAL = "deal";
	public static final String DATE_AND_TIME = "dateAndTime";
	public static final String ID = "id";
	public static final String USER = "user";

}

