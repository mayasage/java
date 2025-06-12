package org.blacksage.learn.easyschool.rowmappers;

import org.blacksage.learn.easyschool.model.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRowMapper implements RowMapper<Contact> {

        @Override
        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {

                Contact contact = new Contact();

                contact.setId(rs.getLong("id"));
                contact.setName(rs.getString("name"));
                contact.setMobileNum(rs.getString("mobile_num"));
                contact.setEmail(rs.getString("email"));
                contact.setSubject(rs.getString("subject"));
                contact.setMessage(rs.getString("message"));
                contact.setStatus(rs.getString("status"));

                BaseEntityFieldsMapper.mapBaseEntityFields(rs, contact);

                return contact;
        }
}
