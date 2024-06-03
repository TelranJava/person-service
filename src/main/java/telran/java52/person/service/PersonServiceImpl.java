package telran.java52.person.service;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java52.person.dao.PersonRepository;
import telran.java52.person.dto.AddresDto;
import telran.java52.person.dto.CityPopulationDto;
import telran.java52.person.dto.PersonDto;
import telran.java52.person.dto.exceptions.PersonNotFoundException;
import telran.java52.person.model.Address;
import telran.java52.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
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
			personRepository.save(person);
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
			personRepository.save(person);
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

	@Override
	public Iterable<PersonDto> findPersonsByCity(String city) {
		return personRepository.findPersonsByAddressCityIgnoreCase(city).stream()
				.map(p -> modelMapper.map(p, PersonDto.class)).toList();
	}

	@Override
	public Iterable<PersonDto> findPersonsByAges(Integer from, Integer to) {
		LocalDate minDate = LocalDate.now().minusYears(to.longValue());
		LocalDate maxDate = LocalDate.now().minusYears(from.longValue());
		return personRepository.findPersonsByBirthDateBetween(minDate, maxDate).stream()
				.map(p -> modelMapper.map(p, PersonDto.class)).toList();
	}

	@Override
	public Iterable<PersonDto> findPersonsByName(String name) {
		return personRepository.findPersonsByNameIgnoreCase(name).stream().map(p -> modelMapper.map(p, PersonDto.class))
				.toList();
	}

	@Override
	public Iterable<CityPopulationDto> getCityPopulation(String city) {
		// TODO Auto-generated method stub
		return null;
	}

}