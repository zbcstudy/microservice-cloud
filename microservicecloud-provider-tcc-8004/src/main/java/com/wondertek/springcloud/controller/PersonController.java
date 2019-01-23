package com.wondertek.springcloud.controller;

import com.wondertek.springcloud.entities.Person;
import com.wondertek.springcloud.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author zbc
 * @Date 21:14-2019/1/23
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    PersonService personService;

    @RequestMapping("/add")
    public Map<String, Object> addPerson() {
        log.info("PersonController---开始保存person");
        Person person = new Person();
        person.setId(2l);
        person.setName("person02");
        person.setAddress("person-address02");
        person.setStatus(0);
        personService.savePerson(person);
        return null;
    }
}
