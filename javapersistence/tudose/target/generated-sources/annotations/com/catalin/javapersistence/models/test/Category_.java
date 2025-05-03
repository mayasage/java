package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.MapAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Category.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Category_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String NAME = "name";
	public static final String ITEM_ADDED_BY = "itemAddedBy";

	
	/**
	 * @see com.catalin.javapersistence.models.test.Category#name
	 **/
	public static volatile SingularAttribute<Category, String> name;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Category#itemAddedBy
	 **/
	public static volatile MapAttribute<Category, Item, User> itemAddedBy;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Category
	 **/
	public static volatile EntityType<Category> class_;

}

