package balance.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * Configuración de la fuente de datos para el ambiente de desarrollo.
 * Esta clase tiene la anotación @Profile("dev") para que solo se active
 * cuando el perfil "dev" esté activo.
 */
@Configuration
@Profile("dev")
public class DevDataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(DevDataSourceConfig.class);

    /**
     * Crea y configura un DataSource para el ambiente de desarrollo.
     * La anotación @Primary asegura que este DataSource se utilice preferentemente
     * sobre cualquier otro DataSource definido.
     */
    @Primary
    @Bean
    public DataSource dataSource() {
        logger.info("**");
        logger.info("* INICIALIZANDO BASE DE DATOS DE DESARROLLO ");
        logger.info(" URL: jdbc:mysql://195.26.244.73:3306/humberto_development ");
        logger.info(" Usuario: humberto_devuser *");
        logger.info("**");

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://195.26.244.73:3306/humberto_development");
        config.setUsername("humberto_devuser");
        config.setPassword("UnaContraseñaNueva2025#");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");

        // Configuración adicional de Hikari
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(30000);
        config.setConnectionTimeout(20000);

        return new HikariDataSource(config);
    }
}


