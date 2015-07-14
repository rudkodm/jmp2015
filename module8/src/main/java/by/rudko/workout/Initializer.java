package by.rudko.workout;

import by.rudko.workout.model.*;
import by.rudko.workout.repository.ClientRepository;
import by.rudko.workout.repository.GymRepository;
import by.rudko.workout.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Initializer {

    private List<Client> clients = new ArrayList<Client>();
    {
        clients.add(createClient(withPersonalData("Dima", "Rudko", "rudko.d.v@gmail.com"), TrainingGoalType.HEALTH));
        clients.add(createClient(withPersonalData("Pasha", "Zaitsau", "zaitsau@gmail.com")));
        clients.add(createClient(withPersonalData("Sasha", "Beliy", "beliy@gmail.com")));
        clients.add(createClient(withPersonalData("Dima", "Popok", "popok@gmail.com")));
        clients.add(createClient(withPersonalData("Lyosha", "Yakimchuk", "yakimchuk@gmail.com")));
    }

    private List<Trainer> trainers = new ArrayList<Trainer>();
    {
        trainers.add(createTrainer(withPersonalData("Arnold", "Schwarzenegger", null)));
    }

    private List<Gym> gyms = new ArrayList<Gym>();
    {
        gyms.add(createGym(withAddress("Kuprevicha 1/1", "220120")));
    }

    @Autowired
    public void init(ClientRepository clientR, TrainerRepository trainerR, GymRepository gymR) {
        clientR.save(clients);
        trainerR.save(trainers);
        gymR.save(gyms);
    }

    private Gym createGym(Address address) {
        Gym gym = new Gym();
        gym.setTrainers(trainers);
        gym.setAddress(address);
        for (Trainer trainer : trainers) {
            trainer.setGym(gym);
        }
        return gym;
    }

    private Trainer createTrainer(PersonalData personalData) {
        Trainer trainer = new Trainer();
        trainer.setPersonalData(personalData);
        trainer.setClients(clients);
        for (Client client : clients) {
            client.setTrainer(trainer);
        }
        return trainer;
    }

    private Client createClient(PersonalData personalData) {
        Client client = new Client();
        client.setPersonalData(personalData);
        return client;
    }

    private Client createClient(PersonalData personalData, TrainingGoalType trainingGoal) {
        Client client = new Client();
        client.setPersonalData(personalData);
        client.setTrainingGoal(trainingGoal);
        return client;
    }

    private PersonalData withPersonalData(String firstName, String lastName, String email) {
        PersonalData result = new PersonalData();
        result.setFirstName(firstName);
        result.setLastName(lastName);
        result.setEmail(email);
        return result;
    }

    private Address withAddress(String address, String zip) {
        Address result = new Address();
        result.setAddress1(address);
        result.setZip(zip);
        return result;
    }

}
