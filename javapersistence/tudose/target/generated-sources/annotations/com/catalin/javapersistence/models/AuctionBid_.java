package com.catalin.javapersistence.models;

import com.catalin.javapersistence.models.auction.AuctionBid;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.time.Instant;

@StaticMetamodel(AuctionBid.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionBid_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String BID_CREATED_AT = "bidCreatedAt";
	public static final String BID_AMOUNT = "bidAmount";

	
	/**
	 * @see AuctionBid#bidCreatedAt
	 **/
	public static volatile SingularAttribute<AuctionBid, Instant> bidCreatedAt;
	
	/**
	 * @see AuctionBid#bidAmount
	 **/
	public static volatile SingularAttribute<AuctionBid, BigDecimal> bidAmount;
	
	/**
	 * @see AuctionBid
	 **/
	public static volatile EntityType<AuctionBid> class_;

}

