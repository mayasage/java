package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.repositories.test.ItemRepository;
import com.catalin.javapersistence.repositories.test.ShipmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class ShipmentTest {

        @Autowired
        private ShipmentRepository shipmentRepository;

        @Autowired
        private ItemRepository itemRepository;

        @Test
        public void test() {
                Shipment shipment = new Shipment();
                Item item = new Item();
                item.setItemName("new item");
                item.setMetricWeight(new BigDecimal(2));
                item.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
//                itemRepository.save(item);

//                item.setShipment(shipment);
                itemRepository.save(item);
//                Shipment auctionShipment = new Shipment(item);
//                shipmentRepository.save(auctionShipment);
        }
}
