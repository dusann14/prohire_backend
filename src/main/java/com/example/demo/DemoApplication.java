package com.example.demo;

import com.example.demo.model.domain.*;
import com.example.demo.repository.*;
import lombok.Builder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Scanner;

@SpringBootApplication
@EnableWebMvc
public class DemoApplication {

	public static void main(String[] args) { SpringApplication.run(DemoApplication.class, args); }

	@Bean
	public CommandLineRunner commandLineRunner(CityRepository cityRepository,
											   IndustryRepository industryRepository,
											   WorkFieldRepository workFieldRepository,
											   AppUserRepository appUserRepository,
											   EmployerRepository employerRepository,
											   JobSeekerRepository jobSeekerRepository
	){
		return args -> {

			String[] array = new String[]{"Beograd", "Novi Sad", "Nis", "Subotica", "Kragujevac",
					"Ada", "Aleksandrovac", "Aleksinac", "Alibunar", "Apatin", "Arandjelovac", "Arilje", "Babusnica",
					"Bajina Basta", "Baric", "Batajnica", "Batocina", "Backa Palanka", "Backa Topola", "Backi Petrovac",
					"Bela Crkva", "Bela Palanka", "Bujanovac", "Danilovgrad", "Dimitrovgrad", "Vranje",
					"Leskovac", "Kikinda", "Kosjeric", "Kopaonik", "Kraljevo", "Cacak", "Prokuplje", "Kursumlija",
					"Kucevo", "Lajkovac", "Uzice", "Ljig", "Loznica", "Negotin", "Nova Varos", "Smederevo", "Pozarevac",
					"Pozega", "Novi Pazar", "Sombor", "Subotica"};


			for (String cityName:
					array) {
				var city = City.builder()
						.name(cityName)
						.build();
				cityRepository.save(city);
			}

			array = new String[]{"Auto industrija", "Farmacija i zdravstvo", "Sektro finansija", "Telekomunikacije",
					"IT", "Saobracaj, infrastruktura i logistika", "Trgovina", "Turizam i ugostiteljstvo", "Poljoprivreda",
					"Energetika i rudarstvo", "Gradjevinarstvo i arhitektura", "Rad i zaposljavanje", "Prosveta, nauka i tehnoloski razvoj",
					"Kultura. informisanje i marketing", "Usluzni sektor i zanati", "Prehrambena industrija", "Proizvodnja",
					"Hemijska industrija", "Duvanska industrija", "Zabava i igre na srecu"};


			for (String industryName:
					array) {
				var industry = Industry.builder()
						.name(industryName)
						.build();
				industryRepository.save(industry);
			}

			array = new String[]{"trgovina, prodaja", "masinstvo", "elektrotehnika", "administracija", "IT",
					"arhitektura", "bankarstvo", "biologija", "briga o lepoti", "dizajn", "ekonomija", "farmacija",
					"finansije", "fizika", "gradjevina, geodezija", "hemija", "higijena", "jezici, književnost", "ljudski resusrsi",
					"logistika", "magacin", "marketing", "mediji", "obezbeđenje", "menadžment", "obrazovanje", "poljoprivreda", "turizam"};

			for (String workFieldName:
					array) {
				var workField = WorkField.builder()
						.name(workFieldName)
						.build();
				workFieldRepository.save(workField);
			}


		};
	}

}
