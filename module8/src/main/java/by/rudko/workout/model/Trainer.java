package by.rudko.workout.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "TRAINERS")
public class Trainer extends AbstractEntity {

    @OneToMany(mappedBy="trainer", cascade = {CascadeType.PERSIST})
    private List<Client> clients;

    @Embedded
    private PersonalData personalData;
    
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "gym_id")
    private Gym gym;
}
