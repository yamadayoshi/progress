package com.progress.controller;

import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.progress.model.Client;
import com.progress.repository.ClientRepository;

@Controller
@RequestMapping("/client")
@SessionAttributes("client")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@GetMapping("/")
	public String getHomeClient(Model model) {
		Iterable<Client> clientList = new ArrayList<>();
		
		clientList = clientRepository.findAll();
				
		model.addAttribute("client", clientList);
		
		return "client";
	}
	
	@PostMapping(value="/")
	public String postHomeClient(@Valid Client client, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			clientRepository.save(client);
			
			return "redirect:/client/";
		} else
			return "client";
	}
	
	@GetMapping("/form/add")
	public String getAddFormClient(Model model) {
		Client client = new Client();
		
		model.addAttribute(client);		
		
		return "client_register";
	}
	
	@PostMapping("/form/add")
	public String postAddFormClient(HttpServletRequest request, @ModelAttribute("client") Client client) {
		clientRepository.save(client);
		
		request.getSession().setAttribute("client", null);
		
		return "redirect:/client/";
	}
	
	@GetMapping("/form/update/{id}")
	public String getUpdateFormClient(@PathVariable("id") int id, Model model) {
		Optional<Client> updateClient = clientRepository.findById(id);		
		
		Client client = updateClient.get();
		
		model.addAttribute(client);
		
		return "client_update";
	}
	
	@PostMapping("/form/update/{id}")
	public String postUpdateFormClient(@PathVariable("id") int id, HttpServletRequest request, @ModelAttribute("client") Client client) {		
		clientRepository.save(client);
		
		// limpa o atributo model
		request.getSession().setAttribute("client", null);		
		
		return "redirect:/client/";
	}
	
	@GetMapping("/api/add")
	public String addClient(@RequestParam String name, @RequestParam String cpf, @RequestParam String email, @RequestParam String phone) {
		Client client = new Client();
		client.setClientName(name);
		client.setClientCnpjCpf(cpf);
		client.setClientEmail(email);
		client.setClientPhone(phone);
		
		clientRepository.save(client);
		
		return "redirect:/client/";
	}
	
	@GetMapping("/api/update/{id}") 
	public String updateClient(@PathVariable("id") int id,  @RequestParam(required=false) String name, @RequestParam(required=false) String cpf, @RequestParam(required=false) String email, @RequestParam(required=false) String phone) {
		Optional<Client> updateClient = clientRepository.findById(id);		
		
		Client client = updateClient.get();
		
		if (name != null)
			client.setClientName(name);
		else
			client.setClientName(client.getClientName());
		
		if (cpf != null) 
			client.setClientCnpjCpf(cpf);
		else
			client.setClientCnpjCpf(client.getClientCnpjCpf());
		
		if (email != null)
			client.setClientEmail(email);
		else
			client.setClientEmail(client.getClientEmail());
		
		if (phone != null)		
			client.setClientPhone(phone);
		else
			client.setClientPhone(client.getClientPhone());		
		
		clientRepository.save(client);
		
		return "redirect:/client/";		
	}
	
	@GetMapping("/form/delete/{id}")
	public String removeClient(@PathVariable("id") int id) {
		clientRepository.deleteById(id);
		
		return "redirect:/client/";
	}
	
	@GetMapping("/api/all")
	public @ResponseBody Iterable<Client> allClient() {
	
		return clientRepository.findAll();
	}
	
	@GetMapping("/api/totalCount")
	public @ResponseBody Iterable<Long> totalCount() {
		return clientRepository.totalCount();
	}
	
	@GetMapping("/api/findByClientName")
	public @ResponseBody Iterable<Client> findByClientName(@RequestParam String name) {
		return clientRepository.findByClientName(name);
	}
}
