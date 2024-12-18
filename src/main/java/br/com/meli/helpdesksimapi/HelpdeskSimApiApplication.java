package br.com.meli.helpdesksimapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HelpdeskSimApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelpdeskSimApiApplication.class, args);
    }

}
