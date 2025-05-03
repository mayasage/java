package com.catalin.javapersistence.models;

import com.catalin.javapersistence.models.appuser.ApplicationUserAction;
import com.catalin.javapersistence.models.appuser.ApplicationUserRole;
import com.catalin.javapersistence.models.appuser.ApplicationUserRoleActionAssignment;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ApplicationUserRoleActionAssignment.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class ApplicationUserRoleAction_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

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

