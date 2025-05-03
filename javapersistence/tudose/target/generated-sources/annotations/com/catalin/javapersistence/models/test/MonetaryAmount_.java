package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(MonetaryAmount.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class MonetaryAmount_ {

	public static final String AMOUNT = "amount";
	public static final String CURRENCY = "currency";

	
	/**
	 * @see com.catalin.javapersistence.models.test.MonetaryAmount#value
	 **/
	public static volatile SingularAttribute<MonetaryAmount, BigDecimal> amount;
	
	/**
	 * @see com.catalin.javapersistence.models.test.MonetaryAmount#currency
	 **/
	public static volatile SingularAttribute<MonetaryAmount, String> currency;
	
	/**
	 * @see com.catalin.javapersistence.models.test.MonetaryAmount
	 **/
	public static volatile EmbeddableType<MonetaryAmount> class_;

}

