package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CreditCard.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class CreditCard_ extends com.catalin.javapersistence.models.test.BillingDetails_ {

	public static final String EXP_MONTH = "expMonth";
	public static final String CC_NUMBER = "ccNumber";
	public static final String EXP_YEAR = "expYear";

	
	/**
	 * @see com.catalin.javapersistence.models.test.CreditCard#expMonth
	 **/
	public static volatile SingularAttribute<CreditCard, String> expMonth;
	
	/**
	 * @see com.catalin.javapersistence.models.test.CreditCard#ccNumber
	 **/
	public static volatile SingularAttribute<CreditCard, String> ccNumber;
	
	/**
	 * @see com.catalin.javapersistence.models.test.CreditCard#expYear
	 **/
	public static volatile SingularAttribute<CreditCard, String> expYear;
	
	/**
	 * @see com.catalin.javapersistence.models.test.CreditCard
	 **/
	public static volatile EntityType<CreditCard> class_;

}

