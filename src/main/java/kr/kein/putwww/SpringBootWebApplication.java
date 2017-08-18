package kr.kein.putwww;

import kr.kein.putwww.job.config.JobScheduleProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
@Configuration
@EnableAutoConfiguration
@ComponentScan
@MapperScan("kr.kein.putwww")
@EnableJpaRepositories(basePackages="kr.kein.putwww")
@EnableConfigurationProperties(value = {JobScheduleProperties.class})
public class SpringBootWebApplication {
	private int maxUploadSizeInMb = 10 * 1024 * 1024; // 10 MB

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}

}