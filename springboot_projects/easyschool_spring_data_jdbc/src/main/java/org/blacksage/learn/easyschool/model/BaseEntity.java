package org.blacksage.learn.easyschool.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseEntity implements BaseEntityContract {

       private LocalDateTime createdAt;
       private String createdBy;

       private LocalDateTime updatedAt;
       private String updatedBy;
}
