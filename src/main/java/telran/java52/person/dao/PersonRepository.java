package telran.java52.person.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.java52.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	public List<Person> findPersonsByAddressCity(String city);
	
	@Query("SELECT p FROM Person p WHERE p.birthDate BETWEEN :maxAge AND :minAge")
	public List<Person> findPersonsByBirthDateBetween(@Param("minAge") LocalDate minAge, @Param("maxAge") LocalDate maxAge);

	public List<Person> findPersonsByName (String name);
}
