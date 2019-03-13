package com.progress.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.progress.model.Client;
import com.progress.model.Request;
import com.progress.model.Screen;
import com.progress.repository.ClientRepository;
import com.progress.repository.RequestRepository;
import com.progress.repository.ScreenRepository;

@Controller
@RequestMapping("/request")
@SessionAttributes("request")
public class RequestController {
	
	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired	
	private ScreenRepository screenRepository;
	
	@GetMapping("/") 
	public String getHomeRequest(Model model) {
		Iterable<Request> requestList = new ArrayList<>();
		
		requestList = requestRepository.findAll();
		
		model.addAttribute("request", requestList);
		
		return "request";
	}
	
	@GetMapping("/form/add")
	public String getAddFormRequest(Model model) {
		Request request = new Request();
		
		Iterable<Client> clientList = new ArrayList<>();
		Iterable<Screen> screenList = new ArrayList<>();
		
		clientList = clientRepository.findAll();
		screenList = screenRepository.findAll();
		
		request.setStatus("Open");
		request.setRequestEntryDate(LocalDateTime.now());
		
		model.addAttribute(request);
		model.addAttribute("client", clientList);
		model.addAttribute("screen", screenList);
		
		return "request_register";
	}
	
	@PostMapping("/form/add")
	public String postAddFormRequest(HttpServletRequest serveletRequest, @ModelAttribute("request") Request request) {
		requestRepository.save(request);
		
		// clean attr
		serveletRequest.getSession().setAttribute("request", null);
		
		return "redirect:/request/";
	}
	
	@GetMapping("/form/update/{id}")
	public String getUpdateFormRequest(@PathVariable("id") int id, Model model) {
		Optional<Request> updateRequest = requestRepository.findById(id);
		Iterable<Client> clientList = new ArrayList<>();
		Iterable<Screen> screenList = new ArrayList<>();
		
		Request request = updateRequest.get();
		clientList = clientRepository.findAll();
		screenList = screenRepository.findAll();		
		
		model.addAttribute(request);
		model.addAttribute("client", clientList);
		model.addAttribute("screen", screenList);		
		
		return "request_update";
	}
	
	@PostMapping("/form/update/{id}")
	public String postFormUpdateRequest(@PathVariable("id") int id, HttpServletRequest serveletRequest, @ModelAttribute("request") Request request) {
		requestRepository.save(request);
		
		serveletRequest.getSession().setAttribute("request", null);
		
		return "redirect:/request/"; 		
	}
	
	@GetMapping("/form/delete/{id}")
	public String deleteFormRequest(@PathVariable("id") int id) {
		requestRepository.deleteById(id);
		
		return "redirect:/request/";
	}
	
	@GetMapping("/api/add")
	public String addApiRequest(@RequestParam String title, @RequestParam String clientDescription, @RequestParam String devDescription, @RequestParam int clientId, @RequestParam int screenId) {
		Request request = new Request();
		request.setRequestTitle(title);
		request.setRequestClientDescription(clientDescription);		
		request.setRequestDevDescription(devDescription);
		request.setRequestEntryDate(LocalDateTime.now());
		request.setStatus("Open");
		request.setRequestClient(new Client(clientId));
		request.setRequestScreen(new Screen(screenId));
		
		requestRepository.save(request);
		
		return "redirect:/request/api/all";
	}
	
	@GetMapping("/api/update/{id}")
	public String updateApiRequest(@PathVariable("id") int id, @RequestParam(required=false) String title, @RequestParam(required=false) String clientDescription, @RequestParam(required=false) String devDescription, @RequestParam(required=false) String clientId, @RequestParam(required=false) String screenId) {
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
		
		return "redirect:/request/api/findById/" + id;
	}	
	
	@GetMapping("/api/delete/{id}")
	public String deleteRequest(@PathVariable("id") int id) {
		requestRepository.deleteById(id);
		
		return "redirect:/request/api/all";	
	}
	
	@GetMapping("/api/findById/{id}")
	public @ResponseBody Optional<Request> findById(@PathVariable("id") int id) {
		return requestRepository.findById(id);
	}
	
	@GetMapping("/api/all")
	public @ResponseBody Iterable<Request> allRequest() {
		return requestRepository.findAll();
	}
	
	@GetMapping("/api/countByStatus")
	public @ResponseBody Iterable<Long> countByStatus(@RequestParam("status") String status) {
		return requestRepository.countByStatus(status);
	}

	@GetMapping("/api/totalCount") 
	public @ResponseBody Iterable<Long> totalCount() {
		return requestRepository.totalCount();
	}
	
	@GetMapping("/api/findByStatus")
	public @ResponseBody Iterable<Request> findByStatus(@RequestParam("status") String status) {			
		return requestRepository.findByStatus(status);		
	}
	
	@GetMapping("/api/countByMonth")
	public @ResponseBody Iterable<Integer> countByMonth(@RequestParam("month") int month, @RequestParam("year") int year) {
		return requestRepository.countByMonth(month, year);
	}
}
