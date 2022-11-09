package com.javatechie.docker.compose;

import com.javatechie.docker.compose.dao.PersonRepository;
import com.javatechie.docker.compose.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("/person")
public class SpringbootMongodbDockerApplication {

	@Autowired
	private PersonRepository repository;

	@GetMapping
	public List<Person> findAll() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Person> findById(@RequestParam(name="id",required=false) String id) {
		return repository.findById(id);
	}

	@PostMapping
	public ResponseEntity<Person> add(@RequestBody Person p) {
		p = repository.save(p);
//        return p; and should return Person
		return new ResponseEntity<>(p, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public void delete(@RequestParam(name="id",required=false) String id) {
		repository.deleteById(id);
	}
//@DeleteMapping("/{id}")
//public void delete(@RequestParam("id") Long id) {
//    List<Person> p = persons.stream().filter(it -> it.getId().equals(id)).collect(Collectors.toList());
//    persons.removeAll(p);
//}

	@PutMapping
	public void update(@RequestBody Person p) {
		repository.save(p);
	}

	@GetMapping("/lastname/{lastName}")
	public List<Person> findByLastName(@RequestParam("lastName") String lastName) {
		return repository.findByLastName(lastName);
	}

	@GetMapping("/age/{age}")
	public List<Person> findByAgeGreaterThan(@RequestParam("age") int age) {
		return repository.findByAgeGreaterThan(age);
	}

	@DeleteMapping("/people")
	public ResponseEntity<HttpStatus> deleteAllPeople() {
		//add your code here
		repository.deleteAll();

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@GetMapping("/count")
	public long count() {

		return  repository.count();

	}
	@GetMapping("/Age/{age}")
	public List<Person> findByAge(@RequestParam("age") int age) {
		return repository.findByAge(age);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMongodbDockerApplication.class, args);
	}

}
