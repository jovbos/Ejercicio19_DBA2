package com.example.Ejercicio19_DBA2.application;

public interface CustomPersonaRepository {
    void partialUpdate(Integer id, String fieldName, Object fieldValue);
}
