package com.example.myapp.services;

import java.util.Iterator;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int id) {
		Optional<User> op = repository.findById(id);
		User user = null;
		if (op.isPresent())
			user = op.get();
		return user;
	}

	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int id, @RequestBody User user) {
		User temp = findUserById(id);
		temp.set(user);
		return repository.save(temp);
	}

	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repository.deleteById(id);
	}

	@GetMapping("/api/user")
	public Iterable<User> findAllUsers(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password) {

		if (username != null && password != null) {
			return repository.findUserByCredentials(username, password);
		}
		if (username != null) {
			return repository.findUserByUsername(username);
		}
		return repository.findAll();
	}

	@PostMapping("/api/register")
	public String registerUser(@RequestBody User user) {
		Iterable<User> users = findAllUsers(user.getUsername(), null);
		if (!users.iterator().hasNext()) {
			repository.save(user);
			return "SUCCESS";
		}
		return "FAIL";
	}

	@PostMapping("/api/login")
	public User login(@RequestBody User user) {
		Iterable<User> users = findAllUsers(user.getUsername(), user.getPassword());
		Iterator<User> it = users.iterator();
		if (it.hasNext()) {
			User u = it.next();
			return u;
		}
		return null;
	}
	
	

	
	
	
	
	// session management functions commented out....
	
//	@PostMapping("/api/logout")
//	public void logout(HttpSession session) {
//		session.invalidate();
//		
//	}
//	
//	@PostMapping("/api/login")
//	public User login(@RequestBody User user, HttpSession session) {
//		Iterable<User> users = findAllUsers(user.getUsername(), user.getPassword());
//		Iterator<User> it = users.iterator();
//		if (it.hasNext()) {
//			User u = it.next();
//			session.setAttribute("loggedUser", u);
//			return u;
//		}
//		return null;
//	}
//	
//	@GetMapping("/api/logged")
//	public User findLoggedUser(HttpSession session) {
//		User user = null;
//		if (session.getAttribute("loggedUser")!=null) {
//			
//			user=(User) session.getAttribute("loggedUser");
//		}
//		
//		System.out.println(user);
//		return user;
//	}
	
}