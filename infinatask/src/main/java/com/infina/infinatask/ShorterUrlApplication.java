package com.infina.infinatask;

import com.infina.rest.UrlRestController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackageClasses = UrlRestController.class)
public class ShorterUrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShorterUrlApplication.class, args);
    }


}