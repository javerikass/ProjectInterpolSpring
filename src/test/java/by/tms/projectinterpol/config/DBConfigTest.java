package by.tms.projectinterpol.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(HibernateConfig.class)
@ComponentScan(basePackages = "by.tms.projectinterpol.util")
public class DBConfigTest {
}
