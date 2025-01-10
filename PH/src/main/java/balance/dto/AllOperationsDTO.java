package balance.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AllOperationsDTO {
    private Long id;
    private String type; // "CLOSING", "SUPPLIER", "SALARY"
    private BigDecimal amount;
    private LocalDate date;
    private String description;
    private String username;
    
    // Campos específicos de Closing Deposits
    private Integer closingsCount;
    private LocalDate periodStart;
    private LocalDate periodEnd;
    
    // Campos específicos de Supplier Payments
    private String supplier;

    // Constructores para cada tipo de operación
    public static AllOperationsDTO fromClosingDeposit(balance.model.ClosingDeposit deposit) {
        AllOperationsDTO dto = new AllOperationsDTO();
        dto.setId(deposit.getId());
        dto.setType("CLOSING");
        dto.setAmount(deposit.getAmount());
        dto.setDate(deposit.getDepositDate());
        dto.setUsername(deposit.getUsername());
        dto.setClosingsCount(deposit.getClosingsCount());
        dto.setPeriodStart(deposit.getPeriodStart());
        dto.setPeriodEnd(deposit.getPeriodEnd());
        dto.setDescription("Depósito de " + deposit.getClosingsCount() + " cierres");
        return dto;
    }

    public static AllOperationsDTO fromSupplierPayment(balance.model.SupplierPayment payment) {
        AllOperationsDTO dto = new AllOperationsDTO();
        dto.setId(payment.getId());
        dto.setType("SUPPLIER");
        dto.setAmount(payment.getAmount());
        dto.setDate(payment.getPaymentDate());
        dto.setUsername(payment.getUsername());
        dto.setSupplier(payment.getSupplier());
        dto.setDescription("Pago a proveedor: " + payment.getSupplier());
        return dto;
    }

    public static AllOperationsDTO fromSalaryPayment(balance.model.SalaryPayment payment) {
        AllOperationsDTO dto = new AllOperationsDTO();
        dto.setId(payment.getId());
        dto.setType("SALARY");
        dto.setAmount(payment.getAmount());
        dto.setDescription(payment.getDescription());
        dto.setUsername(payment.getUsername());
        return dto;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getClosingsCount() {
        return closingsCount;
    }

    public void setClosingsCount(Integer closingsCount) {
        this.closingsCount = closingsCount;
    }

    public LocalDate getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(LocalDate periodStart) {
        this.periodStart = periodStart;
    }

    public LocalDate getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(LocalDate periodEnd) {
        this.periodEnd = periodEnd;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}