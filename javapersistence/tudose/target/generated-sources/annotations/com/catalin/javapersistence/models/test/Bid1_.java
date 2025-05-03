package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(Bid1.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Bid1_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String AMOUNT = "amount";
	public static final String ITEM = "item";

	
	/**
	 * @see com.catalin.javapersistence.models.test.Bid1#amount
	 **/
	public static volatile SingularAttribute<Bid1, BigDecimal> amount;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Bid1#item
	 **/
	public static volatile SingularAttribute<Bid1, Item1> item;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Bid1
	 **/
	public static volatile EntityType<Bid1> class_;

}

