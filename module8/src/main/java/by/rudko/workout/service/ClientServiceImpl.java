package by.rudko.workout.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import by.rudko.workout.model.Client;

@Service
public class ClientServiceImpl implements ClientService {
	private List<Client> clients = new ArrayList<>();
	
	{
		clients.add(new Client("1", "Client1"));
		clients.add(new Client("2", "Client2"));
		clients.add(new Client("3", "Client3"));
	}
	
	@Override
	public List<Client> getAllClients() {
		return clients;
	}

}
