package balance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BalanceApplication {

	public static void main(String[] args) {

		// Detectar el ambiente y forzar la configuración correspondiente
		String profile = System.getenv("SPRING_PROFILES_ACTIVE");

		System.out.println("===========================================");
		System.out.println("PERFIL DETECTADO: " + profile);
		System.out.println("===========================================");

		if ("dev".equals(profile)) {
			System.out.println("=== FORZANDO CONFIGURACIÓN DE DESARROLLO ===");
			System.setProperty("spring.datasource.url",
					"jdbc:mysql://195.26.244.73:3306/humberto_development");
			System.setProperty("spring.datasource.username", "humberto_devuser");
			System.setProperty("spring.datasource.password", "UnaContraseñaNueva2025#");
			System.setProperty("spring.datasource.driver-class-name", "com.mysql.cj.jdbc.Driver");
			System.setProperty("spring.jpa.hibernate.ddl-auto", "update");
			System.setProperty("spring.jpa.show-sql", "true");

			System.out.println("✅ Configuración de desarrollo aplicada:");
			System.out.println("   - URL: jdbc:mysql://195.26.244.73:3306/humberto_development");
			System.out.println("   - Usuario: humberto_devuser");
			System.out.println("===========================================");

		} else if ("prod".equals(profile)) {
			System.out.println("=== FORZANDO CONFIGURACIÓN DE PRODUCCIÓN ===");
			System.setProperty("spring.datasource.url",
					"jdbc:mysql://195.26.244.73:3306/balance_service");
			System.setProperty("spring.datasource.username", "humberto0803");
			System.setProperty("spring.datasource.password", "humberto080315#");
			System.setProperty("spring.datasource.driver-class-name", "com.mysql.cj.jdbc.Driver");
			System.setProperty("spring.jpa.hibernate.ddl-auto", "update");
			System.setProperty("spring.jpa.show-sql", "false");

			System.out.println("✅ Configuración de producción aplicada:");
			System.out.println("   - URL: jdbc:mysql://195.26.244.73:3306/balance_service");
			System.out.println("   - Usuario: humberto0803");
			System.out.println("===========================================");

		} else {
			System.out.println("⚠️  PERFIL NO RECONOCIDO O NO ESPECIFICADO");
			System.out.println("   Perfil recibido: " + profile);
			System.out.println("   Usando configuración por defecto");
			System.out.println("===========================================");
		}


		SpringApplication.run(BalanceApplication.class, args);
	}

}