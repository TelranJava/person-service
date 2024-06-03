package telran.java52.person.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java52.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	List<Person> findPersonsByAddressCityIgnoreCase(String city);

//	@Query("SELECT p FROM Person p WHERE p.birthDate BETWEEN :maxAge AND :minAge")
//	List<Person> findPersonsByBirthDateBetween(@Param("minAge") LocalDate minAge, @Param("maxAge") LocalDate maxAge);
	List<Person> findPersonsByBirthDateBetween (LocalDate from, LocalDate to);

	List<Person> findPersonsByNameIgnoreCase(String name);
}
