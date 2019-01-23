/**
 * Muzix Application main class
 */
package com.stackroute;

import com.stackroute.domain.Muzix;
import com.stackroute.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication //used to enable @EnableAutoConfiguration, @ComponentScan, @Configuration

//property source path to application properties
@PropertySource("classpath:object.properties")
public class MuzixApplication implements CommandLineRunner {

	//@value annotations are used to get the values from application.properties
	@Value("${trackId}")
	private int trackId;

	@Value("${trackName}")
	private String trackName;

	@Value("${comment}")
	private String comment;

	@Autowired
	MuzixRepository muzixRepository;
	public static void main(String[] args) {
		SpringApplication.run(MuzixApplication.class, args);
	}

	//Overriden CommandLineRunner method
	@Override
	public void run(String... args) throws Exception {
		muzixRepository.save(new Muzix(trackId,trackName,comment));
	}
}

