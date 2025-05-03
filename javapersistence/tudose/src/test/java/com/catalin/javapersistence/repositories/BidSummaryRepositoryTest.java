package com.catalin.javapersistence.repositories;

import jakarta.transaction.Transactional;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
@Transactional
@Commit
public class BidSummaryRepositoryTest {
//
//        @Autowired
//        private UserRepository userRepository;
//
//        @Autowired
//        private ItemRepository itemRepository;
//
//        @BeforeEach
//        public void beforeEach() {
//                Item jewelOfKarnaca = new Item("Jewel Of Karnaca");
//                jewelOfKarnaca.setItemInitialPrice(new BigDecimal(100));
//
//                itemRepository.save(jewelOfKarnaca);
//
//                User lara = new User("lara_alana", "lara.alana@gmail.com", "Lara Alana");
//                User sage = new User("sage_morgan", "sage.morgan@gmail.com", "Sage Morgan-Burke");
//
//                userRepository.save(lara);
//                userRepository.save(sage);
//        }

//        @AfterEach
//        public void afterEach() {
//                Item jewelOfKarnaca = itemRepository.findItemByItemName("Jewel Of Karnaca");
//                User lara = userRepository.findUserByUsername("lara_alana");
//                User sage = userRepository.findUserByUsername("sage_morgan");
//
//                userRepository.delete(lara);
//                userRepository.delete(sage);
//                itemRepository.delete(jewelOfKarnaca);
//        }

//        @Test
//        public void runAuction() {
//                Item jewelOfKarnaca = itemRepository.findItemByItemName("Jewel Of Karnaca");
//                User lara = userRepository.findUserByUsername("lara_alana");
//                User sage = userRepository.findUserByUsername("sage_morgan");
//
//                jewelOfKarnaca.startAuction();
//
//                lara.
//
//                Bid laraBid1 = new Bid(jewelOfKarnaca, new BigDecimal(150));
//                Bid sageBid1 = new Bid(jewelOfKarnaca, new BigDecimal(200));
//                Bid laraBid2 = new Bid(jewelOfKarnaca, new BigDecimal(250));
//                Bid sageBid2 = new Bid(jewelOfKarnaca, new BigDecimal(300));
//                Bid laraBid3 = new Bid(jewelOfKarnaca, new BigDecimal(400));
//
//                entityManager.persist(laraBid1);
//                entityManager.persist(sageBid1);
//                entityManager.persist(laraBid2);
//                entityManager.persist(sageBid2);
//                entityManager.persist(laraBid3);
//
//                jewelOfKarnaca.setBidItemAuctionEndedAt(ZonedDateTime.now());
//                entityManager.getTransaction().commit();
//        }
}
