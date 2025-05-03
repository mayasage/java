package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Image.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Image_ {

	public static final String WIDTH = "width";
	public static final String TITLE = "title";
	public static final String HEIGHT = "height";

	
	/**
	 * @see com.catalin.javapersistence.models.test.Image#width
	 **/
	public static volatile SingularAttribute<Image, Integer> width;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Image#title
	 **/
	public static volatile SingularAttribute<Image, String> title;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Image
	 **/
	public static volatile EmbeddableType<Image> class_;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Image#height
	 **/
	public static volatile SingularAttribute<Image, Integer> height;

}

