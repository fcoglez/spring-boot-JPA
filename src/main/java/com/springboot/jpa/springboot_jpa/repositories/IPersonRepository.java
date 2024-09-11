package com.springboot.jpa.springboot_jpa.repositories;

import com.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.springboot.jpa.springboot_jpa.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;

public interface IPersonRepository extends CrudRepository<Person, Long> {

    //MÉTODOS FACILITADOS POR JPA
    List<Person> findByProgrammingLanguage(String programmingLanguage);

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    Optional<Person> findByName(String name); //Busca el nombre completo

    Optional<Person> findByNameContaining(String name); //Busca el nombre con alguna coincidencia

    List<Person> findByIdBetween(Long number1, Long number2); // Busca personas que este entre un id y el otro pasado por parametro

    List<Person> findByNameBetween(String name1, String name2);

    List<Person> findByIdBetweenOrderByIdDesc(Long start, Long end);

    List<Person> findByIdBetweenOrderByNameDesc(Long start, Long end);

    List<Person> findByIdBetweenOrderByNameDescSurnameAsc(Long start, Long end);

    List<Person> findAllByOrderByIdDesc();

    List<Person> findAllByOrderByNameDesc();

    List<Person> findAllByOrderByNameAscSurnameDesc();











    //Implementación de mis propios metodos con Optional
    @Query("select person from Person person where person.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select person from Person person where person.name=?1")
    Optional<Person> findOneName(String name);

    @Query("select person from Person person where person.name like %?1%")
    Optional<Person> findOneLikeName(String name);




    // Querys personalizadas devolviendo el tipo que queramos siempre que coincida en la base de datos
    @Query("select person.name from Person person where person.id = ?1")
    String getNameById(Long Id);

    @Query("select person.id from Person person where person.id = ?1")
    Long getIdById(Long Id);

    @Query("select concat(person.name,' ',person.surname) as fullname from Person person where person.id = ?1")
    String getFullNameById(Long Id);

    @Query("select person.name, person.surname, person.programmingLanguage from Person person where person.id = ?1")
    Object getDataPersonById(Long Id);




    //MÉTEDOS CREADOS POR UNO MISMO
    @Query("select persons from Person persons")
    List<Person> buscarAllPersons();

    @Query("select persons from Person persons where persons.programmingLanguage=?1")
    List<Person> buscarPorLenguajeProgramacion(String programmingLanguage);

    @Query("select persons from Person persons where persons.programmingLanguage=?1 and persons.name=?2")
    List<Person> buscarPorLenguajeProgramacionYNombre(String programmingLanguage, String name);





    // Consultas personalizadas (mixtas)
    @Query("select person, person.programmingLanguage from Person person")
    List<Object[]> findAllMixPerson();

    @Query("select new Person(person.name, person.surname) from Person person") //En esta consulta, vamos a devolver el nombre y apellido de las personas gracias a que tenemos un constructor con los 2 parametros.
    List<Person> findAllClassPerson();

    @Query("select new com.springboot.jpa.springboot_jpa.dto.PersonDto(person.name, person.surname) from Person person")
    List<PersonDto> findAllPersonDto();

    @Query("select person.name from Person person")
    List<String> findAllNames();

    @Query("select distinct(person.name) from Person person") //Con esta consulta si hay nombres repetidos, solo nos devuelve uno (no se repite el nombre)
    List<String> findAllNamesDistinct();

    @Query("select distinct(person.programmingLanguage) from Person person") //Con esta consulta si hay nombres repetidos, solo nos devuelve uno (no se repite el nombre)
    List<String> findAllProgrammingLanguageDistinct();





    //TIPOS PARA CONCATENAR
    //@Query("select concat(person.name,' ',person.surname) from Person person")
    @Query("select person.name || ' ' || person.surname from Person person") // Esta es otra forma de hacer un CONCAT()
    List<String> findAllFullNameConcat();

