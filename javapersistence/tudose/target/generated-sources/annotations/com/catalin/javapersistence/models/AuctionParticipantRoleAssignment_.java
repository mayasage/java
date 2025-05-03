package com.catalin.javapersistence.models;

import com.catalin.javapersistence.models.auction.AuctionParticipant;
import com.catalin.javapersistence.models.auction.AuctionParticipantEventRoleAssignment;
import com.catalin.javapersistence.models.auction.AuctionParticipantRole;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AuctionParticipantEventRoleAssignment.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionParticipantRoleAssignment_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String AUCTION_PARTICIPANT = "auctionParticipant";
	public static final String AUCTION_PARTICIPANT_ROLE = "auctionParticipantRole";

	
	/**
	 * @see AuctionParticipantEventRoleAssignment#auctionParticipant
	 **/
	public static volatile SingularAttribute<AuctionParticipantEventRoleAssignment, AuctionParticipant> auctionParticipant;
	
	/**
	 * @see AuctionParticipantEventRoleAssignment#auctionParticipantRole
	 **/
	public static volatile SingularAttribute<AuctionParticipantEventRoleAssignment, AuctionParticipantRole> auctionParticipantRole;
	
	/**
	 * @see AuctionParticipantEventRoleAssignment
	 **/
	public static volatile EntityType<AuctionParticipantEventRoleAssignment> class_;

}

