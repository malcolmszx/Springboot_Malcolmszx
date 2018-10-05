package com.kingdee.abc.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kingdee.abc.annotation.Encrypt;
import com.kingdee.abc.model.Man;
import com.kingdee.abc.repository.ManRepository;

@RestController
@RequestMapping("/man")
public class ManRestController {
	
	@Autowired private ManRepository manRepository;
	
	@GetMapping("/list")
	@Encrypt
	public List<Man> getList(){
		return manRepository.findAll();
	}
	
	@PostMapping("/add")
	public Man addMan(@RequestBody Man man) {
		return manRepository.save(man);
	}
	
	@GetMapping("/getOne/{id}")
	public Man getOne(@PathVariable("id") Integer id) {
		Optional<Man> man = manRepository.findById(id);
		return man.get(); 
	}
	
	
}
