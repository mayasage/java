package com.catalin.javapersistence.repositories.test;

import com.catalin.javapersistence.exceptions.DuplicateItemNameException;
import com.catalin.javapersistence.models.test.Item;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class ItemRepositoryImpl implements ItemRepositoryCustom {

        @Lazy
        @Autowired
        private ItemRepository itemRepository;

        @Lazy
        @Autowired
        private LogRepository logRepository;

        @Override
        @Transactional(value = Transactional.TxType.MANDATORY) // requires transaction
        public void noDuplicateName(Item item) {
                Item i = itemRepository.findOneByItemName(item.getItemName());
                if (i != null) throw new DuplicateItemNameException("Item with name " + item.getItemName() + " already exists");
        }

        @Override
        @Transactional(value = Transactional.TxType.REQUIRED) // create new transaction, if not present
        public void addItem(Item item) {
                logRepository.log("Adding item with name " + item.getItemName() + " at " + item.getCreationTime());
                noDuplicateName(item);
                itemRepository.save(item);
        }

        @Override
        @Transactional(value = Transactional.TxType.REQUIRED) // default
        public void addItemAndLogInSameTransaction(Item item) {
                logRepository.logRequireTransaction("Adding item with name " + item.getItemName() + " at " + item.getCreationTime());
                noDuplicateName(item);
                itemRepository.save(item);
        }

        @Override
        @Transactional(dontRollbackOn = DuplicateItemNameException.class)
        public void addItemNoRollback(Item item) {
                addItemAndLogInSameTransaction(item);
        }

        @Override
        @Transactional(value = Transactional.TxType.REQUIRED)
        public void callShowLogsInTransaction() {
                logRepository.showLogs();
        }

        @Override
        @Transactional(value = Transactional.TxType.REQUIRED)
        public void callShowLogsSupportsThrowErrorInTransaction() {
                logRepository.showLogsSupportsThrowError();
        }

        @Override
        @Transactional(value = Transactional.TxType.REQUIRED)
        public void callShowLogsNotSupportedThrowErrorInTransaction() {
                logRepository.showLogsNotSupportedThrowError();
        }
}
