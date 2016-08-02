/**
 * 
 */
package sample;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Siva
 *
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
@EnableJdbcHttpSession
public class AppConfig {

	@Autowired
	private Environment env;

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer()
	{
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Value("${init-db:false}")
	private String initDatabase;
	
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource)
	{
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}
	
	@Bean
	public DataSourceInitializer dataSourceInitializer(DataSource dataSource) 
	{
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSource);
		dataSourceInitializer.setEnabled(env.getProperty("init-db", Boolean.class, false));

		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		dataSourceInitializer.setDatabasePopulator(databasePopulator);

		String dbType = env.getProperty("dbType","h2");
		String ss_schema = "org/springframework/session/jdbc/schema-"+dbType+".sql";
		databasePopulator.addScript(new ClassPathResource(ss_schema));
		
		//If the Spring Session tables are already exists
		databasePopulator.setContinueOnError(true);
		
		return dataSourceInitializer;
	}
}
