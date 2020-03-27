package com.api.rent.memovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class MemovieApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemovieApiApplication.class, args);
    }

    @RestController
    class pruebaParaHeroku {
        @RequestMapping({"/"})
        public String helloTest() {
            return "PRIMERA PRUEBA DE AMBIENTE CON HEROKU";
        }

    }

}
