package com.example.pgapi.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pgapi.model.Address;
import com.example.pgapi.repository.PostgresRepository;

@RestController
public class PgController {
	@Autowired
	PostgresRepository postgresRepository;
	
	@GetMapping("/get-all-addresses")
	public List<Address> getAllAddresses() {
		return postgresRepository.findAll();
	}
	
	@GetMapping("get-address/{id}")
	public Address getAddress(@PathVariable("id") Integer id) {
		return postgresRepository.findById(id).get();
	}
	
	@DeleteMapping("delete-address/{id}")
	public boolean deleteAddress(@PathVariable("id") Integer id) {
		//delete if id exists in repository
		if(!postgresRepository.findById(id).equals(Optional.empty())) {
			postgresRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	@PutMapping("update-address/{id}")
	public Address updateAddress(@RequestBody Map<String, String> body, @PathVariable("id") Integer id) {
		Address current = postgresRepository.findById(id).get();
		current.setNumber(Integer.parseInt(body.get("number")));
		current.setStreet(body.get("street"));
		current.setPostcode(body.get("postcode"));
		postgresRepository.save(current);
		
		return current;
	}
	
	@PostMapping("add-address")
	public Address addAddress(@RequestBody Map<String, String> body) {
		Integer number = Integer.parseInt(body.get("number"));
		String street = body.get("street");
		String postcode = body.get("postcode");
		Address newAddress = new Address(number, street, postcode);
		postgresRepository.save(newAddress);
		
		return newAddress;
		
	}
}
