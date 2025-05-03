package com.catalin.javapersistence.models;

import com.catalin.javapersistence.models.auction.AuctionItem;
import com.catalin.javapersistence.models.auction.AuctionLot;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(AuctionItem.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Item_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String ITEM_INITIAL_PRICE = "itemInitialPrice";
	public static final String ITEM_NAME = "itemName";
	public static final String AUCTION_LOTS = "auctionLots";
	public static final String ITEM_DESCRIPTION = "itemDescription";

	
	/**
	 * @see AuctionItem#auctionItemInitialPrice
	 **/
	public static volatile SingularAttribute<AuctionItem, BigDecimal> itemInitialPrice;
	
	/**
	 * @see AuctionItem#auctionItemName
	 **/
	public static volatile SingularAttribute<AuctionItem, String> itemName;
	
	/**
	 * @see AuctionItem#auctionLots
	 **/
	public static volatile SetAttribute<AuctionItem, AuctionLot> auctionLots;
	
	/**
	 * @see AuctionItem#auctionItemDescription
	 **/
	public static volatile SingularAttribute<AuctionItem, String> itemDescription;
	
	/**
	 * @see AuctionItem
	 **/
	public static volatile EntityType<AuctionItem> class_;

}

