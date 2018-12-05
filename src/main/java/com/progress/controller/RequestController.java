package com.progress.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.progress.classes.Client;
import com.progress.classes.Request;
import com.progress.classes.Screen;
import com.progress.repository.RequestRepository;

@Controller
@RequestMapping("/request")
public class RequestController {
	
	@Autowired
	private RequestRepository requestRepository;
	
	@GetMapping("/add")
	public String addRequest(@RequestParam String title, @RequestParam String clientDescription, @RequestParam String devDescription, @RequestParam int clientId, @RequestParam int screenId) {
		Request request = new Request();
		request.setRequestTitle(title);
		request.setRequestClientDescription(clientDescription);		
		request.setRequestDevDescription(devDescription);
		request.setRequestEntryDate(LocalDateTime.now());
		request.setStatus("Open");
		request.setRequestClient(new Client(clientId));
		request.setRequestScreen(new Screen(screenId));
		
		requestRepository.save(request);
		
		return "redirect:/client/";
	}
	
	@GetMapping("/update/{id}")
	public String updateRequest(@PathVariable("id") int id, @RequestParam(required=false) String title, @RequestParam(required=false) String clientDescription, @RequestParam(required=false) String devDescription, @RequestParam(required=false) String clientId, @RequestParam(required=false) String screenId) {
		// recupera o request
		Optional<Request> updateRequest = requestRepository.findById(id);		
		
		Request request = updateRequest.get();
		
		if (title != null)
			request.setRequestTitle(title);
		else		
			request.setRequestTitle(request.getRequestTitle());
		
		if (clientDescription != null)
			request.setRequestClientDescription(clientDescription);
		else
			request.setRequestClientDescription(request.getRequestClientDescription());
		
		if (devDescription != null)				
			request.setRequestDevDescription(devDescription);
		else
			request.setRequestDevDescription(request.getRequestDevDescription());
		
		if (clientId != null)		
			request.setRequestClient(new Client(Integer.parseInt(clientId)));
		else
			request.setRequestClient(new Client(request.getRequestClient().getClientId()));
			
		if (screenId != null)	
			request.setRequestScreen(new Screen(Integer.parseInt(screenId)));
		else
			request.setRequestScreen(new Screen(request.getRequestScreen().getScreenId()));
		
		requestRepository.save(request);
		
		return "redirect:/client/";		
	}
	
	@GetMapping("/delete/{id}")
	public String deleteRequest(@PathVariable("id") int id) {
		requestRepository.deleteById(id);
		
		return "redirect:/client/";		
	}
	
	@GetMapping("/all")
	public @ResponseBody Iterable<Request> allRequest() {
		return requestRepository.findAll();
	}
}
