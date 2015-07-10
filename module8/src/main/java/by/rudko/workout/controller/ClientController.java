package by.rudko.workout.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.rudko.workout.model.Client;
import by.rudko.workout.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;
	

	@RequestMapping(value = "", method = RequestMethod.GET )
	public List<Client> getAllClients(){
		return clientService.getAllClients();
	}
}
