package com.catalin.javapersistence.models;

import com.catalin.javapersistence.models.appuser.ApplicationUser;
import com.catalin.javapersistence.models.auction.AuctionParticipant;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ApplicationUser.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class ApplicationUser_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String AUCTION_PARTICIPANTS = "auctionParticipants";
	public static final String APP_USER_FIRST_NAME = "applicationUserFirstName";
	public static final String APP_USER_LAST_NAME = "applicationUserLastName";
	public static final String APP_USER_EMAIL = "applicationUserEmail";
	public static final String APP_USER_USERNAME = "applicationUserUsername";

	
	/**
	 * @see ApplicationUser#auctionParticipants
	 **/
	public static volatile SetAttribute<ApplicationUser, AuctionParticipant> auctionParticipants;
	
	/**
	 * @see ApplicationUser#applicationUserFirstName
	 **/
	public static volatile SingularAttribute<ApplicationUser, String> applicationUserFirstName;
	
	/**
	 * @see ApplicationUser#applicationUserLastName
	 **/
	public static volatile SingularAttribute<ApplicationUser, String> applicationUserLastName;
	
	/**
	 * @see ApplicationUser#applicationUserEmail
	 **/
	public static volatile SingularAttribute<ApplicationUser, String> applicationUserEmail;
	
	/**
	 * @see ApplicationUser
	 **/
	public static volatile EntityType<ApplicationUser> class_;
	
	/**
	 * @see ApplicationUser#applicationUserUsername
	 **/
	public static volatile SingularAttribute<ApplicationUser, String> applicationUserUsername;

}

