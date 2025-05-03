package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.MappedSuperclassType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Measurement.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Measurement_ {

	public static final String SYMBOL = "symbol";
	public static final String NAME = "name";

	
	/**
	 * @see com.catalin.javapersistence.models.test.Measurement#symbol
	 **/
	public static volatile SingularAttribute<Measurement, String> symbol;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Measurement#name
	 **/
	public static volatile SingularAttribute<Measurement, String> name;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Measurement
	 **/
	public static volatile MappedSuperclassType<Measurement> class_;

}

