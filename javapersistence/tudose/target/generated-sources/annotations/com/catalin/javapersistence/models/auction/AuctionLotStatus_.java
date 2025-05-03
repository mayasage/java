package com.catalin.javapersistence.models.auction;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AuctionLotStatus.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionLotStatus_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String AUCTION_LOT_STATUS_NAME = "auctionLotStatusName";

	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionLotStatus#auctionLotStatusName
	 **/
	public static volatile SingularAttribute<AuctionLotStatus, String> auctionLotStatusName;
	
	/**
	 * @see com.catalin.javapersistence.models.auction.AuctionLotStatus
	 **/
	public static volatile EntityType<AuctionLotStatus> class_;

}

