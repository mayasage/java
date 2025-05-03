package com.catalin.javapersistence.models.base;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.MappedSuperclassType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.ZonedDateTime;

@StaticMetamodel(AbstractEntityBase.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AbstractEntityBase_ {

	public static final String CREATED_AT = "createdAt";
	public static final String ID = "id";
	public static final String VERSION = "version";
	public static final String UPDATED_AT = "updatedAt";

	
	/**
	 * @see com.catalin.javapersistence.models.base.AbstractEntityBase#createdAt
	 **/
	public static volatile SingularAttribute<AbstractEntityBase, ZonedDateTime> createdAt;
	
	/**
	 * @see com.catalin.javapersistence.models.base.AbstractEntityBase#id
	 **/
	public static volatile SingularAttribute<AbstractEntityBase, Long> id;
	
	/**
	 * @see com.catalin.javapersistence.models.base.AbstractEntityBase
	 **/
	public static volatile MappedSuperclassType<AbstractEntityBase> class_;
	
	/**
	 * @see com.catalin.javapersistence.models.base.AbstractEntityBase#version
	 **/
	public static volatile SingularAttribute<AbstractEntityBase, Short> version;
	
	/**
	 * @see com.catalin.javapersistence.models.base.AbstractEntityBase#updatedAt
	 **/
	public static volatile SingularAttribute<AbstractEntityBase, ZonedDateTime> updatedAt;

}

