package com.api.rent.memovie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class MemovieApiApplication implements CommandLineRunner {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(MemovieApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String pass = "admin";
        String passBCrypt = passwordEncoder.encode(pass);
//        System.out.println("****************passencriptado:" + passBCrypt);

    }

}

@RestController
class provisional {

    @GetMapping({"/"})
    public String helloTest() {
        return "PRIMERA PRUEBA DE AMBIENTE CON HEROKU";
    }
}
