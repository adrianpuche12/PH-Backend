// ARCHIVO TEMPORALMENTE DESHABILITADO PARA PRUEBAS
// Para reactivar, descomenta todo el código de abajo

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

/*
@Configuration
@Profile("dev")
public class DevDataSourceConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(DevDataSourceConfig.class);
    
    @Primary
    @Bean
    public DataSource dataSource() {
        logger.info("********************************************");
        logger.info("* INICIALIZANDO BASE DE DATOS DE DESARROLLO *");
        logger.info("* URL: jdbc:mysql://195.26.244.73:3306/humberto_development *");
        logger.info("* Usuario: humberto_devuser *");
        logger.info("********************************************");
        
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://195.26.244.73:3306/humberto_development");
        config.setUsername("humberto_devuser");
        config.setPassword("UnaContraseñaNueva2025#");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(30000);
        config.setConnectionTimeout(20000);
        
        return new HikariDataSource(config);
    }
}
*/

/**
 * NOTAS ADICIONALES:
 * 
 * 1. SEGURIDAD: Las credenciales están hardcodeadas, lo ideal sería
 *    usar variables de entorno o archivos de propiedades:
 *    - @Value("${db.username}") String username
 *    - System.getenv("DB_PASSWORD")
 * 
 * 2. HIKARICP: Es uno de los pools de conexiones más rápidos y eficientes
 *    para Java, optimizado para alto rendimiento.
 * 
 * 3. PROFILE: Al usar @Profile("dev"), esta configuración solo se activa
 *    cuando ejecutas la aplicación con: --spring.profiles.active=dev
 * 
 * 4. @Primary: Asegura que si hay múltiples DataSources, este sea el predeterminado.
 * 
 * 5. POOL SIZING: Los valores (10 max, 5 min) son apropiados para desarrollo.
 *    En producción podrían necesitar ajustes según la carga.
 */