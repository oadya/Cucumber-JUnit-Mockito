package fr.bpifrance.qua.qformation.dao.test.ti;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication

@ComponentScan(basePackages = {"fr.bpifrance.qua.qformation.dao","fr.bpifrance.qua.qformation.domaine.demo" })
@EntityScan("fr.bpifrance.qua.qformation.dao") //.models")
@EnableJpaRepositories("fr.bpifrance.qua.qformation.dao") //.repositories")
public class AppConfig {
}
