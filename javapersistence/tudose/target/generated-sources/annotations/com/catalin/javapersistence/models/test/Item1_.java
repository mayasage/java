package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Item1.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Item1_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String NAME = "name";

	
	/**
	 * @see com.catalin.javapersistence.models.test.Item1#name
	 **/
	public static volatile SingularAttribute<Item1, String> name;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Item1
	 **/
	public static volatile EntityType<Item1> class_;

}

