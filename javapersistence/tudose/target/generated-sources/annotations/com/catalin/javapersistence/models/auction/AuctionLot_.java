package com.catalin.javapersistence.models.auction;

import com.catalin.javapersistence.models.auction.enums.AuctionLotStatusEnum;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AuctionLot.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionLot_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String AUCTION_EVENT = "auctionEvent";
	public static final String AUCTION_BIDS = "auctionBids";
	public static final String AUCTION_ITEM = "auctionItem";
	public static final String AUCTION_LOT_STATUS = "auctionLotStatus";

	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionLot#auctionEvent
	 **/
	public static volatile SingularAttribute<AuctionLot, AuctionEvent> auctionEvent;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionLot#auctionBids
	 **/
	public static volatile SetAttribute<AuctionLot, AuctionBid> auctionBids;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionLot#auctionItem
	 **/
	public static volatile SingularAttribute<AuctionLot, AuctionItem> auctionItem;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionLot
	 **/
	public static volatile EntityType<AuctionLot> class_;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionLot#auctionLotStatus
	 **/
	public static volatile SingularAttribute<AuctionLot, AuctionLotStatusEnum> auctionLotStatus;

}

