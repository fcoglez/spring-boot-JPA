package com.springboot.jpa.springboot_jpa.repositories;

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

    //Implementación de mis propios metodos
    @Query("select person from Person person where person.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select person from Person person where person.name=?1")
    Optional<Person> findOneName(String name);

    @Query("select person from Person person where person.name like %?1%")
    Optional<Person> findOneLikeName(String name);



    //MÉTEDOS CREADOS POR UNO MISMO
    @Query("select persons from Person persons")
    List<Person> buscarAllPersons();

    @Query("select persons from Person persons where persons.programmingLanguage=?1")
    List<Person> buscarPorLenguajeProgramacion(String programmingLanguage);

    @Query("select persons from Person persons where persons.programmingLanguage=?1 and persons.name=?2")
    List<Person> buscarPorLenguajeProgramacionYNombre(String programmingLanguage, String name);


    //CARGA DE ARGUMENTOS
    @Query("select person.name, person.programmingLanguage from Person person")
    List<Object[]> obtenerValoresPersona();

    @Query("select person.name, person.programmingLanguage from Person person where person.programmingLanguage=?1")
    List<Object[]> obtenerValoresPersona(String programmingLanguage);


}
