package com.catalin.javapersistence.models;

import com.catalin.javapersistence.models.appuser.ApplicationUser;
import com.catalin.javapersistence.models.auction.AuctionEvent;
import com.catalin.javapersistence.models.auction.AuctionParticipant;
import com.catalin.javapersistence.models.auction.AuctionParticipantEventRoleAssignment;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AuctionParticipant.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionParticipant_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String APP_USER = "applicationUser";
	public static final String AUCTION_EVENT = "auctionEvent";
	public static final String AUCTION_PARTICIPANT_ROLE_ASSIGNMENTS = "auctionParticipantRoleAssignments";

	
	/**
	 * @see AuctionParticipant#applicationUser
	 **/
	public static volatile SingularAttribute<AuctionParticipant, ApplicationUser> applicationUser;
	
	/**
	 * @see AuctionParticipant#auctionEvent
	 **/
	public static volatile SingularAttribute<AuctionParticipant, AuctionEvent> auctionEvent;
	
	/**
	 * @see AuctionParticipant#auctionParticipantRoleAssignments
	 **/
	public static volatile SetAttribute<AuctionParticipant, AuctionParticipantEventRoleAssignment> auctionParticipantRoleAssignments;
	
	/**
	 * @see AuctionParticipant
	 **/
	public static volatile EntityType<AuctionParticipant> class_;

}

