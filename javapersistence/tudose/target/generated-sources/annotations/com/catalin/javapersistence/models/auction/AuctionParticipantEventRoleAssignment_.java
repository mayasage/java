package com.catalin.javapersistence.models.auction;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AuctionParticipantEventRoleAssignment.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionParticipantEventRoleAssignment_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String AUCTION_PARTICIPANT = "auctionParticipant";
	public static final String AUCTION_EVENT_PARTICIPANT_ROLE = "auctionEventParticipantRole";

	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionParticipantEventRoleAssignment#auctionParticipant
	 **/
	public static volatile SingularAttribute<AuctionParticipantEventRoleAssignment, AuctionParticipant> auctionParticipant;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionParticipantEventRoleAssignment
	 **/
	public static volatile EntityType<AuctionParticipantEventRoleAssignment> class_;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionParticipantEventRoleAssignment#auctionEventParticipantRole
	 **/
	public static volatile SingularAttribute<AuctionParticipantEventRoleAssignment, AuctionEventParticipantRole> auctionEventParticipantRole;

}

