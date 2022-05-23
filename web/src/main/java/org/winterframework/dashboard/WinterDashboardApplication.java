package org.winterframework.dashboard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.winterframework.**.mapper")
public class WinterDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(WinterDashboardApplication.class, args);
	}

}
