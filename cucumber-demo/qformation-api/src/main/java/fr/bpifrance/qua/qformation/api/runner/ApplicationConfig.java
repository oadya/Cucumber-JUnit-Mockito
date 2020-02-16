package fr.bpifrance.qua.qformation.api.runner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"fr.bpifrance.qua.qformation" })
@EntityScan("fr.bpifrance.qua.qformation.dao")
@EnableJpaRepositories("fr.bpifrance.qua.qformation.dao")
public class ApplicationConfig {
}
