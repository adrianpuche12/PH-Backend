package balance.controller;

import balance.model.*;
import balance.service.FormsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

/*
 Endpoints para la gestión de depósitos de cierres, pagos a proveedores y salarios.
 */

@RestController
@RequestMapping("/api/forms")
public class FormsController {
    
    @Autowired
    private FormsService formsService;

    /**
     * Crea un nuevo depósito de cierre.
     * 
     * @param deposit Objeto ClosingDeposit con los datos del depósito a crear
     * @return ResponseEntity con el depósito creado y su ID asignado
     * 
     * Ejemplo de JSON requerido:
     * {
     *     "closingsCount": 5,
     *     "amount": 1000.00,
     *     "periodStart": "2024-01-01",
     *     "periodEnd": "2024-01-31",
     *     "username": "usuario123"
     * }
     */
    @PostMapping("/closing-deposits")
    public ResponseEntity<ClosingDeposit> addClosingDeposit(
            @Valid @RequestBody ClosingDeposit deposit) {
        return ResponseEntity.ok(formsService.saveClosingDeposit(deposit));
    }

    /**
     * Obtiene todos los depósitos de cierre.
     * 
     * @return ResponseEntity con la lista de todos los depósitos
     */
    @GetMapping("/closing-deposits/all")
    public ResponseEntity<List<ClosingDeposit>> getAllClosingDeposits() {
        return ResponseEntity.ok(formsService.getAllClosingDeposits());
    }

    /**
     * Obtiene todos los depósitos de cierre en un rango de fechas.
     * 
     * @param startDate Fecha inicial del rango (formato: YYYY-MM-DD)
     * @param endDate Fecha final del rango (formato: YYYY-MM-DD)
     * @return ResponseEntity con la lista de depósitos encontrados
     */
    @GetMapping("/closing-deposits")
    public ResponseEntity<List<ClosingDeposit>> getClosingDeposits(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(formsService.getClosingDeposits(startDate, endDate));
    }

    // 🆕 Endpoint para filtrar por store ID
    @GetMapping("/closing-deposits/store/{storeId}")
    public ResponseEntity<List<ClosingDeposit>> getByStoreId(@PathVariable Long storeId) {
        return ResponseEntity.ok(formsService.findByStoreId(storeId));
    }

    /**
     * Registra un nuevo pago a proveedor.
     * 
     * @param payment Objeto SupplierPayment con los datos del pago a registrar
     * @return ResponseEntity con el pago registrado y su ID asignado
     * 
     * Ejemplo de JSON requerido:
     * {
     *     "supplier": "Pollo Rey",
     *     "amount": 5000.00,
     *     "username": "usuario123"
     * }
     */
    @PostMapping("/supplier-payments")
    public ResponseEntity<SupplierPayment> addSupplierPayment(
            @Valid @RequestBody SupplierPayment payment) {
        return ResponseEntity.ok(formsService.saveSupplierPayment(payment));
    }

    /**
     * Obtiene todos los pagos a proveedores.
     * 
     * @return ResponseEntity con la lista de todos los pagos a proveedores
     */
    @GetMapping("/supplier-payments/all")
    public ResponseEntity<List<SupplierPayment>> getAllSupplierPayments() {
        return ResponseEntity.ok(formsService.getAllSupplierPayments());
    }

    /**
     * Obtiene todos los pagos a proveedores en un rango de fechas.
     * 
     * @param startDate Fecha inicial del rango (formato: YYYY-MM-DD)
     * @param endDate Fecha final del rango (formato: YYYY-MM-DD)
     * @return ResponseEntity con la lista de pagos encontrados
     */
    @GetMapping("/supplier-payments")
    public ResponseEntity<List<SupplierPayment>> getSupplierPayments(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(formsService.getSupplierPayments(startDate, endDate));
    }

    /**
     * Registra un nuevo pago de salario.
     * 
     * @param payment Objeto SalaryPayment con los datos del pago de salario
     * @return ResponseEntity con el pago registrado y su ID asignado
     * 
     * Ejemplo de JSON requerido:
     * {
     *     "description": "Pago quincenal",
     *     "amount": 3000.00,
     *     "username": "usuario123"
     * }
     */
    @PostMapping("/salary-payments")
    public ResponseEntity<SalaryPayment> addSalaryPayment(
            @Valid @RequestBody SalaryPayment payment) {
        return ResponseEntity.ok(formsService.saveSalaryPayment(payment));
    }

     /**
     * Obtiene todos los pagos de salarios registrados.
     * 
     * @return ResponseEntity con la lista de todos los pagos de salarios
     */
    @GetMapping("/salary-payments")
    public ResponseEntity<List<SalaryPayment>> getAllSalaryPayments() {
        return ResponseEntity.ok(formsService.getAllSalaryPayments());
    }


    
}