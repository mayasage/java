package com.catalin.javapersistence.models.appuser;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ApplicationUserRole.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class ApplicationUserRole_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String APPLICATION_USER_ROLE_ACTION_ASSIGNMENTS = "applicationUserRoleActionAssignments";
	public static final String APPLICATION_USER_ROLE_ASSIGNMENTS = "applicationUserRoleAssignments";
	public static final String APPLICATION_USER_ROLE_NAME = "applicationUserRoleName";

	
	/**
	 * @see com.catalin.javapersistence.models.appuser.ApplicationUserRole#applicationUserRoleActionAssignments
	 **/
	public static volatile SetAttribute<ApplicationUserRole, ApplicationUserRoleActionAssignment> applicationUserRoleActionAssignments;
	
	/**
	 * @see com.catalin.javapersistence.models.appuser.ApplicationUserRole#applicationUserRoleAssignments
	 **/
	public static volatile SetAttribute<ApplicationUserRole, ApplicationUserRoleAssignment> applicationUserRoleAssignments;
	
	/**
	 * @see com.catalin.javapersistence.models.appuser.ApplicationUserRole#applicationUserRoleName
	 **/
	public static volatile SingularAttribute<ApplicationUserRole, String> applicationUserRoleName;
	
	/**
	 * @see com.catalin.javapersistence.models.appuser.ApplicationUserRole
	 **/
	public static volatile EntityType<ApplicationUserRole> class_;

}

