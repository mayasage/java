package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class User_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String SHIPPING_ADDRESS = "shippingAddress";
	public static final String RANKING = "ranking";
	public static final String BOUGHT_ITEMS = "boughtItems";
	public static final String USERNAME = "username";

	
	/**
	 * @see com.catalin.javapersistence.models.test.User#shippingAddress
	 **/
	public static volatile SingularAttribute<User, Address> shippingAddress;
	
	/**
	 * @see com.catalin.javapersistence.models.test.User#ranking
	 **/
	public static volatile SingularAttribute<User, Integer> ranking;
	
	/**
	 * @see com.catalin.javapersistence.models.test.User#boughtItems
	 **/
	public static volatile SetAttribute<User, Item> boughtItems;
	
	/**
	 * @see com.catalin.javapersistence.models.test.User
	 **/
	public static volatile EntityType<User> class_;
	
	/**
	 * @see com.catalin.javapersistence.models.test.User#username
	 **/
	public static volatile SingularAttribute<User, String> username;

}

