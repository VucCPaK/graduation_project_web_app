package ua.kirilogrecha.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Objects;

@SpringBootApplication
public class BackendApplication {

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//		dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.driver-class-name")));
//		dataSource.setUsername(Objects.requireNonNull(env.getProperty("spring.datasource.username")));
//		dataSource.setPassword(Objects.requireNonNull(env.getProperty("spring.datasource.password")));
//		dataSource.setUrl(Objects.requireNonNull(env.getProperty("spring.datasource.url")));
//
//		return dataSource;
//	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:8082")
						.allowedMethods("*")
						.allowCredentials(true);
			}
		};
	}


}