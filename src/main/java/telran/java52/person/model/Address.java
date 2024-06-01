package telran.java52.person.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable // второй вариант как встроить в таблицу родителя
public class Address implements Serializable {
	private static final long serialVersionUID = -4731041582279975648L;
	String city;
	String street;
	Integer building;
}
