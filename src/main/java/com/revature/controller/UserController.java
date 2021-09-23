package com.revature.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.User;
import com.revature.service.UserService;

// We have to tell SPring that this class has the capability of processing web requests at a specific end point

@RestController // RestController is a specfici type of Controller that already assumes you're returning a @ResponseBody
@RequestMapping("/users") // all methods and endpoints exposed at http://localhost:8090/api/users
public class UserController {
	
	// our controller needs to call its dependency which is what our UserService!
	@Autowired
	UserService userService;
	
	
	/**
	 * If someone sends a GET request here: http://localhost:8090/api/users
	 * they retrieve ALL users.
	 */
	
	@GetMapping
	public ResponseEntity<Set<User>> findAll() {
		// ResponseEntity allows us to send back custom HTTP status & headers within the HTTP response
		return ResponseEntity.ok(userService.findAll());
		
	}
	
	// GET request that reads the id from the query parameter
	@GetMapping("/{id}") // if I send a get request to http://localhost:8090/api/users/5, it will capture 5 and search the User table for it
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
		
		// call the service method, pass the captured id through, and returnit as a reposne entity with 200 OK status
		return ResponseEntity.ok(userService.findById(id));
		
	}

	// Create a method that fetches the path variable for finding a user by their username (Get request)
	@GetMapping("/{username}") // if I send a get request to http://localhost:8090/api/users/5, it will capture 5 and search the User table for it
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		
		// call the service method, pass the captured id through, and returning as a response entity with 200 OK status
		return ResponseEntity.ok(userService.findByUsername(username));
	}
	
	@PostMapping("/add")			// The Valid annotation makes sure that the User must compy with the resttrictions we set in the model
	public ResponseEntity<User> addUser(@Valid @RequestBody User u) {  // we're taking in the User object in the HTTP RequestBody so we must 
															  	// use the @RequestBody annotation for the parameter
		return ResponseEntity.ok(userService.insert(u));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
