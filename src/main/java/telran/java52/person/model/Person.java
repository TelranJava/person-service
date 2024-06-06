package telran.java52.person.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Entity
@Inheritance(strategy = InheritanceType.JOINED )
// SINGLE_TABLE - одна общая таблица для всех - минус часть полей null
// TABLE_PER_CLASS - отдельная таблица для каждого класса - минус больше селектов придется делать, потому что в каждую таблицу нужно писать свой
// JOINED - one to many - минус потеря производительности изза множества join
public class Person implements Serializable{
	private static final long serialVersionUID = -4035220012537609590L;
	@Id 
	Integer id;
	@Setter
	String name;
	LocalDate birthDate;
	@Setter
	Address address;
}
//если используется наследование то в PersonController, PersonService и тд лучше использовать массивы 
// если возвращается множество потому что jackson парсит некорректно все List Iterable и тд