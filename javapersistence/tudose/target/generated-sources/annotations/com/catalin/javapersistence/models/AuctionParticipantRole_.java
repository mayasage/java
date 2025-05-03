package com.catalin.javapersistence.models;

import com.catalin.javapersistence.models.auction.AuctionParticipantRole;
import com.catalin.javapersistence.models.auction.AuctionParticipantRoleActionAssignment;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AuctionParticipantRole.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionParticipantRole_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String AUCTION_PARTICIPANT_ROLE_NAME = "auctionParticipantRoleName";
	public static final String AUCTION_PARTICIPANT_ROLE_ACTIONS = "auctionParticipantRoleActions";

	
	/**
	 * @see AuctionParticipantRole#auctionParticipantRoleName
	 **/
	public static volatile SingularAttribute<AuctionParticipantRole, String> auctionParticipantRoleName;
	
	/**
	 * @see AuctionParticipantRole
	 **/
	public static volatile EntityType<AuctionParticipantRole> class_;
	
	/**
	 * @see AuctionParticipantRole#auctionParticipantRoleActions
	 **/
	public static volatile SetAttribute<AuctionParticipantRole, AuctionParticipantRoleActionAssignment> auctionParticipantRoleActions;

}

