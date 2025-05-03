package com.catalin.javapersistence.models;

import com.catalin.javapersistence.models.auction.AuctionEvent;
import com.catalin.javapersistence.models.auction.AuctionItem;
import com.catalin.javapersistence.models.auction.AuctionLot;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AuctionLot.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionLot_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String AUCTION_EVENT = "auctionEvent";
	public static final String AUCTION_ITEM = "auctionItem";

	
	/**
	 * @see AuctionLot#auctionEvent
	 **/
	public static volatile SingularAttribute<AuctionLot, AuctionEvent> auctionEvent;
	
	/**
	 * @see AuctionLot#auctionItem
	 **/
	public static volatile SingularAttribute<AuctionLot, AuctionItem> auctionItem;
	
	/**
	 * @see AuctionLot
	 **/
	public static volatile EntityType<AuctionLot> class_;

}

