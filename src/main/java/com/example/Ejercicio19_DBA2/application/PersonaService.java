package com.example.Ejercicio19_DBA2.application;

import com.example.Ejercicio19_DBA2.domain.Persona;
import com.example.Ejercicio19_DBA2.domain.PersonaRepository;
import com.example.Ejercicio19_DBA2.infracstructure.PersonaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class PersonaService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PersonaRepository personaRepository;

    public List<PersonaDTO> findAll() {
        List<Persona> personaList = mongoTemplate.findAll(Persona.class);
        List<PersonaDTO> personaDTOList = new ArrayList<>();
        personaList.forEach(p -> {
            PersonaDTO personaDTO = modelMapper.map(p, PersonaDTO.class);
            personaDTOList.add(personaDTO);
        });
        return personaDTOList;
    }

    public void saveAll(List<PersonaDTO> personaDTOList) {
        List<Persona> personaList = new ArrayList<>();
        personaDTOList.forEach(dto -> {
            Persona persona = modelMapper.map(dto, Persona.class);
            personaList.add(persona);
        });
        mongoTemplate.insertAll(personaList);
    }

    public PersonaDTO findById(Integer id) {
        Persona persona = mongoTemplate.findById(id, Persona.class);
        PersonaDTO personaDTO = modelMapper.map(persona, PersonaDTO.class);
        return personaDTO;
    }

    public void partialUpdatePersona(Integer id, PersonaDTO personaDTO) throws Exception {
        Persona persona = modelMapper.map(personaDTO, Persona.class);
        for (Field field : Persona.class.getDeclaredFields()) {
            String fieldName = field.getName();

            if (fieldName.equals("id")) {
                continue;
            }
            Method getter = Persona.class.getDeclaredMethod("get" + StringUtils.capitalize(fieldName));
            Object fieldValue = getter.invoke(persona);

            if ( Objects.nonNull(fieldValue)) {
                personaRepository.partialUpdate(id, fieldName, fieldValue);
            }
        }
    }

    public void deletePersona(Integer id) {
        Persona persona = mongoTemplate.findById(id, Persona.class);
        mongoTemplate.remove(persona);
    }
}
