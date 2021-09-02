package com.ivoronline.springboot_db_query_jpql_create.services;

import com.ivoronline.springboot_db_query_jpql_create.entities.Person;
import com.ivoronline.springboot_db_query_jpql_create.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class DBAccess {

  @Autowired          PersonRepository personRepository;
  @PersistenceContext EntityManager    entityManager;

  //================================================================
  // SELECT PERSON BY NAME AGE
  //================================================================
  public Person selectPersonByNameAge() {

    //CREATE QUERY
    String select = "SELECT john FROM Person john WHERE john.name = :name AND john.age = :age";
    Query  query  = entityManager.createQuery(select, Person.class);
           query.setParameter("name", "John");
           query.setParameter("age" , 20);

    //SELECT PERSON
    Person person = (Person) query.getSingleResult();

    //RETURN PERSON
    return person;

  }

  //================================================================
  // INSERT PERSON
  //================================================================
  @Transactional
  public void insertPerson() {
    Person person = personRepository.save(new Person("John" , 20));
  }

  //================================================================
  // UPDATE PERSON
  //================================================================
  @Transactional
  public Integer updatePerson() {

    //CREATE QUERY
    String update = "UPDATE Person person SET person.age = :newAge WHERE person.name = :name";
    Query  query  = entityManager.createQuery(update);
           query.setParameter("name"  , "John");
           query.setParameter("newAge", 200);

    //INSERT PERSON
    Integer updatedRecords = query.executeUpdate();

    //RETURN NUMBER OF INSERTED RECORDS
    return updatedRecords;

  }

  //================================================================
  // DELETE PERSON
  //================================================================
  @Transactional
  public Integer deletePerson() {

    //CREATE QUERY
    String delete = "DELETE FROM Person person WHERE person.name = :name";
    Query  query  = entityManager.createQuery(delete);
           query.setParameter("name"  , "John");

    //INSERT PERSON
    Integer deletedRecords = query.executeUpdate();

    //RETURN NUMBER OF INSERTED RECORDS
    return deletedRecords;

  }

}
