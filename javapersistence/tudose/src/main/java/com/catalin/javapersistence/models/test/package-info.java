@org.hibernate.annotations.FetchProfiles({
        @FetchProfile(
                name = "joinItemBuyer",
                fetchOverrides = @FetchProfile.FetchOverride(
                        entity = Item.class,
                        association = "buyer",
                        mode = FetchMode.JOIN
                )
        ),

        @FetchProfile(
                name = "joinItemBids",
                fetchOverrides = @FetchProfile.FetchOverride(
                        entity = Item.class,
                        association = "bids",
                        mode = FetchMode.SELECT
                )
        )
})

@org.hibernate.annotations.FilterDefs({
        @FilterDef(
                name = "limitByUserRanking",
                parameters = {
                        @org.hibernate.annotations.ParamDef(
                                name = "currentUserRanking",
                                type = Integer.class
                        )
                }
        ),

        @FilterDef(
                name = "limitByUserRankingDefault",
                defaultCondition = """
                                        :currentUserRanking >=   (
                                                                        SELECT  u.ranking
                                                                        FROM    users u
                                                                        WHERE   u.id = seller_id
                                                                )
                                   """,
                parameters = {
                        @org.hibernate.annotations.ParamDef(
                                name = "currentUserRanking",
                                type = Integer.class
                        )
                }
        )
})

package com.catalin.javapersistence.models.test;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.FilterDef;