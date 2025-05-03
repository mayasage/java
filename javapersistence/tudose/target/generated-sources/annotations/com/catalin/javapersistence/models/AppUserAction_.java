package com.catalin.javapersistence.models;

import com.catalin.javapersistence.models.appuser.ApplicationUserAction;
import com.catalin.javapersistence.models.appuser.ApplicationUserRoleActionAssignment;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ApplicationUserAction.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class ApplicationUserAction_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String APP_USER_ROLE_ACTIONS = "applicationUserRoleActions";
	public static final String APP_USER_ACTION_NAME = "applicationUserActionName";

	
	/**
	 * @see ApplicationUserAction#applicationUserRoleActions
	 **/
	public static volatile SetAttribute<ApplicationUserAction, ApplicationUserRoleActionAssignment> applicationUserRoleActions;
	
	/**
	 * @see ApplicationUserAction#applicationUserActionName
	 **/
	public static volatile SingularAttribute<ApplicationUserAction, String> applicationUserActionName;
	
	/**
	 * @see ApplicationUserAction
	 **/
	public static volatile EntityType<ApplicationUserAction> class_;

}

