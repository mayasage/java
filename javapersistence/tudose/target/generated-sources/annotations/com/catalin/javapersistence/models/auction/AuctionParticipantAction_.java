package com.catalin.javapersistence.models.auction;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AuctionParticipantAction.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionParticipantAction_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String AUCTION_PARTICIPANT_ROLE_ACTION_ASSIGNMENTS = "auctionParticipantRoleActionAssignments";
	public static final String AUCTION_PARTICIPANT_ACTION_NAME = "auctionParticipantActionName";

	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionParticipantAction#auctionParticipantRoleActionAssignments
	 **/
	public static volatile SetAttribute<AuctionParticipantAction, AuctionParticipantRoleActionAssignment> auctionParticipantRoleActionAssignments;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionParticipantAction#auctionParticipantActionName
	 **/
	public static volatile SingularAttribute<AuctionParticipantAction, String> auctionParticipantActionName;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionParticipantAction
	 **/
	public static volatile EntityType<AuctionParticipantAction> class_;

}

