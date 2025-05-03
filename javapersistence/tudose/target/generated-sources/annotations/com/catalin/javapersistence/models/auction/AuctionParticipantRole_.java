package com.catalin.javapersistence.models.auction;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AuctionParticipantRole.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionParticipantRole_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String IS_CUSTOM = "isCustom";
	public static final String AUCTION_PARTICIPANT_ROLE_ACTION_ASSIGNMENTS = "auctionParticipantRoleActionAssignments";
	public static final String AUCTION_PARTICIPANT_ROLE_NAME = "auctionParticipantRoleName";
	public static final String AUCTION_EVENT_PARTICIPANT_ROLES = "auctionEventParticipantRoles";

	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionParticipantRole#isCustom
	 **/
	public static volatile SingularAttribute<AuctionParticipantRole, Boolean> isCustom;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionParticipantRole#auctionParticipantRoleActionAssignments
	 **/
	public static volatile SetAttribute<AuctionParticipantRole, AuctionParticipantRoleActionAssignment> auctionParticipantRoleActionAssignments;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionParticipantRole#auctionParticipantRoleName
	 **/
	public static volatile SingularAttribute<AuctionParticipantRole, String> auctionParticipantRoleName;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionParticipantRole
	 **/
	public static volatile EntityType<AuctionParticipantRole> class_;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionParticipantRole#auctionEventParticipantRoles
	 **/
	public static volatile SetAttribute<AuctionParticipantRole, AuctionEventParticipantRole> auctionEventParticipantRoles;

}

