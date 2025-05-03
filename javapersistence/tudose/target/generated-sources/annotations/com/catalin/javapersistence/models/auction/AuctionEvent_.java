package com.catalin.javapersistence.models.auction;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AuctionEvent.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionEvent_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String AUCTION_EVENT_DESCRIPTION = "auctionEventDescription";
	public static final String AUCTION_PARTICIPANTS = "auctionParticipants";
	public static final String AUCTION_EVENT_NAME = "auctionEventName";
	public static final String AUCTION_LOTS = "auctionLots";
	public static final String AUCTION_EVENT_PARTICIPANT_ROLES = "auctionEventParticipantRoles";

	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionEvent#auctionEventDescription
	 **/
	public static volatile SingularAttribute<AuctionEvent, String> auctionEventDescription;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionEvent#auctionParticipants
	 **/
	public static volatile SetAttribute<AuctionEvent, AuctionParticipant> auctionParticipants;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionEvent#auctionEventName
	 **/
	public static volatile SingularAttribute<AuctionEvent, String> auctionEventName;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionEvent#auctionLots
	 **/
	public static volatile SetAttribute<AuctionEvent, AuctionLot> auctionLots;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionEvent
	 **/
	public static volatile EntityType<AuctionEvent> class_;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionEvent#auctionEventParticipantRoles
	 **/
	public static volatile SetAttribute<AuctionEvent, AuctionEventParticipantRole> auctionEventParticipantRoles;

}

