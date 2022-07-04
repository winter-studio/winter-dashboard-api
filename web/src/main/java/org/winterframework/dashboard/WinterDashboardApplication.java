package org.winterframework.dashboard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@MapperScan("org.winterframework.**.mapper")
@ConfigurationPropertiesScan("org.winterframework")
public class WinterDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(WinterDashboardApplication.class, args);
	}

}
