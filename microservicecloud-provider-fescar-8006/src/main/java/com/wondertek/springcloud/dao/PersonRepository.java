package com.wondertek.springcloud.dao;

import com.wondertek.springcloud.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {

}
