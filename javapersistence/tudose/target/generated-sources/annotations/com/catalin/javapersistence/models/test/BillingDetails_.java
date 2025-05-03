package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(BillingDetails.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class BillingDetails_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String OWNER = "owner";
	public static final String USER = "user";

	
	/**
	 * @see com.catalin.javapersistence.models.test.BillingDetails#owner
	 **/
	public static volatile SingularAttribute<BillingDetails, String> owner;
	
	/**
	 * @see com.catalin.javapersistence.models.test.BillingDetails
	 **/
	public static volatile EntityType<BillingDetails> class_;
	
	/**
	 * @see com.catalin.javapersistence.models.test.BillingDetails#user
	 **/
	public static volatile SingularAttribute<BillingDetails, User> user;

}

