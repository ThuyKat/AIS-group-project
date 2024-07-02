package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.AllInSmall.demo.model.Order;

@Configuration
public class SessionConfig {

	@Bean
	@SessionScope
	public Order order() {
		return new Order();
	}
}
