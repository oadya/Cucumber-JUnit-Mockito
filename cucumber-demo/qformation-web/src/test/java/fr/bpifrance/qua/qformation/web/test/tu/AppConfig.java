package fr.bpifrance.qua.qformation.web.test.tu;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"fr.bpifrance.qua.qformation","fr.bpifrance.qua.qformation.web.restclient" })
public class AppConfig  {
}
