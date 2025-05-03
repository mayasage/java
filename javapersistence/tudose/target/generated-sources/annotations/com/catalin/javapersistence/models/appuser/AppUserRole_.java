package com.catalin.javapersistence.models.appuser;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.ZonedDateTime;

@StaticMetamodel(ApplicationUserRole.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class ApplicationUserRole_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String APP_USER_ROLE_NAME = "applicationUserRoleName";
	public static final String APP_USER_ROLE_ASSIGNMENTS = "applicationUserRoleAssignments";
	public static final String APP_USER_ROLE_ACTION_ASSIGNMENTS = "applicationUserRoleActionAssignments";
	public static final String TEST_CREATED_AT = "testCreatedAt";

	
	/**
	 * @see ApplicationUserRole#applicationUserRoleName
	 **/
	public static volatile SingularAttribute<ApplicationUserRole, String> applicationUserRoleName;
	
	/**
	 * @see ApplicationUserRole#applicationUserRoleAssignments
	 **/
	public static volatile SetAttribute<ApplicationUserRole, ApplicationUserRoleAssignment> applicationUserRoleAssignments;
	
	/**
	 * @see ApplicationUserRole#applicationUserRoleActionAssignments
	 **/
	public static volatile SetAttribute<ApplicationUserRole, ApplicationUserRoleActionAssignment> applicationUserRoleActionAssignments;
	
	/**
	 * @see ApplicationUserRole#testCreatedAt
	 **/
	public static volatile SingularAttribute<ApplicationUserRole, ZonedDateTime> testCreatedAt;
	
	/**
	 * @see ApplicationUserRole
	 **/
	public static volatile EntityType<ApplicationUserRole> class_;

}

