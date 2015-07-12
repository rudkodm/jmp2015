package by.rudko.workout.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Trainer extends AbstractEntity {

    @OneToMany
    private List<Client> clients;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private PersonalData personalData;
}
