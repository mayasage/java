package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(I1.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class I1_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String NAME = "name";
	public static final String BUY_NOW_PRICE = "buyNowPrice";
	public static final String CATEGORY = "category";

	
	/**
	 * @see com.catalin.javapersistence.models.test.I1#name
	 **/
	public static volatile SingularAttribute<I1, String> name;
	
	/**
	 * @see com.catalin.javapersistence.models.test.I1#buyNowPrice
	 **/
	public static volatile SingularAttribute<I1, BigDecimal> buyNowPrice;
	
	/**
	 * @see com.catalin.javapersistence.models.test.I1#category
	 **/
	public static volatile SingularAttribute<I1, C1> category;
	
	/**
	 * @see com.catalin.javapersistence.models.test.I1
	 **/
	public static volatile EntityType<I1> class_;

}

