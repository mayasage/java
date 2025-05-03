package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(Bid.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Bid_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String ITEM = "item";
	public static final String AMOUNT = "amount";
	public static final String GRAPH_GRAPH__BID_ITEM_BUYER_BIDS = "graph.BidItemBuyerBids";
	public static final String BIDDER = "bidder";

	
	/**
	 * @see com.catalin.javapersistence.models.test.Bid#item
	 **/
	public static volatile SingularAttribute<Bid, Item> item;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Bid#amount
	 **/
	public static volatile SingularAttribute<Bid, BigDecimal> amount;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Bid#bidder
	 **/
	public static volatile SingularAttribute<Bid, User> bidder;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Bid
	 **/
	public static volatile EntityType<Bid> class_;

}

