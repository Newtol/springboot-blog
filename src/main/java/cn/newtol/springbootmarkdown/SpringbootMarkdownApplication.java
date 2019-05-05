package cn.newtol.springbootmarkdown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 表自动更新时间、创建时间
@EnableJpaAuditing
@SpringBootApplication
public class SpringbootMarkdownApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMarkdownApplication.class, args);
	}

}
