package by.rudko.workout.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Gym extends AbstractEntity{

    @OneToMany
    private List<Trainer> trainers;
}
