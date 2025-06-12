package org.blacksage.learn.easyschool.repository;

import lombok.RequiredArgsConstructor;
import org.blacksage.learn.easyschool.constants.ContactStatusConstants;
import org.blacksage.learn.easyschool.model.Contact;
import org.blacksage.learn.easyschool.rowmappers.ContactRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContactRepository {

        private final JdbcTemplate jdbcTemplate;

        public int saveContactMsg(Contact contact){
                String sql = """
                        INSERT INTO contact_msg (
                            name,
                            mobile_num,
                            email,
                            subject,
                            message,
                            status,
                            created_at,
                            created_by
                        ) 
                        VALUES (?,?,?,?,?,?,?,?)
                """;

                return jdbcTemplate.update(
                        sql,
                        contact.getName(),
                        contact.getMobileNum(),
                        contact.getEmail(),
                        contact.getSubject(),
                        contact.getMessage(),
                        contact.getStatus(),
                        contact.getCreatedAt(),
                        contact.getCreatedBy()
                );
        }

        public List<Contact> findMessagesWithStatus(String status) {
            String sql =
                    """
                            SELECT *
                            FROM   contact_msg c
                            WHERE  c.status = ?
                    """;

            return jdbcTemplate.query(sql, new ContactRowMapper(), status);
        }

        public void updateMessageStatus(Long id, String newStatus, String updatedBy) {
                String sql =
                        """
                                UPDATE  contact_msg
                                
                                SET     status = ?,
                                        updated_at = ?,
                                        updated_by = ?
                                        
                                WHERE   id = ?
                        """;

                jdbcTemplate.update(
                        sql,
                        newStatus,
                        LocalDateTime.now(),
                        updatedBy,
                        id
                );
        }
}
