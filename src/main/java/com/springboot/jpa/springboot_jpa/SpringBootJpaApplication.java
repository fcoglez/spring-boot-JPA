package com.springboot.jpa.springboot_jpa;

import com.springboot.jpa.springboot_jpa.entities.Person;
import com.springboot.jpa.springboot_jpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringBootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//List<Person> persons = (List<Person>) repository.findAll();
		//List<Person> persons = (List<Person>) repository.findByProgrammingLanguage("Java");
		//List<Person> persons = (List<Person>) repository.buscarAllPersons();
		//List<Person> persons = (List<Person>) repository.buscarPorLenguajeProgramacion("Java");
		//List<Person> persons = (List<Person>) repository.buscarPorLenguajeProgramacionYNombre("Java", "Francisco");
		List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("Java", "Francisco");
		persons.forEach(person -> System.out.println(person));
	}
}
