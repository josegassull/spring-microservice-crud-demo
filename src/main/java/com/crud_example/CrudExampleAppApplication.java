package com.crud_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudExampleAppApplication {

	//Todo A: Completar el service de NatioinalId y hacer el Controller de NationalId

	//Todo B: (Lo hacemos luego con las querys)Crear método para buscar todos los identificadores de un cliente

	//TODO DUDA: Cada vez que se modifican las entidades, hay que reiniciar toda la base de datos? ¿Como se
	//maneja esto en realidad?

	public static void main(String[] args)
	{
		SpringApplication.run(CrudExampleAppApplication.class, args);
	}
}