package com.javatechie.docker.compose.dao;

import com.javatechie.docker.compose.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person,String> {
    public List<Person> findByLastName(String lastName);
    public List<Person> findByAgeGreaterThan(int age);
    public List<Person> findByAge(int age);
}
