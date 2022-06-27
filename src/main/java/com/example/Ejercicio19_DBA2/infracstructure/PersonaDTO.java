package com.example.Ejercicio19_DBA2.infracstructure;

import lombok.Data;

import java.util.Date;

@Data
public class PersonaDTO {

    private Integer id;

    private String usuario;

    private String password;

    private String name;

    private String surname;

    private String company_email;

    private String personal_email;

    private String city;

    private Boolean active;

    private Date created_date;

    private String image_url;

    private Date termination_date;


}
