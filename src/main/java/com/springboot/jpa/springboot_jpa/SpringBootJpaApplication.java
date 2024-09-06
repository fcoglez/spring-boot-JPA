package com.springboot.jpa.springboot_jpa;

import com.springboot.jpa.springboot_jpa.entities.Person;
import com.springboot.jpa.springboot_jpa.repositories.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringBootJpaApplication implements CommandLineRunner {

	@Autowired
	private IPersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.findOne();
	}

	//Formas de trabajar con un Optional<>
	public void findOne(){
		//1º
		//orElseThrow en este caso, si no existe una persona, devuelve una exception
		//Person person = repository.findById(1L).orElseThrow();

		//2º
		//Person person = null;
		//Optional<Person> optionalPerson = repository.findById(1L);

		//if (optionalPerson.isPresent()){
			//person = optionalPerson.get();
			//System.out.println(person);
		//}

		//3º
		//repository.findById(1L).ifPresent(person -> System.out.println(person));

		//4º Probando metodos
		//repository.findOneName("Francisco").ifPresent(System.out::println);
		//repository.findOneLikeName("Franc").ifPresent(System.out::println);
		//repository.findByName("Francisco").ifPresent(System.out::println);
		repository.findByNameContaining("Franc").ifPresent(System.out::println);

	}

	public void list(){
		//List<Person> persons = (List<Person>) repository.findAll();
		//List<Person> persons = (List<Person>) repository.findByProgrammingLanguage("Java");
		//List<Person> persons = (List<Person>) repository.buscarAllPersons();
		//List<Person> persons = (List<Person>) repository.buscarPorLenguajeProgramacion("Java");
		//List<Person> persons = (List<Person>) repository.buscarPorLenguajeProgramacionYNombre("Java", "Francisco");
		//List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("Java", "Francisco");
		//List<Object[]> persons =  repository.obtenerValoresPersona("Java");
		List<Object[]> persons =  repository.obtenerValoresPersona();

		persons.forEach(person -> System.out.println(person[0] + " es experto en " + person[1]));
	}
}
