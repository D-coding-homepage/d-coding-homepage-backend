package d_coding_homepage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DCodingHomepageApplication {

	public static void main(String[] args) {
		SpringApplication.run(DCodingHomepageApplication.class, args);
	}

}
