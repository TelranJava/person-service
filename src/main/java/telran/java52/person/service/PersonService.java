package telran.java52.person.service;

import java.util.List;

import telran.java52.person.dto.AddressDto;
import telran.java52.person.dto.CityPopulationDto;
import telran.java52.person.dto.PersonDto;

public interface PersonService {
	Boolean addPerson(PersonDto personDto);

	PersonDto findPersonById(Integer personId);

	PersonDto updateName(Integer personId, String name);

	PersonDto updateAddress(Integer personId, AddressDto newAddres);

	PersonDto deletePersonById(Integer personId);

	PersonDto[] findPersonsByCity(String city);

	PersonDto[] findPersonsByAges(Integer from, Integer to);

	PersonDto[]  findPersonsByName(String name);

	List<CityPopulationDto> getCityPopulation(String city);
	
	PersonDto[] findAllChildren();
	
	PersonDto[] findEmployeesBySalary(Integer from, Integer to);
}
