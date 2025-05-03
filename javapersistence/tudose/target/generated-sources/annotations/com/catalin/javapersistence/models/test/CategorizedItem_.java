package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.ZonedDateTime;

@StaticMetamodel(CategorizedItem.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class CategorizedItem_ {

	public static final String ITEM = "item";
	public static final String ADDED_BY = "addedBy";
	public static final String ADDED_ON = "addedOn";

	
	/**
	 * @see com.catalin.javapersistence.models.test.CategorizedItem#item
	 **/
	public static volatile SingularAttribute<CategorizedItem, Item> item;
	
	/**
	 * @see com.catalin.javapersistence.models.test.CategorizedItem#addedBy
	 **/
	public static volatile SingularAttribute<CategorizedItem, User> addedBy;
	
	/**
	 * @see com.catalin.javapersistence.models.test.CategorizedItem
	 **/
	public static volatile EmbeddableType<CategorizedItem> class_;
	
	/**
	 * @see com.catalin.javapersistence.models.test.CategorizedItem#addedOn
	 **/
	public static volatile SingularAttribute<CategorizedItem, ZonedDateTime> addedOn;

}

