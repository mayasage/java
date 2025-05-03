package com.catalin.javapersistence.models.auction;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AuctionParticipantRoleActionAssignment.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionParticipantRoleAction_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String AUCTION_PARTICIPANT_ACTION = "auctionParticipantAction";
	public static final String AUCTION_PARTICIPANT_ROLE = "auctionParticipantRole";

	
	/**
	 * @see AuctionParticipantRoleActionAssignment#auctionParticipantAction
	 **/
	public static volatile SingularAttribute<AuctionParticipantRoleActionAssignment, AuctionParticipantAction> auctionParticipantAction;
	
	/**
	 * @see AuctionParticipantRoleActionAssignment#auctionParticipantRole
	 **/
	public static volatile SingularAttribute<AuctionParticipantRoleActionAssignment, AuctionParticipantRole> auctionParticipantRole;
	
	/**
	 * @see AuctionParticipantRoleActionAssignment
	 **/
	public static volatile EntityType<AuctionParticipantRoleActionAssignment> class_;

}

