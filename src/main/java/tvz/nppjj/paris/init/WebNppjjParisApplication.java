package tvz.nppjj.paris.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan(basePackages = { "tvz.nppjj.paris" })
@EnableJpaRepositories(basePackages = { "tvz.nppjj.paris.repository" })
@EntityScan(basePackages = { "tvz.nppjj.paris.model" })
@EnableAsync
public class WebNppjjParisApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebNppjjParisApplication.class, args);
	}
}
