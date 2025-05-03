package com.catalin.javapersistence.intitializers;

import com.catalin.javapersistence.models.appuser.ApplicationUser;
import com.catalin.javapersistence.models.appuser.ApplicationUserAction;
import com.catalin.javapersistence.models.appuser.ApplicationUserRole;
import com.catalin.javapersistence.models.appuser.enums.ApplicationUserActionEnum;
import com.catalin.javapersistence.models.appuser.enums.PartialApplicationUserRoleEnum;
import com.catalin.javapersistence.models.auction.enums.PartialAuctionParticipantRoleEnum;
import com.catalin.javapersistence.models.city_assignment.Region;
import com.catalin.javapersistence.repositories.appuser.ApplicationUserActionRepository;
import com.catalin.javapersistence.repositories.appuser.ApplicationUserRoleRepository;
import com.catalin.javapersistence.repositories.appuser.ApplicationUserRepository;
import com.catalin.javapersistence.repositories.auction.AuctionEventRepository;
import com.catalin.javapersistence.repositories.auction.AuctionParticipantActionRepository;
import com.catalin.javapersistence.repositories.auction.AuctionParticipantRoleRepository;
import com.catalin.javapersistence.repositories.city_assignment.RegionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataInitializer {

        private final ApplicationUserRepository applicationUserRepository;
        private final ApplicationUserRoleRepository applicationUserRoleRepository;
        private final ApplicationUserActionRepository applicationUserActionRepository;
        private final AuctionEventRepository auctionEventRepository;
        private final AuctionParticipantActionRepository auctionParticipantActionRepository;
        private final AuctionParticipantRoleRepository auctionParticipantRoleRepository;
        private final RegionRepository regionRepository;
        private final EntityManagerFactory emf;

        @EventListener(ApplicationReadyEvent.class)
        @Transactional
        public void init() {
                // Application domain
//                createApplicationUserActions();
//                createApplicationUserRoles();
//                assignUserActionsToUserRoles();

//                createApplicationAdmin();
//                createAuctionAdmin();

                // Auction domain
//                createAuctionParticipantActions();

                regionSampleData();

                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                String r = (String) em.createNativeQuery("SELECT @@transaction_isolation").getSingleResult();
                System.out.println("transaction isolation: " + r);
                assert r.equals("READ-COMMITTED");
        }

        private void createApplicationUserActions() {
                List<ApplicationUserAction> applicationUserActions =
                        Arrays.stream(ApplicationUserActionEnum.values())
                                .map(
                                        applicationUserActionEnum -> {
                                                ApplicationUserAction applicationUserAction = new ApplicationUserAction();
                                                applicationUserAction.setApplicationUserActionName(applicationUserActionEnum.name());
                                                return applicationUserAction;
                                        }
                                )
                                .collect(Collectors.toList());
                applicationUserActionRepository.saveAll(applicationUserActions);
        }

        private void createApplicationUserRoles() {
                for (PartialApplicationUserRoleEnum partialApplicationUserRoleEnum : PartialApplicationUserRoleEnum.values()) {
                        ApplicationUserRole applicationUserRole = new ApplicationUserRole();
                        applicationUserRole.setApplicationUserRoleName(partialApplicationUserRoleEnum.name());
                        applicationUserRoleRepository.save(applicationUserRole);
                }
        }

        private void assignUserActionsToUserRoles() {
                ApplicationUserRole adminRole =
                        applicationUserRoleRepository
                                .findByApplicationUserRoleName(
                                        PartialApplicationUserRoleEnum.APPLICATION_ADMINISTRATOR.name()
                                );
                assert adminRole != null;

                ApplicationUserRole auctionAdminRole =
                        applicationUserRoleRepository
                                .findByApplicationUserRoleName(
                                        PartialApplicationUserRoleEnum.APPLICATION_AUCTION_ADMINISTRATOR.name()
                                );
                assert auctionAdminRole != null;
                List<String> auctionManagerActions = List.of(
                        ApplicationUserActionEnum.APPLICATION_USER_CAN_VIEW_AUCTIONS.name(),
                        ApplicationUserActionEnum.APPLICATION_USER_CAN_EDIT_AUCTIONS.name(),
                        ApplicationUserActionEnum.APPLICATION_USER_CAN_DELETE_AUCTIONS.name()
                );

                List<ApplicationUserAction> applicationUserActions = applicationUserActionRepository.findAll();
                for (ApplicationUserAction userAction : applicationUserActions) {
                        adminRole.assignApplicationUserAction(userAction);

                        if (auctionManagerActions.contains(userAction.getApplicationUserActionName())) {
                                auctionAdminRole.assignApplicationUserAction(userAction);
                        }
                }

                applicationUserRoleRepository.save(adminRole);
                applicationUserRoleRepository.save(auctionAdminRole);
        }

        private void createApplicationAdmin() {
                ApplicationUser admin = new ApplicationUser();
                admin.setApplicationUserFullName("Lara Alana");
                admin.setApplicationUserEmail("lara");
                admin.setApplicationUserUsername("lara.alana");

                ApplicationUserRole userRole =
                        applicationUserRoleRepository.findByApplicationUserRoleName(
                                PartialApplicationUserRoleEnum.APPLICATION_USER.name()
                        );
                assert userRole != null;
                admin.assignUserRole(userRole);

                ApplicationUserRole adminRole =
                        applicationUserRoleRepository
                                .findByApplicationUserRoleName(
                                        PartialAuctionParticipantRoleEnum.AUCTION_ADMINISTRATOR.name()
                                );
                assert adminRole != null;

                admin.assignUserRole(adminRole);

                applicationUserRepository.save(admin);
        }

        private void createAuctionAdmin() {
                ApplicationUser auctionAdmin = new ApplicationUser();
                auctionAdmin.setApplicationUserFullName("Sage Morgan");
                auctionAdmin.setApplicationUserEmail("sage");
                auctionAdmin.setApplicationUserUsername("sage.morgan");

                ApplicationUserRole userRole =
                        applicationUserRoleRepository.findByApplicationUserRoleName(
                                PartialApplicationUserRoleEnum.APPLICATION_USER.name()
                        );
                assert userRole != null;
                auctionAdmin.assignUserRole(userRole);

                ApplicationUserRole auctionAdministratorRole =
                        applicationUserRoleRepository
                                .findByApplicationUserRoleName(
                                        PartialApplicationUserRoleEnum.APPLICATION_AUCTION_ADMINISTRATOR.name()
                                );
                assert auctionAdministratorRole != null;
                auctionAdmin.assignUserRole(auctionAdministratorRole);

                applicationUserRepository.save(auctionAdmin);
        }

        private void createAuctionParticipantActions() {

        }

        public void regionSampleData() {
                Region region1 = new Region();
                region1.setCountry("USA");
                region1.setState("California");
                region1.setCity("Los Angeles");
                region1.setDistrict("Downtown");
                regionRepository.save(region1);

                Region region2 = new Region();
                region2.setCountry("USA");
                region2.setState("New York");
                region2.setCity("New York City");
                region2.setDistrict("Manhattan");
                regionRepository.save(region2);

                Region region3 = new Region();
                region3.setCountry("Canada");
                region3.setState("Ontario");
                region3.setCity("Toronto");
                region3.setDistrict("Old Toronto");
                regionRepository.save(region3);
        }
}
