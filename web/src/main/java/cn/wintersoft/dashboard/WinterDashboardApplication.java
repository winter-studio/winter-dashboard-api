package cn.wintersoft.dashboard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@MapperScan("cn.wintersoft.**.mapper")
@ConfigurationPropertiesScan("cn.wintersoft")
public class WinterDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(WinterDashboardApplication.class, args);
	}

}
