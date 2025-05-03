//package com.catalin.javapersistence.repositories.projections;
//
//import org.springframework.beans.factory.annotation.Value;
//
//public class UserProjection {
//
//        public interface UserActivityCounts {
//                Long getActiveUserCount();
//
//                Long getInactiveUserCount();
//
//                @Value("#{target.activeUserCount + target.inactiveUserCount}")
//                Long getTotalUserCount();
//
//                default String stringify() {
//                        return "activeUserCount=" + getActiveUserCount() + ", inactiveUserCount=" + getInactiveUserCount() + ", totalUserCount=" + getTotalUserCount();
//                }
//        }
//
//        public interface UsernameOnly {
//                String getUsername();
//        }
//}
