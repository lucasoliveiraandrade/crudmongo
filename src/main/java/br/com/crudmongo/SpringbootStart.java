package br.com.crudmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class used by SpringBoot to start the application.
 *
 * @author lucasandrade
 */
@SpringBootApplication
@Configuration 
@ComponentScan(basePackages = {"br.com.crudmongo"})
public class SpringbootStart {
    public static void main(String[] args){
    		SpringApplication.run(SpringbootStart.class, args);	
    }
}