package by.rudko.workout.repository;

import org.springframework.data.repository.CrudRepository;

import by.rudko.workout.model.Trainer;

public interface TrainerRepository extends CrudRepository<Trainer, Long>{
}
