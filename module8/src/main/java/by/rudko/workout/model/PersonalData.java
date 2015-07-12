package by.rudko.workout.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class PersonalData extends AbstractEntity {
    @Column private String firstName;
    @Column private String lastName;
    @Column private String email;
}
