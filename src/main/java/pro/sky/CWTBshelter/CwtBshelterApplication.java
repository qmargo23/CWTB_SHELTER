package pro.sky.CWTBshelter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
	public class CwtBshelterApplication {

		public static void main(String[] args) {
			SpringApplication.run(CwtBshelterApplication.class, args);
		}

	}
