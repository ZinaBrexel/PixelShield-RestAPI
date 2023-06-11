package fr.simplon.pixelshielrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("fr.simplon.pixelshielrestapi.repository")
@EntityScan("fr.simplon.pixelshielrestapi.entity")
public class PixelShielRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PixelShielRestApiApplication.class, args);
    }

}
