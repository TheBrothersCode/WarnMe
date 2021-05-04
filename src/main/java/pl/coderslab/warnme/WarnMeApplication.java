package pl.coderslab.warnme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WarnMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarnMeApplication.class, args);
	}

	public String sayHello(String name) {
		return "Hi " + name;
	}

}
