package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.exceptions.DuplicateItemNameException;
import com.catalin.javapersistence.repositories.test.ItemRepository;
import com.catalin.javapersistence.repositories.test.LogRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.IllegalTransactionStateException;

import java.time.ZonedDateTime;

@SpringBootTest
public class TransactionPropagationTest {

        @Autowired
        LogRepository logRepository;

        @Autowired
        ItemRepository itemRepository;

        // Rollback doesn't stop logging
        @Test
        public void addItem() {
                Item item = new Item();
                item.setItemName("Item1");
                item.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
                item.setCreationTime(ZonedDateTime.now());

                itemRepository.addItem(item);

                Log log = logRepository.findAll().getFirst();
                Item item1 = itemRepository.findAll().getFirst();

                Assertions.assertAll(
                        () -> Assertions.assertEquals("Item1", item1.getItemName()),
                        () -> Assertions.assertTrue(log.getMessage().startsWith("Adding item with name " + item.getItemName() + " at "))
                );

                Item notAdded = new Item();
                notAdded.setItemName("Item1");
                notAdded.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
                notAdded.setCreationTime(ZonedDateTime.now());

                Assertions.assertThrows(DuplicateItemNameException.class, () -> itemRepository.addItem(notAdded));

                Assertions.assertAll(
                        () -> Assertions.assertEquals(2, logRepository.count()),
                        () -> Assertions.assertEquals(1, itemRepository.count())
                );
        }

        // Rollback stops logging
        @Test
        public void addItemAndLogInSameTransaction() {
                Item item = new Item();
                item.setItemName("Item1");
                item.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
                item.setCreationTime(ZonedDateTime.now());

                itemRepository.addItemAndLogInSameTransaction(item);

                Log log = logRepository.findAll().getFirst();
                Item item1 = itemRepository.findAll().getFirst();

                Assertions.assertAll(
                        () -> Assertions.assertEquals("Item1", item1.getItemName()),
                        () -> Assertions.assertTrue(log.getMessage().startsWith("Adding item with name " + item.getItemName() + " at "))
                );

                Item notAdded = new Item();
                notAdded.setItemName("Item1");
                notAdded.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
                notAdded.setCreationTime(ZonedDateTime.now());

                Assertions.assertThrows(DuplicateItemNameException.class, () -> itemRepository.addItemAndLogInSameTransaction(notAdded));

                Assertions.assertAll(
                        () -> Assertions.assertEquals(1, logRepository.count()), // log entry was also rolled back
                        () -> Assertions.assertEquals(1, itemRepository.count())
                );
        }

        // Don't rollback on expected exception
        @Test
        public void noRollback() {
                Item item = new Item();
                item.setItemName("Item1");
                item.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
                item.setCreationTime(ZonedDateTime.now());

                itemRepository.addItemNoRollback(item);

                Log log = logRepository.findAll().getFirst();
                Item item1 = itemRepository.findAll().getFirst();

                Assertions.assertAll(
                        () -> Assertions.assertEquals("Item1", item1.getItemName()),
                        () -> Assertions.assertTrue(log.getMessage().startsWith("Adding item with name " + item.getItemName() + " at "))
                );

                Item notAdded = new Item();
                notAdded.setItemName("Item1");
                notAdded.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
                notAdded.setCreationTime(ZonedDateTime.now());

                Assertions.assertThrows(DuplicateItemNameException.class, () -> itemRepository.addItemNoRollback(notAdded));

                Assertions.assertAll(
                        () -> Assertions.assertEquals(2, logRepository.count()), // log entry wasn't rolled back
                        () -> Assertions.assertEquals(1, itemRepository.count())
                );
        }

        @Test
        public void mandatoryTransaction() {
                Item item = new Item();
                item.setItemName("Item1");
                item.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
                item.setCreationTime(ZonedDateTime.now());

                IllegalTransactionStateException ex = Assertions.assertThrows(IllegalTransactionStateException.class,
                        () -> itemRepository.noDuplicateName(item));

                Assertions.assertEquals("No existing transaction found for transaction marked with propagation " +
                                        "'mandatory'",
                        ex.getMessage());
        }

        @Test
        public void noTransaction() {
                Assertions.assertDoesNotThrow(() -> logRepository.showLogs());
                Assertions.assertThrows(IllegalTransactionStateException.class, () -> itemRepository.callShowLogsInTransaction());
        }

        @Test
        public void supportsTransaction() {
                RuntimeException ex = Assertions.assertThrows(RuntimeException.class,
                        () -> itemRepository.callShowLogsSupportsThrowErrorInTransaction());
                Assertions.assertAll(
                        () -> Assertions.assertEquals("something went wrong", ex.getMessage()),
                        () -> Assertions.assertEquals(0, logRepository.count())
                );

                RuntimeException ex2 = Assertions.assertThrows(RuntimeException.class,
                        () -> logRepository.showLogsSupportsThrowError());
                Assertions.assertAll(
                        () -> Assertions.assertEquals("something went wrong", ex2.getMessage()),
                        () -> Assertions.assertEquals(1, logRepository.count())
                );
        }

        @Test
        public void notSupportedTransaction() {
                RuntimeException ex = Assertions.assertThrows(RuntimeException.class,
                        () -> itemRepository.callShowLogsNotSupportedThrowErrorInTransaction());
                Assertions.assertAll(
                        () -> Assertions.assertEquals("something went wrong", ex.getMessage()),
                        () -> Assertions.assertEquals(1, logRepository.count())
                );

                RuntimeException ex2 = Assertions.assertThrows(RuntimeException.class,
                        () -> logRepository.showLogsNotSupportedThrowError());
                Assertions.assertAll(
                        () -> Assertions.assertEquals("something went wrong", ex2.getMessage()),
                        () -> Assertions.assertEquals(2, logRepository.count())
                );
        }
}
