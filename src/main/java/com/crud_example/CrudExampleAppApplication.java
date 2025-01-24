package com.crud_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudExampleAppApplication {

	//Todo Crear clase nationalId con atributos
	//-tipo de identificación: enumeración (dentro de la carpeta core/types)
	//-nro de identificación
	//-fecha de expedición (tipo localDate)
	//-fecha de expiración (tipo localDate)
	//
	//Luego hacer el crud de esta entidad y relacionarlo con customer de forma unidireccional. Con hibernate.
	// 1-1. Hacemos un service y un controller para esta entidad.

	public static void main(String[] args)
	{
		SpringApplication.run(CrudExampleAppApplication.class, args);
	}
}