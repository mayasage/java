package org.blacksage.learn.easyschool.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Holiday extends BaseEntity {

        private String day;
        private String reason;
        private Type type;

        public enum Type {
                FESTIVAL, FEDERAL
        }

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
}
