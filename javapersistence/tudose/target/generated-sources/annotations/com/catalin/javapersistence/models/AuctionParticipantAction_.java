package com.catalin.javapersistence.models;

import com.catalin.javapersistence.models.auction.AuctionParticipantAction;
import com.catalin.javapersistence.models.auction.AuctionParticipantRoleActionAssignment;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AuctionParticipantAction.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionParticipantAction_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String AUCTION_PARTICIPANT_ACTION_NAME = "auctionParticipantActionName";
	public static final String AUCTION_PARTICIPANT_ROLE_ACTIONS = "auctionParticipantRoleActions";

	
	/**
	 * @see AuctionParticipantAction#auctionParticipantActionName
	 **/
	public static volatile SingularAttribute<AuctionParticipantAction, String> auctionParticipantActionName;
	
	/**
	 * @see AuctionParticipantAction
	 **/
	public static volatile EntityType<AuctionParticipantAction> class_;
	
	/**
	 * @see AuctionParticipantAction#auctionParticipantRoleActions
	 **/
	public static volatile SetAttribute<AuctionParticipantAction, AuctionParticipantRoleActionAssignment> auctionParticipantRoleActions;

}

