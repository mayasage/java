package org.blacksage.learn.easyschool.model;

import java.time.LocalDateTime;

public interface BaseEntityContract {
        LocalDateTime getCreatedAt();

        void setCreatedAt(LocalDateTime createdAt);

        String getCreatedBy();

        void setCreatedBy(String createdBy);

        LocalDateTime getUpdatedAt();

        void setUpdatedAt(LocalDateTime updatedAt);

        String getUpdatedBy();

        void setUpdatedBy(String updatedBy);
}
