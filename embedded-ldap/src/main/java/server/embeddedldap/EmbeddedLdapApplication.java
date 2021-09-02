package server.embeddedldap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmbeddedLdapApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmbeddedLdapApplication.class, args);
	}
}
