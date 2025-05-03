package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.repositories.test.A1Repository;
import com.catalin.javapersistence.repositories.test.A2Repository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
public class a1a2Test {

        @Autowired
        private A1Repository a1Repository;

        @Autowired
        private A2Repository a2Repository;

        @Test
        public void test() {
                A1 a11 = new A1();
                A1 a12 = new A1();
                A2 a21 = new A2();
                A2 a22 = new A2();

//                a11.getA2().add(a21);
//                a11.getA2().add(a22);
//                a12.getA2().add(a21);
//                a12.getA2().add(a22);
//                a1Repository.save(a11);
//                a1Repository.save(a12);

                a21.getA1().add(a11);
                a11.getA2().add(a21);

                a21.getA1().add(a12);
                a12.getA2().add(a21);

                a22.getA1().add(a11);
                a11.getA2().add(a22);

                a22.getA1().add(a12);
                a12.getA2().add(a22);

                a2Repository.save(a21);
//                a2Repository.save(a22);
        }
}
