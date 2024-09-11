package com.springboot.jpa.springboot_jpa;

import com.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.springboot.jpa.springboot_jpa.entities.Person;
import com.springboot.jpa.springboot_jpa.repositories.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class SpringBootJpaApplication implements CommandLineRunner {

	@Autowired
	private IPersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.personalizeQueryBetween();
	}

	@Transactional(readOnly = true)
	public void personalizeQueryBetween() {
		System.out.println("Consultas entre parametros usando between");

		//List<Person> betweenId = repository.findAllBetweenId();
		//List<Person> betweenName = repository.findAllBetweenName("E", "G");
		List<Person> betweenId = repository.findByIdBetween(2L, 5L);
		List<Person> betweenName = repository.findByNameBetween("E", "G");
		List<Person> betweenNameOrderBy = repository.findAllBetweenNameAndOderBy();
		List<Person> betweenIdOrderBy = repository.findByIdBetweenOrderByNameDescSurnameAsc(2L, 5L);


		betweenIdOrderBy.forEach(System.out::println);
	}


	@Transactional(readOnly = true)
	public void personalizeQueryConcatUpperAndLowerCase() {
		System.out.println("Consultas con los nombres y apellidos de las personas");

		List<String> names = repository.findAllFullNameConcat();
		List<String> namesUpper = repository.findAllFullNameConcatUpper();
		List<String> namesLower = repository.findAllFullNameConcatLower();
		List<Object[]> personCase = repository.findAllPersonDataCase();

		//namesLower.forEach(System.out::println);

		personCase.forEach(person -> System.out.println("id " + person[0] + " , nombre " + person[1] + " , apellido " + person[2] + " , lenguaje de programacion " + person[3]));
	}


	@Transactional(readOnly = true)
	public void personalizeQueryDistinct() {
		System.out.println("Consultas con los nombres de las personas");

		//List<String> personsName = repository.findAllNames();
		//List<String> personsName = repository.findAllNamesDistinct();
		List<String> personsProgrammingLanguague = repository.findAllProgrammingLanguageDistinct();

		personsProgrammingLanguague.forEach(System.out::println);
	}


	@Transactional(readOnly = true)
	public void personalizeQuery() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona que quieres ver el nombre");
		Long id = scanner.nextLong();
		//String name = repository.getNameById(id);
		//Long idPerson = repository.getIdById(id);
		//String fullName = repository.getFullNameById(id);
		Object[] person = (Object[]) repository.getDataPersonById(id);

		System.out.println(person[0] + " " + person[1] + " " + person[2]);
	}



	@Transactional(readOnly = true)
	public void personalizeQuery2() {
		//List<Object[]> personsData = repository.findAllMixPerson();
		//List<Person> persons = repository.findAllClassPerson();
		List<PersonDto> personsDto = repository.findAllPersonDto();

		personsDto.forEach(System.out::println);
	}




	@Transactional
	public void delete(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona que quieres eliminar");
		Long id = scanner.nextLong();

		repository.deleteById(id);
	}

	@Transactional
	public void delete2(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona que quieres eliminar");
		Long id = scanner.nextLong();

		Optional<Person> person = repository.findById(id);
		person.ifPresentOrElse(person1 -> repository.delete(person1),
				() -> System.out.println("La persona no existe"));
	}

	@Transactional
	public void update(){

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona que quieres buscar");
		Long id = scanner.nextLong();

		Optional<Person> person = repository.findById(id);
//		person.ifPresent(person1 -> {
//			System.out.println(person1);
//			System.out.println("Ingrese el lenguaje de programación de " + person1.getName());
//			String newProgrammingLanguage = scanner.next();
//			person1.setProgrammingLanguage(newProgrammingLanguage);
//			repository.save(person1);
//			System.out.println("Persona modificada " + person1);
//		});

		if (person.isPresent()){
			System.out.println(person);
			System.out.println("Ingrese el lenguaje de programación de " + person.get().getName());
			String newProgrammingLanguage = scanner.next();
			person.get().setProgrammingLanguage(newProgrammingLanguage);
			repository.save(person.get());
			System.out.println("Persona modificada " + person);
		}else {
			System.out.println("La persona no existe");
		}

	}

	@Transactional
	public void create(){

		Person person = new Person(null, "Francisco", "Hontoria", "Java");
		repository.save(person);

		System.out.println(person.toString());

		repository.findByName("Francisco").ifPresent(System.out::println);

	}

	@Transactional(readOnly = true)
	public void findOne(){
		//Formas de trabajar con un Optional<>

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





		// Probando metodos
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
