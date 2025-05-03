package com.catalin.javapersistence.models.auction;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AuctionEventLog.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionEventLog_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String AUCTION_EVENT = "auctionEvent";
	public static final String AUCTION_PARTICIPANT = "auctionParticipant";
	public static final String AUCTION_PARTICIPANT_ACTION = "auctionParticipantAction";

	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionEventLog#auctionEvent
	 **/
	public static volatile SingularAttribute<AuctionEventLog, AuctionEvent> auctionEvent;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionEventLog#auctionParticipant
	 **/
	public static volatile SingularAttribute<AuctionEventLog, AuctionParticipant> auctionParticipant;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionEventLog#auctionParticipantAction
	 **/
	public static volatile SingularAttribute<AuctionEventLog, AuctionParticipantAction> auctionParticipantAction;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionEventLog
	 **/
	public static volatile EntityType<AuctionEventLog> class_;

}

