package telran.java52.person.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java52.person.dto.AddresDto;
import telran.java52.person.dto.CityPopulationDto;
import telran.java52.person.dto.PersonDto;
import telran.java52.person.service.PersonService;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
	final PersonService personService;

	@PostMapping
	public boolean addPerson(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);
	}
	
	@GetMapping("/{id}")
	public PersonDto findPersonById(@PathVariable Integer id) {
		return personService.findPersonById(id);
	}

	@PutMapping("/{id}/name/{name}")
	public PersonDto updateName(@PathVariable Integer id, @PathVariable String name) {
		return personService.updateName(id, name);
	}

	@PutMapping("/{id}/address")
	public PersonDto updateAddress(@PathVariable Integer id, @RequestBody AddresDto newAddres) {
		return personService.updateAddress(id, newAddres);
	}

	@DeleteMapping("/{id}")
	public PersonDto deletePersonById(@PathVariable Integer id) {
		return personService.deletePersonById(id);
	}

	@GetMapping("/city/{city}")
	public Iterable<PersonDto> findPersonsByCity(@PathVariable String city) {
		return personService.findPersonsByCity(city);
	}

	@GetMapping("/ages/{minAge}/{maxAge}")
	public Iterable<PersonDto> findPersonsByAges(@PathVariable Integer minAge, @PathVariable Integer maxAge) {
		return personService.findPersonsByAges(minAge, maxAge);
	}

	@GetMapping("/name/{name}")
	public Iterable<PersonDto> findPersonsByName(@PathVariable String name) {
		return personService.findPersonsByName(name);
	}

	@GetMapping("/population/{city}")
	public Iterable<CityPopulationDto> getCityPopulation(@PathVariable String city) {
		return personService.getCityPopulation(city);
	}
}
