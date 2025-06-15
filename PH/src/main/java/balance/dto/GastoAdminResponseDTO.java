package balance.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class GastoAdminResponseDTO {
    
    private String mensaje;
    private Integer transaccionesCreadas;
    private BigDecimal montoTotal;
    private List<TransaccionCreada> transacciones;
    private Long gastoAdminId;
    
    // Constructores
    public GastoAdminResponseDTO() {}
    
    public GastoAdminResponseDTO(String mensaje, Integer transaccionesCreadas, 
                               BigDecimal montoTotal, List<TransaccionCreada> transacciones,
                               Long gastoAdminId) {
        this.mensaje = mensaje;
        this.transaccionesCreadas = transaccionesCreadas;
        this.montoTotal = montoTotal;
        this.transacciones = transacciones;
        this.gastoAdminId = gastoAdminId;
    }
    
    // Clase interna para las transacciones creadas
    public static class TransaccionCreada {
        private Long id;
        private String tipo;
        private BigDecimal monto;
        private LocalDate fecha;
        private String descripcion;
        private String local;
        private Integer porcentaje;
        
        public TransaccionCreada() {}
        
        public TransaccionCreada(Long id, String tipo, BigDecimal monto, LocalDate fecha,
                               String descripcion, String local, Integer porcentaje) {
            this.id = id;
            this.tipo = tipo;
            this.monto = monto;
            this.fecha = fecha;
            this.descripcion = descripcion;
            this.local = local;
            this.porcentaje = porcentaje;
        }
        
        // Getters y Setters
        public Long getId() { 
            return id; 
        }
        
        public void setId(Long id) { 
            this.id = id; 
        }
        
        public String getTipo() { 
            return tipo; 
        }
        
        public void setTipo(String tipo) { 
            this.tipo = tipo; 
        }
        
        public BigDecimal getMonto() { 
            return monto; 
        }
        
        public void setMonto(BigDecimal monto) { 
            this.monto = monto; 
        }
        
        public LocalDate getFecha() { 
            return fecha; 
        }
        
        public void setFecha(LocalDate fecha) { 
            this.fecha = fecha; 
        }
        
        public String getDescripcion() { 
            return descripcion; 
        }
        
        public void setDescripcion(String descripcion) { 
            this.descripcion = descripcion; 
        }
        
        public String getLocal() { 
            return local; 
        }
        
        public void setLocal(String local) { 
            this.local = local; 
        }
        
        public Integer getPorcentaje() { 
            return porcentaje; 
        }
        
        public void setPorcentaje(Integer porcentaje) { 
            this.porcentaje = porcentaje; 
        }
        
        @Override
        public String toString() {
            return "TransaccionCreada{" +
                    "id=" + id +
                    ", tipo='" + tipo + '\'' +
                    ", monto=" + monto +
                    ", fecha=" + fecha +
                    ", descripcion='" + descripcion + '\'' +
                    ", local='" + local + '\'' +
                    ", porcentaje=" + porcentaje +
                    '}';
        }
    }
    
    // Getters y Setters principales
    public String getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public Integer getTransaccionesCreadas() {
        return transaccionesCreadas;
    }
    
    public void setTransaccionesCreadas(Integer transaccionesCreadas) {
        this.transaccionesCreadas = transaccionesCreadas;
    }
    
    public BigDecimal getMontoTotal() {
        return montoTotal;
    }
    
    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }
    
    public List<TransaccionCreada> getTransacciones() {
        return transacciones;
    }
    
    public void setTransacciones(List<TransaccionCreada> transacciones) {
        this.transacciones = transacciones;
    }
    
    public Long getGastoAdminId() {
        return gastoAdminId;
    }
    
    public void setGastoAdminId(Long gastoAdminId) {
        this.gastoAdminId = gastoAdminId;
    }
    
    @Override
    public String toString() {
        return "GastoAdminResponseDTO{" +
                "mensaje='" + mensaje + '\'' +
                ", transaccionesCreadas=" + transaccionesCreadas +
                ", montoTotal=" + montoTotal +
                ", transacciones=" + transacciones +
                ", gastoAdminId=" + gastoAdminId +
                '}';
    }
}