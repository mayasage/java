package org.blacksage.learn.easyschool.repositories;

import org.blacksage.learn.easyschool.models.Holiday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidaysRepository extends CrudRepository<Holiday, Long> {
}
