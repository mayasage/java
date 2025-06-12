package org.blacksage.learn.easyschool.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "holidays")
public class Holiday extends BaseEntity {

        private String day;
        private String reason;

        @Enumerated(EnumType.STRING)
        private Type type;

        public static Holiday createHoliday(String day,
                                            String reason,
                                            Holiday.Type type,
                                            String createdBy) {

                Holiday holiday = new Holiday();

                holiday.setDay(day);
                holiday.setReason(reason);
                holiday.setType(type);

                holiday.setCreatedAt(LocalDateTime.now());
                holiday.setCreatedBy(createdBy);

                return holiday;
        }

        public enum Type {
                FESTIVAL, FEDERAL
        }
}
