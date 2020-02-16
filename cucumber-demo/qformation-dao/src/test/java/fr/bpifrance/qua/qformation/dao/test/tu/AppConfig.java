package fr.bpifrance.qua.qformation.dao.test.tu;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@ComponentScan(basePackages = {"fr.bpifrance.qua.qformation.dao"})
@EntityScan("fr.bpifrance.qua.qformation.dao.models")
@EnableJpaRepositories("fr.bpifrance.qua.qformation.dao.repositories")
public class AppConfig {
}
