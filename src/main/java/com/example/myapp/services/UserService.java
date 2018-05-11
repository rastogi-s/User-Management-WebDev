package com.example.myapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.model.User;
import com.example.myapp.repositories.UserRepository;

@RestController
public class UserService {

	@Autowired
	UserRepository repository;

	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		
		return repository.save(user);
		
	}

	@GetMapping("/api/user")
	public Iterable<User> findAllUsers() {
		return repository.findAll();
	}

	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int id) {
		Optional<User> op = repository.findById(id);
		User user=null;
		if(op.isPresent())
			user = op.get();
		return user;
	}

	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int id, @RequestBody User user) {
		User temp = findUserById(id);
		temp.setFirstName(user.getFirstName());
		temp.setLastName(user.getLastName());
		temp.setPassword(user.getPassword());
		temp.setUsername(user.getUsername());

		return repository.save(temp);
	}

	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repository.deleteById(id);
	}

}
