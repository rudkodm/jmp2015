package by.rudko.workout.repository;

import org.springframework.data.repository.CrudRepository;

import by.rudko.workout.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long>{
}
