package telran.java52.person.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Entity // это будет сущность таблицы
public class Person implements Serializable{
	private static final long serialVersionUID = -4035220012537609590L;
	@Id // тут она обязательна для SQL primary key
	Integer id;
	@Setter
	String name;
	LocalDate birthDate;
	@Setter
//	@Embedded // встроить адрес а эту таблицу
	Address address;
}
