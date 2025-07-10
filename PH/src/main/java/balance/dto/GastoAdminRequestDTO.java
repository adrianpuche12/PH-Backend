package balance.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class GastoAdminRequestDTO {
    
    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;
    
    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    private BigDecimal monto;
    
    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres")
    private String descripcion;
    
    @NotBlank(message = "El tipo es obligatorio")
    @Pattern(regexp = "income|expense", message = "El tipo debe ser 'income' o 'expense'")
    private String tipo;
    
    @NotNull(message = "El porcentaje de Danli es obligatorio")
    @Min(value = 0, message = "El porcentaje de Danli debe ser mayor o igual a 0")
    @Max(value = 100, message = "El porcentaje de Danli debe ser menor o igual a 100")
    private Integer porcentajeDanli;
    
    @NotNull(message = "El porcentaje de El Paraíso es obligatorio")
    @Min(value = 0, message = "El porcentaje de El Paraíso debe ser mayor o igual a 0")
    @Max(value = 100, message = "El porcentaje de El Paraíso debe ser menor o igual a 100")
    private Integer porcentajeParaiso;
    
    // Constructores
    public GastoAdminRequestDTO() {}
    
    public GastoAdminRequestDTO(LocalDate fecha, BigDecimal monto, String descripcion, 
                              String tipo, Integer porcentajeDanli, Integer porcentajeParaiso) {
        this.fecha = fecha;
        this.monto = monto;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.porcentajeDanli = porcentajeDanli;
        this.porcentajeParaiso = porcentajeParaiso;
    }
    
    // Método de validación personalizada
    public boolean isValidPercentages() {
        if (porcentajeDanli == null || porcentajeParaiso == null) {
            return false;
        }
        return (porcentajeDanli + porcentajeParaiso) == 100;
    }
    
    // Getters y Setters
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
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public Integer getPorcentajeDanli() {
        return porcentajeDanli;
    }
    
    public void setPorcentajeDanli(Integer porcentajeDanli) {
        this.porcentajeDanli = porcentajeDanli;
    }
    
    public Integer getPorcentajeParaiso() {
        return porcentajeParaiso;
    }
    
    public void setPorcentajeParaiso(Integer porcentajeParaiso) {
        this.porcentajeParaiso = porcentajeParaiso;
    }
    
    @Override
    public String toString() {
        return "GastoAdminRequestDTO{" +
                "fecha=" + fecha +
                ", monto=" + monto +
                ", descripcion='" + descripcion + '\'' +
                ", tipo='" + tipo + '\'' +
                ", porcentajeDanli=" + porcentajeDanli +
                ", porcentajeParaiso=" + porcentajeParaiso +
                '}';
    }
}