package fr.bpifrance.qua.qformation.api.runner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ApplicationConfig.class })
public class SpringRun{//  extends SpringBootServletInitializer {
/*
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringRun.class);
	}*/
	
    public static void main(String[] args) {
    	
        SpringApplication.run(ApplicationConfig.class, args);
    }

 
}
