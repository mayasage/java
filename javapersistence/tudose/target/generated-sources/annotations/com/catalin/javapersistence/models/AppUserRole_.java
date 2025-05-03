package com.catalin.javapersistence.models;

import com.catalin.javapersistence.models.appuser.ApplicationUserRole;
import com.catalin.javapersistence.models.appuser.ApplicationUserRoleActionAssignment;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ApplicationUserRole.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class ApplicationUserRole_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String APP_USER_ROLE_NAME = "applicationUserRoleName";
	public static final String APP_USER_ROLE_ACTIONS = "applicationUserRoleActions";

	
	/**
	 * @see ApplicationUserRole#applicationUserRoleName
	 **/
	public static volatile SingularAttribute<ApplicationUserRole, String> applicationUserRoleName;
	
	/**
	 * @see ApplicationUserRole#applicationUserRoleActions
	 **/
	public static volatile SetAttribute<ApplicationUserRole, ApplicationUserRoleActionAssignment> applicationUserRoleActions;
	
	/**
	 * @see ApplicationUserRole
	 **/
	public static volatile EntityType<ApplicationUserRole> class_;

}

