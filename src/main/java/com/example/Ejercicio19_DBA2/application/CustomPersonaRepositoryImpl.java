package com.example.Ejercicio19_DBA2.application;

import com.example.Ejercicio19_DBA2.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;

public class CustomPersonaRepositoryImpl implements  CustomPersonaRepository{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void partialUpdate(Integer id, String fieldName, Object fieldValue) {
        mongoTemplate.findAndModify(BasicQuery.query(Criteria.where("id").is(id)),
                BasicUpdate.update(fieldName, fieldValue), FindAndModifyOptions.none(), Persona.class);
    }
}
