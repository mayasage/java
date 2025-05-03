package com.catalin.javapersistence.models.test;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(Weight.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Weight_ extends com.catalin.javapersistence.models.test.Measurement_ {

	public static final String WEIGHT_VALUE = "weightValue";

	
	/**
	 * @see com.catalin.javapersistence.models.test.Weight
	 **/
	public static volatile EmbeddableType<Weight> class_;
	
	/**
	 * @see com.catalin.javapersistence.models.test.Weight#weightValue
	 **/
	public static volatile SingularAttribute<Weight, BigDecimal> weightValue;

}

