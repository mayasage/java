package com.catalin.javapersistence.models.auction;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AuctionEventParticipantRole.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionEventParticipantRole_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String AUCTION_EVENT = "auctionEvent";
	public static final String AUCTION_PARTICIPANT_EVENT_ROLE_ASSIGNMENTS = "auctionParticipantEventRoleAssignments";
	public static final String AUCTION_PARTICIPANT_ROLE = "auctionParticipantRole";

	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionEventParticipantRole#auctionEvent
	 **/
	public static volatile SingularAttribute<AuctionEventParticipantRole, AuctionEvent> auctionEvent;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionEventParticipantRole#auctionParticipantEventRoleAssignments
	 **/
	public static volatile SetAttribute<AuctionEventParticipantRole, AuctionParticipantEventRoleAssignment> auctionParticipantEventRoleAssignments;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionEventParticipantRole#auctionParticipantRole
	 **/
	public static volatile SingularAttribute<AuctionEventParticipantRole, AuctionParticipantRole> auctionParticipantRole;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionEventParticipantRole
	 **/
	public static volatile EntityType<AuctionEventParticipantRole> class_;

}

