package by.rudko.workout.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class PersonalData {
    private String firstName;
    private String lastName;
    private String email;
}
