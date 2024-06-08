package telran.java52.person.dao;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.java52.person.dto.CityPopulationDto;
import telran.java52.person.model.Child;
import telran.java52.person.model.Employee;
import telran.java52.person.model.Person; 

public interface PersonRepository extends JpaRepository<Person, Integer> {

	Stream<Person> findPersonsByNameIgnoreCase(String name);

	Stream<Person> findPersonsByAddressCityIgnoreCase( String city);

	Stream<Person> findPersonsByBirthDateBetween(LocalDate from, LocalDate to);
	
	@Query("select new telran.java52.person.dto.CityPopulationDto(p.address.city, count(p)) from Person p group by p.address.city order by count(p) desc") // <- JPQL
	CityPopulationDto[]  getCitiesPopulation(String city); 

	
	@Query("select p from Person p where type(p) = ?1")
	Stream<Person> findPersonsByType(Class<? extends Person> type);
	
	Stream<Child> findAllChildrenBy();
	
	Stream<Employee> findBySalaryBetween(Integer from,  Integer to);
	 
	 
}
