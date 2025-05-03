package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(Dimensions.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Dimensions_ extends com.catalin.javapersistence.models.test.Measurement_ {

	public static final String DEPTH = "depth";
	public static final String WIDTH = "width";
	public static final String HEIGHT = "height";

	
	/**
	 * @see com.catalin.javapersistence.models.test.Dimensions#depth
	 **/
	public static volatile SingularAttribute<Dimensions, BigDecimal> depth;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Dimensions#width
	 **/
	public static volatile SingularAttribute<Dimensions, BigDecimal> width;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Dimensions
	 **/
	public static volatile EmbeddableType<Dimensions> class_;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Dimensions#height
	 **/
	public static volatile SingularAttribute<Dimensions, BigDecimal> height;

}

