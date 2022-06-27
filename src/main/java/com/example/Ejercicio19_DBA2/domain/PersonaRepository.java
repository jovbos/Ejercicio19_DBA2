package com.example.Ejercicio19_DBA2.domain;

import com.example.Ejercicio19_DBA2.application.CustomPersonaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonaRepository extends MongoRepository<Persona, Integer>, CustomPersonaRepository {
    List<Persona> findByUsuario(String usuario);
}
