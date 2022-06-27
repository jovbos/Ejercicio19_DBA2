package com.example.Ejercicio19_DBA2.infracstructure;

import com.example.Ejercicio19_DBA2.application.PersonaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controlador {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/addPersona")
    public void addPersona(@RequestBody List<PersonaDTO> personaDTOList) throws Exception {
        personaService.saveAll(personaDTOList);
    }


    // Metodo para modificar personas por campos

    @PatchMapping("/partialUpdatePersona/{id}")
    public void partialUpdatePersona(@PathVariable("id") Integer id, @RequestBody PersonaDTO personaDTO) throws Exception {
        personaService.partialUpdatePersona(id, personaDTO);
    }

    @DeleteMapping("/deletePersona/{id}")
    public void deletePersona(@PathVariable("id") Integer id) throws Exception{
        personaService.deletePersona(id);
    }

    @GetMapping("/getPersona/{id}")
    public PersonaDTO getPersonaId(@PathVariable("id") Integer id) throws Exception{
        PersonaDTO personaDTO = modelMapper.map(personaService.findById(id), PersonaDTO.class);
        return personaDTO;
    }

    @GetMapping("/getPersona")
    public List<PersonaDTO> getAll() {
        return personaService.findAll();
    }
}
