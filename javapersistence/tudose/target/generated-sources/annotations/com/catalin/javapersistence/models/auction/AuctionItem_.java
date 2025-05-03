package com.catalin.javapersistence.models.auction;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(AuctionItem.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionItem_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String AUCTION_LOTS = "auctionLots";
	public static final String AUCTION_ITEM_INITIAL_PRICE = "auctionItemInitialPrice";
	public static final String AUCTION_ITEM_DESCRIPTION = "auctionItemDescription";
	public static final String AUCTION_ITEM_NAME = "auctionItemName";

	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionItem#auctionLots
	 **/
	public static volatile SetAttribute<AuctionItem, AuctionLot> auctionLots;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionItem#auctionItemInitialPrice
	 **/
	public static volatile SingularAttribute<AuctionItem, BigDecimal> auctionItemInitialPrice;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionItem#auctionItemDescription
	 **/
	public static volatile SingularAttribute<AuctionItem, String> auctionItemDescription;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionItem
	 **/
	public static volatile EntityType<AuctionItem> class_;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionItem#auctionItemName
	 **/
	public static volatile SingularAttribute<AuctionItem, String> auctionItemName;

}

