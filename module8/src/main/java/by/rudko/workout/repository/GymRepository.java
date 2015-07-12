package by.rudko.workout.repository;

import by.rudko.workout.model.Gym;
import org.springframework.data.repository.CrudRepository;

public interface GymRepository  extends CrudRepository<Gym, Long> {
}