    @Query("select upper(person.name || ' ' || person.surname) from Person person") // De esta forma, ponemos los nombres y apellidos en MAYUS
    List<String> findAllFullNameConcatUpper();

    @Query("select lower(concat(person.name || ' ' || person.surname)) from Person person")
    List<String> findAllFullNameConcatLower();

    @Query("select person.id, upper(person.name), lower(person.surname), upper(person.programmingLanguage) from Person person")
    List<Object[]> findAllPersonDataCase();





    //USANDO BETWEEN
    @Query("select person from Person person where person.id between 2 and 8")
    List<Person> findAllBetweenId();

    //@Query("select person from Person person where person.name between 'E' and 'F'") //Devuelve personas cuyo nombre está alfabéticamente entre 'E' y 'F', la letra F es inclusiva, por lo tanto no la muestra los nombres que empiezan por F.
    //@Query("select person from Person person where person.name >= 'E' and person.name < 'G'") //Esto devuelve todos los nombres que van desde 'E' hasta justo antes de 'G'
    //List<Person> findAllBetweenName();

    @Query("select person from Person person where person.name >= ?1 and person.name < ?2") //De esta forma seria igual que arriba pero pasando parametros.
    List<Person> findAllBetweenName(String character1, String character2);








    //USANDO ODER BY PARA ORDENAR
    //@Query("select person from Person person where person.name >= ?1 and person.name < ?2 order by person.name") //Esto al resultado, lo ordenaria por el nombre alfabeticamente
    //@Query("select person from Person person order by person.name") //Esto al resultado, lo ordenaria por el nombre alfabeticamente
    //@Query("select person from Person person order by person.name asc") //Cuando usamos asc es de manera ascendente desde la A a la Z. Si no se pone nada, por defecto usa asc
    //@Query("select person from Person person order by person.name desc") //Cuando usamos desc es de manera descendente desde la Z a la A
    @Query("select person from Person person order by person.name asc, person.surname desc")
    List<Person> findAllBetweenNameAndOderBy();






    //USANDO COUNT, MIN, MAX
    @Query("select count(person.name) from Person person")
    Long totalPerson();

    @Query("select min(person.id) from Person person")
    Long minId();

    @Query("select max(person.id) from Person person")
    Long maxId();




    //USANDO LENGTH
    @Query("select person.name, length(person.name) from Person person")
    List<Object[]> getPersonNameLength();

    @Query("select min(length(person.name)) from Person person")
    Integer getMinLengthName();





    //Resumen con todas las funciones
    @Query("select min(person.id), max(person.id), sum(person.id), avg(length(person.name)), count(person.id) from Person person")
    Object getResumeAllFunctions();



    //CONSULTAS ANIDADAS
    //La consulta devuelve los nombres y sus longitudes para todas las personas cuyos nombres tienen la longitud mínima en la tabla.
    @Query("select person.name, length(person.name) from Person person where length(person.name) = (select min(length(person.name)) from Person person)")
//  @Query("select person.name, length(person.name) from Person person where length(person.name) = (select min(length(p.name)) from Person p)")
    List<Object[]> getShorterName();

    @Query("select person from Person person where person.id = (select max(p.id) from Person p)")
    Optional<Person> getLastPersonRegistration();



    //USANSO EL IN (que contengan) O EL NOT IN ( que no contenga)
    //@Query("select person from Person person where person.id in (2, 5, 4)") //Devuelve las personas con id 2, 5, 4
    //@Query("select person from Person person where person.id in ?1") //Devuelve las personas con el id que pasamos por parametros
    @Query("select person from Person person where person.id not in ?1") //Devuelve las personas que no contiene el id pasado por parametro
    List<Person> getPersonsByIds(List<Long> ids);



    //CARGA DE ARGUMENTOS
    @Query("select person.name, person.programmingLanguage from Person person")
    List<Object[]> obtenerValoresPersona();

    @Query("select person.name, person.programmingLanguage from Person person where person.programmingLanguage=?1")
    List<Object[]> obtenerValoresPersona(String programmingLanguage);



}
