package by.rudko.workout.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "ADDRESSES")
public class Address extends AbstractEntity{
	
	private String address1;
	private String address2;
	private String zip;
}
