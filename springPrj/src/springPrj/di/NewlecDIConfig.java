package springPrj.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springPrj.di.entity.Exam;
import springPrj.di.entity.NewlecExam;


@ComponentScan({"spring.di.ui", "spring.di.entity"})
@Configuration
public class NewlecDIConfig {

	@Bean
	public Exam exam() {
		return new NewlecExam();
	}
}
