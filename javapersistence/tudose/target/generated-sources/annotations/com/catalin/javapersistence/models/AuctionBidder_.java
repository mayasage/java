package com.catalin.javapersistence.models;

import com.catalin.javapersistence.models.auction.AuctionBid;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.ZonedDateTime;

@StaticMetamodel(AuctionBidder.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class AuctionBidder_ extends com.catalin.javapersistence.models.base.AbstractEntityBase_ {

	public static final String USER_UPDATED_AT = "userUpdatedAt";
	public static final String AUCTION_BIDS = "auctionBids";
	public static final String USER_LAST_NAME = "userLastName";
	public static final String USER_EMAIL = "userEmail";
	public static final String USER_CREATED_AT = "userCreatedAt";
	public static final String USER_FIRST_NAME = "userFirstName";
	public static final String USER_ID = "userId";
	public static final String USERNAME = "username";

	
	/**
	 * @see com.catalin.javapersistence.models.AuctionBidder#userUpdatedAt
	 **/
	public static volatile SingularAttribute<AuctionBidder, ZonedDateTime> userUpdatedAt;
	
	/**
	 * @see com.catalin.javapersistence.models.AuctionBidder#auctionBids
	 **/
	public static volatile SetAttribute<AuctionBidder, AuctionBid> auctionBids;
	
	/**
	 * @see com.catalin.javapersistence.models.AuctionBidder#userLastName
	 **/
	public static volatile SingularAttribute<AuctionBidder, String> userLastName;
	
	/**
	 * @see com.catalin.javapersistence.models.AuctionBidder#userEmail
	 **/
	public static volatile SingularAttribute<AuctionBidder, String> userEmail;
	
	/**
	 * @see com.catalin.javapersistence.models.AuctionBidder#userCreatedAt
	 **/
	public static volatile SingularAttribute<AuctionBidder, ZonedDateTime> userCreatedAt;
	
	/**
	 * @see com.catalin.javapersistence.models.AuctionBidder#userFirstName
	 **/
	public static volatile SingularAttribute<AuctionBidder, String> userFirstName;
	
	/**
	 * @see com.catalin.javapersistence.models.AuctionBidder
	 **/
	public static volatile EntityType<AuctionBidder> class_;
	
	/**
	 * @see com.catalin.javapersistence.models.AuctionBidder#userId
	 **/
	public static volatile SingularAttribute<AuctionBidder, Long> userId;
	
	/**
	 * @see com.catalin.javapersistence.models.AuctionBidder#username
	 **/
	public static volatile SingularAttribute<AuctionBidder, String> username;

}

