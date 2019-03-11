package com.progress.controller;

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

import com.progress.model.Screen;
import com.progress.repository.ScreenRepository;

@Controller
@RequestMapping("/screen")
@SessionAttributes("screen")
public class ScreenController {
	
	@Autowired
	private ScreenRepository screenRepository;
	
	@GetMapping("/")
	public String getHomeScreen(Model model) {
		Iterable<Screen> screenList = new ArrayList<>();
		
		screenList = screenRepository.findAll();
		
		model.addAttribute("screen", screenList);
		
		return "screen";
	}
	
	// to do post /screen 
	
	@GetMapping("/form/add")
	public String getAddFormScreen(Model model) {
		Screen screen = new Screen();
		
		model.addAttribute(screen);
		
		return "screen_register";
	}
	
	@PostMapping("/form/add")
	public String postAddFormScreen(HttpServletRequest request, @ModelAttribute("screen") Screen screen) {
		screenRepository.save(screen);
		
		//clean 
		request.getSession().setAttribute("screen", null);
		
		return "redirect:/screen/";		
	}
	
	@GetMapping("/form/update/{id}")
	public String getUpdateFormScreen(@PathVariable("id") int id, Model model) {
		Optional<Screen> updateScreen = screenRepository.findById(id);
	
		Screen screen = updateScreen.get();
		
		model.addAttribute(screen);
		
		return "screen_update";
	}
	
	@PostMapping("/form/update/{id}")
	public String postUpdateFormScreen(@PathVariable("id") int id, HttpServletRequest request, @ModelAttribute("screen") Screen screen) {
		screenRepository.save(screen);
		
		request.getSession().setAttribute("screen", null);
		
		return "redirect:/screen/";		
	}	
	
	
	@GetMapping("/add")
	public String addScreen(@RequestParam String name, @RequestParam String description) {
		Screen screen = new Screen();
		screen.setScreenName(name);
		screen.setScreenDescription(description);
		
		screenRepository.save(screen);
		
		return "redirect:/screen/";
	}
	
	@GetMapping("/update/{id}")
	public String updateScreen(@PathVariable("id") int id, @RequestParam(required=false) String name, @RequestParam(required=false) String description) {
		Optional<Screen> updateScreen = screenRepository.findById(id);
		
		Screen screen = updateScreen.get();		
		
		if (name != null)  
			screen.setScreenName(name);
		else
			screen.setScreenName(screen.getScreenName());
		
		if(description != null)
			screen.setScreenDescription(description);
		else
			screen.setScreenDescription(screen.getScreenDescription());

		screenRepository.save(screen);
		
		return "redirect:/screen/";		
	}
	
	@GetMapping("/form/delete/{id}")
	public String deleteScreen(@PathVariable("id") int id) {
		screenRepository.deleteById(id);
		
		return "redirect:/screen/";
	}
	
	@GetMapping("/api/add") 
	public String addScreenApi(@RequestParam(required=true) String name, @RequestParam(required=true) String description){
		Screen screen = new Screen();
		screen.setScreenName(name);
		screen.setScreenDescription(description);
		
		screenRepository.save(screen);
		
		return "redirect:/screen/";
	}
	
	@GetMapping("/api/update/{id}")
	public void updateScreenApi(@PathVariable("id") int id, @RequestParam String name, @RequestParam String description) {
		Optional<Screen> screen = screenRepository.findById(id);
		
		Screen screenUpdate = screen.get();
		
		if (name != null) {
			screenUpdate.setScreenName(name);
		}
		
		if (description != null) {
			screenUpdate.setScreenDescription(description);	
		}		
		
		screenRepository.save(screenUpdate);
	}
	
	@GetMapping("/api/delete/{id}")
	public void deleteScreenApi(@PathVariable("id") int id) {
		screenRepository.deleteById(id);
	}
	
	
	@GetMapping("/api/all")
	public @ResponseBody Iterable<Screen> allScreen() {
		return screenRepository.findAll();
	}	
}
