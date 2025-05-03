package com.catalin.javapersistence.models.auction;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(AuctionBid.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionBid_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String AUCTION_LOT = "auctionLot";
	public static final String AUCTION_BID_AMOUNT = "auctionBidAmount";
	public static final String AUCTION_PARTICIPANT = "auctionParticipant";

	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionBid#auctionLot
	 **/
	public static volatile SingularAttribute<AuctionBid, AuctionLot> auctionLot;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionBid#auctionBidAmount
	 **/
	public static volatile SingularAttribute<AuctionBid, BigDecimal> auctionBidAmount;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionBid#auctionParticipant
	 **/
	public static volatile SingularAttribute<AuctionBid, AuctionParticipant> auctionParticipant;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionBid
	 **/
	public static volatile EntityType<AuctionBid> class_;

}

