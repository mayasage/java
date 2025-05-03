package com.catalin.javapersistence.models.auction;

import com.catalin.javapersistence.models.appuser.ApplicationUser;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AuctionParticipant.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionParticipant_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String AUCTION_EVENT = "auctionEvent";
	public static final String APPLICATION_USER = "applicationUser";
	public static final String AUCTION_PARTICIPANT_EVENT_ROLE_ASSIGNMENTS = "auctionParticipantEventRoleAssignments";
	public static final String AUCTION_BIDS = "auctionBids";

	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionParticipant#auctionEvent
	 **/
	public static volatile SingularAttribute<AuctionParticipant, AuctionEvent> auctionEvent;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionParticipant#applicationUser
	 **/
	public static volatile SingularAttribute<AuctionParticipant, ApplicationUser> applicationUser;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionParticipant#auctionParticipantEventRoleAssignments
	 **/
	public static volatile SetAttribute<AuctionParticipant, AuctionParticipantEventRoleAssignment> auctionParticipantEventRoleAssignments;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionParticipant#auctionBids
	 **/
	public static volatile SetAttribute<AuctionParticipant, AuctionBid> auctionBids;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionParticipant
	 **/
	public static volatile EntityType<AuctionParticipant> class_;

}

