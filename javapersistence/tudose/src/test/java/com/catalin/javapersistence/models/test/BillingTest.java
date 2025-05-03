package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.repositories.test.BankAccountRepository;
import com.catalin.javapersistence.repositories.test.BillingDetailsRepository;
import com.catalin.javapersistence.repositories.test.CreditCardRepository;
import com.catalin.javapersistence.repositories.test.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BillingTest {

        @Autowired
        private CreditCardRepository creditCardRepository;

        @Autowired
        private BankAccountRepository bankAccountRepository;

        @Autowired
        private BillingDetailsRepository billingDetailsRepository;
        @Autowired
        private UserRepository userRepository;

        @Test
        @Transactional
        public void storeLoadEntities() {
                CreditCard creditCard = new CreditCard();
                creditCard.setOwner("Sage Morgan");
                creditCard.setCcNumber("1");
                creditCard.setExpMonth("10");
                creditCard.setExpYear("2030");
                creditCardRepository.save(creditCard);

//                BankAccount bankAccount = new BankAccount();
//                bankAccount.setOwner("Maya Bailey");
//                bankAccount.setBankName("Burgmeister & Royce");
//                bankAccount.setAccNumber("2");
//                bankAccount.setSwift("3");
//                bankAccountRepository.save(bankAccount);
//
//                BankAccount bankAccount3 = new BankAccount();
//                bankAccount3.setOwner("deleteme");
//                bankAccount3.setBankName("deleteme");
//                bankAccount3.setAccNumber("deleteme");
//                bankAccount3.setSwift("deleteme");
//                bankAccountRepository.save(bankAccount3);
//
//                List<CreditCard> creditCards = creditCardRepository.findByOwner("Sage Morgan");
//                List<BankAccount> bankAccounts = bankAccountRepository.findByOwner("Maya Bailey");
//                List<CreditCard> creditCards2 = creditCardRepository.findByExpYear("2030");
//                List<BankAccount> bankAccounts2 = bankAccountRepository.findBySwift("3");
//
//                Assertions.assertAll(
//                        () -> Assertions.assertEquals(1, creditCards.size()),
//                        () -> Assertions.assertEquals("1", creditCards.getFirst().getCcNumber()),
//                        () -> Assertions.assertEquals(1, bankAccounts.size()),
//                        () -> Assertions.assertEquals("Burgmeister & Royce", bankAccounts.getFirst().getBankName()),
//                        () -> Assertions.assertEquals(1, creditCards2.size()),
//                        () -> Assertions.assertEquals("Sage Morgan", creditCards2.getFirst().getOwner()),
//                        () -> Assertions.assertEquals("Maya Bailey", bankAccounts2.getFirst().getOwner())
//                );
//
//                System.out.println("I'll hit the findAll query, and you should see the Union query");
//
//                billingDetailsRepository.findAll();

                System.out.println("I am trying to create user now");
//
//                User user = new User();
//                user.getBillingDetails().add(creditCard);
//                userRepository.save(user);
//
//                List<User> users = userRepository.findAll();
//                System.out.println("Users found: " + users);
        }
}
