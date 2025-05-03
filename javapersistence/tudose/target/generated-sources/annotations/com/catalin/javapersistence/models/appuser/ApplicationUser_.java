package com.catalin.javapersistence.models.appuser;

import com.catalin.javapersistence.models.auction.AuctionParticipant;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ApplicationUser.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class ApplicationUser_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String APPLICATION_USER_LAST_NAME = "applicationUserLastName";
	public static final String APPLICATION_USER_EMAIL = "applicationUserEmail";
	public static final String APPLICATION_USER_ROLE_ASSIGNMENTS = "applicationUserRoleAssignments";
	public static final String APPLICATION_USER_USERNAME = "applicationUserUsername";
	public static final String APPLICATION_USER_FIRST_NAME = "applicationUserFirstName";
	public static final String AUCTION_PARTICIPANTS = "auctionParticipants";

	
	/**
	 * @see com.catalin.javapersistence.models.appuser.ApplicationUser#applicationUserLastName
	 **/
	public static volatile SingularAttribute<ApplicationUser, String> applicationUserLastName;
	
	/**
	 * @see com.catalin.javapersistence.models.appuser.ApplicationUser#applicationUserEmail
	 **/
	public static volatile SingularAttribute<ApplicationUser, String> applicationUserEmail;
	
	/**
	 * @see com.catalin.javapersistence.models.appuser.ApplicationUser#applicationUserRoleAssignments
	 **/
	public static volatile SetAttribute<ApplicationUser, ApplicationUserRoleAssignment> applicationUserRoleAssignments;
	
	/**
	 * @see com.catalin.javapersistence.models.appuser.ApplicationUser#applicationUserUsername
	 **/
	public static volatile SingularAttribute<ApplicationUser, String> applicationUserUsername;
	
	/**
	 * @see com.catalin.javapersistence.models.appuser.ApplicationUser#applicationUserFirstName
	 **/
	public static volatile SingularAttribute<ApplicationUser, String> applicationUserFirstName;
	
	/**
	 * @see com.catalin.javapersistence.models.appuser.ApplicationUser#auctionParticipants
	 **/
	public static volatile SetAttribute<ApplicationUser, AuctionParticipant> auctionParticipants;
	
	/**
	 * @see com.catalin.javapersistence.models.appuser.ApplicationUser
	 **/
	public static volatile EntityType<ApplicationUser> class_;

}

