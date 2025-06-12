package org.blacksage.learn.easyschool.rowmappers;

import org.blacksage.learn.easyschool.model.BaseEntityContract;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseEntityFieldsMapper {
        
        public static void mapBaseEntityFields(ResultSet rs,
                                               BaseEntityContract obj) throws SQLException {

                obj.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                obj.setCreatedBy(rs.getString("created_by"));
                obj.setUpdatedBy(rs.getString("updated_by"));

                if (rs.getTimestamp("updated_at") != null) {
                        obj
                                .setUpdatedAt(rs.getTimestamp("updated_at")
                                        .toLocalDateTime()
                                );
                }
        }
}
