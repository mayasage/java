package org.blacksage.learn.easyschool.repository;

import lombok.RequiredArgsConstructor;
import org.blacksage.learn.easyschool.model.Holiday;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HolidaysRepository {

        private final JdbcTemplate jdbcTemplate;

        public List<Holiday> findAllHolidays() {
                String sql = "SELECT * FROM holidays";
                return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Holiday.class));
        }
}
