package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Filename.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Filename_ {

	public static final String NAME = "name";

	
	/**
	 * @see com.catalin.javapersistence.models.test.Filename#name
	 **/
	public static volatile SingularAttribute<Filename, String> name;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Filename
	 **/
	public static volatile EmbeddableType<Filename> class_;

}

