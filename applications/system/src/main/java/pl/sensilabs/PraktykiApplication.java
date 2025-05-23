package pl.sensilabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"pl.sensilabs"})
public class PraktykiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PraktykiApplication.class, args);
    }

}
