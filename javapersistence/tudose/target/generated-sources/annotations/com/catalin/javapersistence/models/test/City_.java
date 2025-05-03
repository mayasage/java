package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(City.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class City_ {

	public static final String COUNTRY = "country";
	public static final String NAME = "name";

	
	/**
	 * @see com.catalin.javapersistence.models.test.City#country
	 **/
	public static volatile SingularAttribute<City, String> country;
	
	/**
	 * @see com.catalin.javapersistence.models.test.City#name
	 **/
	public static volatile SingularAttribute<City, String> name;
	
	/**
	 * @see com.catalin.javapersistence.models.test.City
	 **/
	public static volatile EmbeddableType<City> class_;

}

