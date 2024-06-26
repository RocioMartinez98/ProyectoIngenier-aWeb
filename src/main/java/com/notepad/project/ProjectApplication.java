package com.notepad.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;

@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class)
public class ProjectApplication {

    public static void main(String[] args){
        SpringApplication.run(ProjectApplication.class, args);
    }
}
