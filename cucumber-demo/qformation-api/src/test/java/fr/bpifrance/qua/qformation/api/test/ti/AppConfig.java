package fr.bpifrance.qua.qformation.api.test.ti;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication
@SpringBootApplication
@ComponentScan(basePackages = {"fr.bpifrance.qua.qformation.api.resources" })
@ComponentScan(basePackages = {"fr.bpifrance.qua.qformation" })
@EntityScan("fr.bpifrance.qua.qformation.dao.models")
@EnableJpaRepositories("fr.bpifrance.qua.qformation.dao.repositories")
public class AppConfig {
}
