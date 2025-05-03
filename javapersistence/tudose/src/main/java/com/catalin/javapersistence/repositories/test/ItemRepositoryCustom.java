package com.catalin.javapersistence.repositories.test;

import com.catalin.javapersistence.models.test.Item;

public interface ItemRepositoryCustom {

        void noDuplicateName(Item item);

        void addItem(Item item);

        void addItemAndLogInSameTransaction(Item item);

        void addItemNoRollback(Item item);

        void callShowLogsInTransaction();

        void callShowLogsSupportsThrowErrorInTransaction();

        void callShowLogsNotSupportedThrowErrorInTransaction();
}
