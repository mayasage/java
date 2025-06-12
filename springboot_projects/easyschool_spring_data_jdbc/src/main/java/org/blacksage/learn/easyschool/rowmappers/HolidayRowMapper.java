package org.blacksage.learn.easyschool.rowmappers;

import org.blacksage.learn.easyschool.model.Holiday;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HolidayRowMapper implements RowMapper<Holiday> {
        
        @Override
        public Holiday mapRow(ResultSet rs, int rowNum) throws SQLException {

                Holiday holiday = new Holiday();

                holiday.setDay(rs.getString("day"));
                holiday.setReason(rs.getString("reason"));
                holiday.setType(Holiday.Type.valueOf(rs.getString("type")));

                BaseEntityFieldsMapper.mapBaseEntityFields(rs, holiday);

                return holiday;
        }
}
