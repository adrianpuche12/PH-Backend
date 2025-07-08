package balance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "gasto_admin")
public class GastoAdmin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "La fecha es obligatoria")
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;
    
    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;
    
    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres")
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    
    @NotNull(message = "El porcentaje de Danli es obligatorio")
    @Min(value = 0, message = "El porcentaje de Danli debe ser mayor o igual a 0")
    @Max(value = 100, message = "El porcentaje de Danli debe ser menor o igual a 100")
    @Column(name = "porcentaje_danli", nullable = false)
    private Integer porcentajeDanli;
    
    @NotNull(message = "El porcentaje de El Paraíso es obligatorio")
    @Min(value = 0, message = "El porcentaje de El Paraíso debe ser mayor o igual a 0")
    @Max(value = 100, message = "El porcentaje de El Paraíso debe ser menor o igual a 100")
    @Column(name = "porcentaje_paraiso", nullable = false)
    private Integer porcentajeParaiso;
    
    @NotNull(message = "El monto de Danli es obligatorio")
    @DecimalMin(value = "0.00", message = "El monto de Danli debe ser mayor o igual a 0")
    @Column(name = "monto_danli", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoDanli;
    
    @NotNull(message = "El monto de El Paraíso es obligatorio")
    @DecimalMin(value = "0.00", message = "El monto de El Paraíso debe ser mayor o igual a 0")
    @Column(name = "monto_paraiso", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoParaiso;
    
    @Column(name = "username", nullable = false, length = 100)
    private String username = "admin_user";
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "image_uri", length = 500)
    private String imageUri;

    // Constructores
    public GastoAdmin() {
        this.createdAt = LocalDateTime.now();
    }
    
    public GastoAdmin(LocalDate fecha, BigDecimal monto, String descripcion, 
                     Integer porcentajeDanli, Integer porcentajeParaiso) {
        this();
        this.fecha = fecha;
        this.monto = monto;
        this.descripcion = descripcion;
        this.porcentajeDanli = porcentajeDanli;
        this.porcentajeParaiso = porcentajeParaiso;
        calcularMontos();
    }
    
    // Métodos de lifecycle
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        calcularMontos();
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        calcularMontos();
    }
    
    // Método para calcular montos automáticamente
    private void calcularMontos() {
        if (monto != null && porcentajeDanli != null && porcentajeParaiso != null) {
            this.montoDanli = monto.multiply(BigDecimal.valueOf(porcentajeDanli))
                                 .divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
            this.montoParaiso = monto.multiply(BigDecimal.valueOf(porcentajeParaiso))
                                   .divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
        }
    }
    
    // Método de validación personalizada
    public boolean isValidPercentages() {
        if (porcentajeDanli == null || porcentajeParaiso == null) {
            return false;
        }
        return (porcentajeDanli + porcentajeParaiso) == 100;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public BigDecimal getMonto() {
        return monto;
    }
    
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
        calcularMontos(); // Recalcular cuando cambia el monto
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Integer getPorcentajeDanli() {
        return porcentajeDanli;
    }
    
    public void setPorcentajeDanli(Integer porcentajeDanli) {
        this.porcentajeDanli = porcentajeDanli;
        calcularMontos(); // Recalcular cuando cambia el porcentaje
    }
    
    public Integer getPorcentajeParaiso() {
        return porcentajeParaiso;
    }
    
    public void setPorcentajeParaiso(Integer porcentajeParaiso) {
        this.porcentajeParaiso = porcentajeParaiso;
        calcularMontos(); // Recalcular cuando cambia el porcentaje
    }
    
    public BigDecimal getMontoDanli() {
        return montoDanli;
    }
    
    public void setMontoDanli(BigDecimal montoDanli) {
        this.montoDanli = montoDanli;
    }
    
    public BigDecimal getMontoParaiso() {
        return montoParaiso;
    }
    
    public void setMontoParaiso(BigDecimal montoParaiso) {
        this.montoParaiso = montoParaiso;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImageUri() { 
        return imageUri; 
    }

    public void setImageUri(String imageUri) { 
        this.imageUri = imageUri; 
    }
    
    @Override
    public String toString() {
        return "GastoAdmin{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", monto=" + monto +
                ", descripcion='" + descripcion + '\'' +
                ", porcentajeDanli=" + porcentajeDanli +
                ", porcentajeParaiso=" + porcentajeParaiso +
                ", montoDanli=" + montoDanli +
                ", montoParaiso=" + montoParaiso +
                ", username='" + username + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}