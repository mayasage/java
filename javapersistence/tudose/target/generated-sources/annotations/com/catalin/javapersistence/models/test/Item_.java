package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.MapAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@StaticMetamodel(Item.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Item_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String SELLER = "seller";
	public static final String IMAGES = "images";
	public static final String CREATION_TIME = "creationTime";
	public static final String INITIAL_PRICE = "initialPrice";
	public static final String WEIGHT = "weight";
	public static final String BUY_NOW_PRICE = "buyNowPrice";
	public static final String BUYER = "buyer";
	public static final String METRIC_WEIGHT = "metricWeight";
	public static final String ITEM_NAME = "itemName";
	public static final String ITEM_SHORT_DESCRIPTION = "itemShortDescription";
	public static final String BIDS = "bids";
	public static final String DIMENSIONS = "dimensions";

	
	/**
	 * @see com.catalin.javapersistence.models.test.Item#seller
	 **/
	public static volatile SingularAttribute<Item, User> seller;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Item#images
	 **/
	public static volatile MapAttribute<Item, Filename, Image> images;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Item#creationTime
	 **/
	public static volatile SingularAttribute<Item, ZonedDateTime> creationTime;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Item#initialPrice
	 **/
	public static volatile SingularAttribute<Item, BigDecimal> initialPrice;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Item#weight
	 **/
	public static volatile SingularAttribute<Item, Weight> weight;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Item#buyNowPrice
	 **/
	public static volatile SingularAttribute<Item, MonetaryAmount> buyNowPrice;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Item#buyer
	 **/
	public static volatile SingularAttribute<Item, User> buyer;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Item#metricWeight
	 **/
	public static volatile SingularAttribute<Item, BigDecimal> metricWeight;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Item#itemName
	 **/
	public static volatile SingularAttribute<Item, String> itemName;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Item#itemShortDescription
	 **/
	public static volatile SingularAttribute<Item, String> itemShortDescription;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Item#bids
	 **/
	public static volatile MapAttribute<Item, Long, Bid> bids;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Item
	 **/
	public static volatile EntityType<Item> class_;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Item#dimensions
	 **/
	public static volatile SingularAttribute<Item, Dimensions> dimensions;

}

