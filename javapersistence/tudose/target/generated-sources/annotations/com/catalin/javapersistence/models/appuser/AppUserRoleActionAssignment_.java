package com.catalin.javapersistence.models.appuser;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ApplicationUserRoleActionAssignment.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class ApplicationUserRoleActionAssignment_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String APP_USER_ROLE = "applicationUserRole";
	public static final String APP_USER_ACTION = "applicationUserAction";

	
	/**
	 * @see ApplicationUserRoleActionAssignment#applicationUserRole
	 **/
	public static volatile SingularAttribute<ApplicationUserRoleActionAssignment, ApplicationUserRole> applicationUserRole;
	
	/**
	 * @see ApplicationUserRoleActionAssignment
	 **/
	public static volatile EntityType<ApplicationUserRoleActionAssignment> class_;
	
	/**
	 * @see ApplicationUserRoleActionAssignment#applicationUserAction
	 **/
	public static volatile SingularAttribute<ApplicationUserRoleActionAssignment, ApplicationUserAction> applicationUserAction;

}

