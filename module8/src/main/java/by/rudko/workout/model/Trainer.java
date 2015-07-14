package by.rudko.workout.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
