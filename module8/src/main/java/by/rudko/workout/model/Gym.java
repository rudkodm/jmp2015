package by.rudko.workout.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "GYMS")
public class Gym extends AbstractEntity {

    @OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private Address address;

    @OneToMany(mappedBy = "gym", cascade = { CascadeType.PERSIST })
    private List<Trainer> trainers;
}
