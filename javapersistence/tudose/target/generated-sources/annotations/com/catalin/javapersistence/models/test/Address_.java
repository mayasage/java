package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Address.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Address_ {

	public static final String ZIPCODE = "zipcode";
	public static final String CITY = "city";
	public static final String STREET = "street";
	public static final String DELIVERIES = "deliveries";

	
	/**
	 * @see com.catalin.javapersistence.models.test.Address#zipcode
	 **/
	public static volatile SingularAttribute<Address, String> zipcode;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Address#city
	 **/
	public static volatile SingularAttribute<Address, String> city;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Address#street
	 **/
	public static volatile SingularAttribute<Address, String> street;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Address
	 **/
	public static volatile EmbeddableType<Address> class_;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Address#deliveries
	 **/
	public static volatile SetAttribute<Address, Shipment> deliveries;

}

