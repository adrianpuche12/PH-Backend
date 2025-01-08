package balance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "supplier_payments")
public class SupplierPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String supplier;
    
    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.0", message = "El monto debe ser mayor que 0")
    @Column(nullable = false)
    private BigDecimal amount;
    
    @Column(nullable = false)
    private LocalDate paymentDate;
    
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Column(nullable = false)
    private String username;


    @PrePersist
    public void prePersist() {
        if (paymentDate == null) {
            paymentDate = LocalDate.now();
        }
    }

    // Getters y Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getSupplier() { return supplier; }

    public void setSupplier(String supplier) { this.supplier = supplier; }

    public BigDecimal getAmount() { return amount; }

    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDate getPaymentDate() { return paymentDate; }

    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }
}