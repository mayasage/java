package org.blacksage.learn.easyschool.repositories;

import org.blacksage.learn.easyschool.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
