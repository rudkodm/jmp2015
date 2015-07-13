package by.rudko.workout.repository;

import org.springframework.data.repository.CrudRepository;

import by.rudko.workout.model.Gym;

public interface GymRepository  extends CrudRepository<Gym, Long> {
}
