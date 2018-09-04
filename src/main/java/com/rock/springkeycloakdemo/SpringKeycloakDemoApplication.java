package com.rock.springkeycloakdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.rock.springkeycloakdemo"})
public class SpringKeycloakDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringKeycloakDemoApplication.class, args);
    }
}
