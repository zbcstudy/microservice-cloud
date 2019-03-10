package com.wondertek.springcloud.service;

import com.wondertek.springcloud.entities.Person;
import org.mengyun.tcctransaction.api.Compensable;

import java.util.Map;

/**
 * @Author zbc
 * @Date 21:16-2019/1/23
 */
public interface PersonService {

    @Compensable
    Map<String, Object> savePerson(Person person);

    Map<String, Object> confirmSavePerson(Person person);

    void cancelSavePerson(Person person);
}
