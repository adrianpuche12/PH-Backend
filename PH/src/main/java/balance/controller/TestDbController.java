package balance.controller;

import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test-db")
public class TestDbController {
    
    @Autowired
    private EntityManager entityManager;
    
    @GetMapping("/info")
    public Map<String, Object> getDbInfo() {
        Map<String, Object> info = new HashMap<>();
        
        // Obtener información de la conexión actual
        Session session = entityManager.unwrap(Session.class);
        SessionFactoryImplementor sessionFactory = 
            (SessionFactoryImplementor) session.getSessionFactory();
        
        ConnectionProvider connectionProvider = 
            sessionFactory.getServiceRegistry().getService(ConnectionProvider.class);
        
        try (Connection conn = connectionProvider.getConnection()) {
            info.put("url", conn.getMetaData().getURL());
            info.put("username", conn.getMetaData().getUserName());
            info.put("catalog", conn.getCatalog());
            info.put("schema", conn.getSchema());
        } catch (Exception e) {
            info.put("error", e.getMessage());
            info.put("stack", e.getStackTrace());
        }
        
        return info;
    }
    
    @GetMapping("/test-write")
    public Map<String, Object> testWrite() {
        Map<String, Object> result = new HashMap<>();
        String testMessage = "Test DB Write " + System.currentTimeMillis();
        
        try {
            // Crear una entidad de prueba y guardarla
            // Por ejemplo, si tienes una entidad simple como Transaction
            
            /* Ejemplo (adapta a tu modelo):
            Transaction testTransaction = new Transaction();
            testTransaction.setType("test");
            testTransaction.setAmount(new BigDecimal("0.01"));
            testTransaction.setDate(LocalDate.now());
            testTransaction.setDescription(testMessage);
            
            // Necesitarás un Store válido
            Store store = new Store();
            store.setId(1L); // Asegúrate que este ID existe
            testTransaction.setStore(store);
            
            entityManager.persist(testTransaction);
            entityManager.flush();
            
            result.put("id", testTransaction.getId());
            */
            
            // Alternativa usando SQL nativo si no quieres usar una entidad existente
            String sql = "INSERT INTO test_db_info (message, created_at) VALUES (?, NOW())";
            entityManager.createNativeQuery(sql)
                .setParameter(1, testMessage)
                .executeUpdate();
            
            result.put("message", testMessage);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("error", e.getMessage());
            result.put("status", "error");
        }
        
        // Obtener la información de la base de datos también
        result.putAll(getDbInfo());
        
        return result;
    }
}