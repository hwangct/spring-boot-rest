package com.example.demo.controller;

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

import com.example.demo.entity.Address;
import com.example.demo.repository.MySqlRepository;

@RestController
public class DemoController {

	@Autowired
	MySqlRepository mySqlRepository;
	
	@GetMapping("/get-all-addresses")
	public List<Address> getAllAddresses() {
		return mySqlRepository.findAll();
	}
	
	@GetMapping("get-address/{id}") 
	public Address getAddress(@PathVariable("id") Integer id) {
		return mySqlRepository.findById(id).get();
	}
	
	@DeleteMapping("delete-address/{id}")
	public boolean deleteAddress(@PathVariable("id") Integer id) {
		// delete if id exists in repository
		if(!mySqlRepository.findById(id).equals(Optional.empty())) {
			mySqlRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	@PutMapping("update-address/{id}")
	public Address updateAddress(@RequestBody Map<String, String> body, @PathVariable("id") Integer id) {
		Address current = mySqlRepository.findById(id).get();
		current.setNumber(Integer.parseInt(body.get("number")));
		current.setStreet(body.get("street"));
		current.setPostcode(body.get("postcode"));
		mySqlRepository.save(current);
		
		return current;
	}
	
	@PostMapping("add-address")
	public Address addAddress(@RequestBody Map<String, String> body) {
		Integer number = Integer.parseInt(body.get("number")); 
		String street = body.get("street");
		String postcode = body.get("postcode");
		Address newAddress = new Address(number, street, postcode);
		mySqlRepository.save(newAddress);
		return newAddress;
	}
}
