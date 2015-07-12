package by.rudko.workout;

import by.rudko.workout.model.Client;
import by.rudko.workout.model.Gym;
import by.rudko.workout.model.PersonalData;
import by.rudko.workout.model.Trainer;
import by.rudko.workout.repository.ClientRepository;
import by.rudko.workout.repository.GymRepository;
import by.rudko.workout.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudkodm on 7/11/15.
 */
@Component
public class Initializer {
    private List<Client> clients = new ArrayList<Client>();
    {
        clients.add(createClient(withPersonalData("Dima", "Rudko", "rudko.d.v@gmail.com")));
        clients.add(createClient(withPersonalData("Pasha", "Zaitsau", "zaitsau@gmail.com")));
        clients.add(createClient(withPersonalData("Sasha", "Beliy", "beliy@gmail.com")));
        clients.add(createClient(withPersonalData("Dima", "Popok", "popok@gmail.com")));
        clients.add(createClient(withPersonalData("Lyosha", "Yakimchuk", "yakimchuk@gmail.com")));
    }

    private List<Trainer> trainers = new ArrayList<Trainer>();
    {
        trainers.add(createTrainer(withPersonalData("Arnold", "Schwarzenegger", null)));
    }

    @Autowired
    public void init(ClientRepository clientR, TrainerRepository trainerR, GymRepository gymR){
        clientR.save(clients);
        trainerR.save(trainers);
        gymR.save(createGym());
    }

    private Gym createGym() {
        Gym gym = new Gym();
        gym.setTrainers(trainers);
        return gym;
    }

    private Trainer createTrainer(PersonalData personalData) {
        Trainer trainer = new Trainer();
        trainer.setPersonalData(personalData);
        trainer.setClients(clients);
        return trainer;
    }

    private Client createClient(PersonalData personalData) {
        Client client = new Client();
        client.setPersonalData(personalData);
        return client;
    }

    private PersonalData withPersonalData(String firstName, String lastName, String email) {
        PersonalData data = new PersonalData();
        data.setFirstName(firstName);
        data.setLastName(lastName);
        data.setEmail(email);
        return data;
    }

}
