package com.dbclm.nace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableAutoConfiguration
@EntityScan("com.dbclm.persistence.entity,")
@EnableJpaRepositories("com.dbclm.persistence.*")
@SpringBootApplication(scanBasePackages = {"com.dbclm.bean.config","com.dbclm.nace.service.*","com.dbclm.nace.*"
		})
public class NaceDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(NaceDataApplication.class, args);
	}

}
