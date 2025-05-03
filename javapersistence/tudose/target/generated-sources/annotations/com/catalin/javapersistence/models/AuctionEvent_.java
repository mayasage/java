package com.catalin.javapersistence.models;

import com.catalin.javapersistence.models.auction.AuctionEvent;
import com.catalin.javapersistence.models.auction.AuctionEventLog;
import com.catalin.javapersistence.models.auction.AuctionLot;
import com.catalin.javapersistence.models.auction.AuctionParticipant;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AuctionEvent.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionEvent_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String AUCTION_EVENT_DESCRIPTION = "auctionEventDescription";
	public static final String AUCTION_EVENT_LOGS = "auctionEventLogs";
	public static final String AUCTION_PARTICIPANTS = "auctionParticipants";
	public static final String AUCTION_EVENT_NAME = "auctionEventName";
	public static final String AUCTION_LOTS = "auctionLots";

	
	/**
	 * @see AuctionEvent#auctionEventDescription
	 **/
	public static volatile SingularAttribute<AuctionEvent, String> auctionEventDescription;
	
	/**
	 * @see AuctionEvent#auctionEventLogs
	 **/
	public static volatile ListAttribute<AuctionEvent, AuctionEventLog> auctionEventLogs;
	
	/**
	 * @see AuctionEvent#auctionParticipants
	 **/
	public static volatile SetAttribute<AuctionEvent, AuctionParticipant> auctionParticipants;
	
	/**
	 * @see AuctionEvent#auctionEventName
	 **/
	public static volatile SingularAttribute<AuctionEvent, String> auctionEventName;
	
	/**
	 * @see AuctionEvent#auctionLots
	 **/
	public static volatile SetAttribute<AuctionEvent, AuctionLot> auctionLots;
	
	/**
	 * @see AuctionEvent
	 **/
	public static volatile EntityType<AuctionEvent> class_;

}

