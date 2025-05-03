package com.catalin.javapersistence.models.appuser;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ApplicationUserAction.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class ApplicationUserAction_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String APPLICATION_USER_ROLE_ACTION_ASSIGNMENTS = "applicationUserRoleActionAssignments";
	public static final String APPLICATION_USER_ACTION_NAME = "applicationUserActionName";

	
	/**
	 * @see com.catalin.javapersistence.models.appuser.ApplicationUserAction#applicationUserRoleActionAssignments
	 **/
	public static volatile SetAttribute<ApplicationUserAction, ApplicationUserRoleActionAssignment> applicationUserRoleActionAssignments;
	
	/**
	 * @see com.catalin.javapersistence.models.appuser.ApplicationUserAction#applicationUserActionName
	 **/
	public static volatile SingularAttribute<ApplicationUserAction, String> applicationUserActionName;
	
	/**
	 * @see com.catalin.javapersistence.models.appuser.ApplicationUserAction
	 **/
	public static volatile EntityType<ApplicationUserAction> class_;

}

