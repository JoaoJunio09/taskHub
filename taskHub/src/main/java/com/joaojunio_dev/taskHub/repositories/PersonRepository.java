package com.joaojunio_dev.taskHub.repositories;

import com.joaojunio_dev.taskHub.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
