package telran.java52.person.service;

import java.time.LocalDate;

import org.apache.tomcat.util.collections.ManagedConcurrentWeakHashMap;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java52.person.dao.PersonRepository;
import telran.java52.person.dto.AddresDto;
import telran.java52.person.dto.CityPopulationDto;
import telran.java52.person.dto.PersonDto;
import telran.java52.person.dto.exceptions.PersonNotFoundException;
import telran.java52.person.model.Address;
import telran.java52.person.model.Child;
import telran.java52.person.model.Employee;
import telran.java52.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, CommandLineRunner {
	final PersonRepository personRepository;
	final ModelMapper modelMapper;

	@Transactional
	@Override
	public Boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		personRepository.save(modelMapper.map(personDto, Person.class));
		return true;
	}

	@Transactional(readOnly = true)
	@Override
	public PersonDto findPersonById(Integer personId) {
		Person person = personRepository.findById(personId).orElseThrow(PersonNotFoundException::new);
		return modelMapper.map(person, PersonDto.class);
	}

	@Transactional
	@Override
	public PersonDto updateName(Integer personId, String newName) {
		Person person = personRepository.findById(personId).orElseThrow(PersonNotFoundException::new);
		if (!newName.isEmpty() && !newName.isBlank()) {
			person.setName(newName);
		}
		return modelMapper.map(person, PersonDto.class);
	}

	@Transactional
	@Override
	public PersonDto updateAddress(Integer personId, AddresDto newAddres) {
		Person person = personRepository.findById(personId).orElseThrow(PersonNotFoundException::new);
		if (newAddres.getBuilding() > 0 && !newAddres.getCity().isBlank() && !newAddres.getCity().isEmpty()
				&& !newAddres.getStreet().isBlank() && !newAddres.getStreet().isEmpty()) {
			person.setAddress(modelMapper.map(newAddres, Address.class));
		}
		return modelMapper.map(person, PersonDto.class);
	}

	@Transactional
	@Override
	public PersonDto deletePersonById(Integer personId) {
		Person person = personRepository.findById(personId).orElseThrow(PersonNotFoundException::new);
		personRepository.deleteById(personId);
		return modelMapper.map(person, PersonDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<PersonDto> findPersonsByCity(String city) {
		return personRepository.findPersonsByAddressCityIgnoreCase(city).map(p -> modelMapper.map(p, PersonDto.class))
				.toList();
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<PersonDto> findPersonsByAges(Integer min, Integer max) {
		LocalDate minDate = LocalDate.now().minusYears(max.longValue());
		LocalDate maxDate = LocalDate.now().minusYears(min.longValue());
		return personRepository.findPersonsByBirthDateBetween(minDate, maxDate)
				.map(p -> modelMapper.map(p, PersonDto.class)).toList();
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<PersonDto> findPersonsByName(String name) {
		return personRepository.findPersonsByNameIgnoreCase(name).map(p -> modelMapper.map(p, PersonDto.class))
				.toList();
	}

	@Override
	public Iterable<CityPopulationDto> getCityPopulation(String city) {
		return personRepository.getCitiesPopulation(city);
	}

	@Override
	public void run(String... args) throws Exception {
		if (personRepository.count() == 0) {
			Person person = new Person(1000, "John", LocalDate.of(1985, 3, 11),
					new Address("Tel Aviv", "ben Gvirol", 81));
			Child child = new Child(2000, "Moshe", LocalDate.of(2018, 7, 5), new Address("Ashkelon", "Bar Kohva", 21),
					"Shalom");
			Employee employee = new Employee(3000, "Sarah", LocalDate.of(1995, 11, 23),
					new Address("Rehovot", "Herzl", 7), "Motorola", 20000);
			personRepository.save(person);
			personRepository.save(child);
			personRepository.save(employee);
		}

	}

}