package com.catalin.javapersistence.models.appuser;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ApplicationUserRoleAssignment.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class ApplicationUserRoleAssignment_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String APPLICATION_USER = "applicationUser";
	public static final String APPLICATION_USER_ROLE = "applicationUserRole";

	
	/**
	 * @see com.catalin.javapersistence.models.appuser.ApplicationUserRoleAssignment#applicationUser
	 **/
	public static volatile SingularAttribute<ApplicationUserRoleAssignment, ApplicationUser> applicationUser;
	
	/**
	 * @see com.catalin.javapersistence.models.appuser.ApplicationUserRoleAssignment#applicationUserRole
	 **/
	public static volatile SingularAttribute<ApplicationUserRoleAssignment, ApplicationUserRole> applicationUserRole;
	
	/**
	 * @see com.catalin.javapersistence.models.appuser.ApplicationUserRoleAssignment
	 **/
	public static volatile EntityType<ApplicationUserRoleAssignment> class_;

}

