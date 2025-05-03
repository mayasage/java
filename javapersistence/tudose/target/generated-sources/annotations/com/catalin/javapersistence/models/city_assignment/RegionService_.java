package com.catalin.javapersistence.models.city_assignment;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Region.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class RegionService_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String COUNTRY = "country";
	public static final String CITY = "city";
	public static final String DISTRICT = "district";
	public static final String STATE = "state";

	
	/**
	 * @see Region#country
	 **/
	public static volatile SingularAttribute<Region, String> country;
	
	/**
	 * @see Region#city
	 **/
	public static volatile SingularAttribute<Region, String> city;
	
	/**
	 * @see Region#district
	 **/
	public static volatile SingularAttribute<Region, String> district;
	
	/**
	 * @see Region#state
	 **/
	public static volatile SingularAttribute<Region, String> state;
	
	/**
	 * @see Region
	 **/
	public static volatile EntityType<Region> class_;

}

