package balance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "salary_payments")
public class SalaryPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 1, max = 200, message = "La descripción debe tener entre 1 y 200 caracteres")
    @Column(nullable = false)
    private String description;
    
    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.0", message = "El monto debe ser mayor que 0")
    @Column(nullable = false)
    private BigDecimal amount;
    
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Column(nullable = false)
    private String username;

    // Getters y Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public BigDecimal getAmount() { return amount; }

    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }
}