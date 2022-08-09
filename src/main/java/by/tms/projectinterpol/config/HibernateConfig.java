package by.tms.projectinterpol.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("by.tms.projectinterpol")
@PropertySource({"classpath:database.properties", "classpath:hibernate.properties"})
public class HibernateConfig {

    @Bean
    public DataSource dataSource(@Value("${db.driver}") String driver,
                                 @Value("${db.url}") String url,
                                 @Value("${db.username}") String username,
                                 @Value("${db.password}") String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public Properties hibernateProperties(@Value("classpath:hibernate.properties") Resource resource) throws IOException {
        Properties properties = new Properties();
        properties.load(resource.getInputStream());
        return properties;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource, Properties hibernateProperties) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setHibernateProperties(hibernateProperties);
        localSessionFactoryBean.setPackagesToScan("by.tms.projectinterpol.entity");
        return localSessionFactoryBean;
    }

    @Bean
    public TransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
