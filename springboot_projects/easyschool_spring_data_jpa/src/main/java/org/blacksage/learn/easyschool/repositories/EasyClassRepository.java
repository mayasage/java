package org.blacksage.learn.easyschool.repositories;

import org.blacksage.learn.easyschool.models.EasyClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EasyClassRepository extends JpaRepository<EasyClass, Long> {
}
