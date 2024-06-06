package telran.java52.person.service;

import telran.java52.person.dto.AddresDto;
import telran.java52.person.dto.CityPopulationDto;
import telran.java52.person.dto.PersonDto;

public interface PersonService {
	Boolean addPerson(PersonDto personDto);

	PersonDto findPersonById(Integer personId);

	PersonDto updateName(Integer personId, String name);

	PersonDto updateAddress(Integer personId, AddresDto newAddres);

	PersonDto deletePersonById(Integer personId);

	PersonDto[] findPersonsByCity(String city);

	PersonDto[] findPersonsByAges(Integer from, Integer to);

	PersonDto[]  findPersonsByName(String name);

	CityPopulationDto[] getCityPopulation(String city);
}
