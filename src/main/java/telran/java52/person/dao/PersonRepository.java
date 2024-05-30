package telran.java52.person.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java52.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
