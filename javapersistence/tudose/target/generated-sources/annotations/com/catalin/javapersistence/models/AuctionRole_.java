package com.catalin.javapersistence.models;

import com.catalin.javapersistence.models.auction.AuctionParticipantRole;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AuctionParticipantRole.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionRole_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String AUCTION_ROLE_NAME = "auctionRoleName";

	
	/**
	 * @see AuctionParticipantRole
	 **/
	public static volatile EntityType<AuctionParticipantRole> class_;
	
	/**
	 * @see AuctionParticipantRole#auctionParticipantRoleName
	 **/
	public static volatile SingularAttribute<AuctionParticipantRole, String> auctionRoleName;

}

