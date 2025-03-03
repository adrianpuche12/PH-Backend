package balance.controller;

import balance.dto.AllOperationsDTO;
import balance.model.ClosingDeposit;
import balance.model.SupplierPayment;
import balance.model.SalaryPayment;
import balance.service.FormsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/operations")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class OperationsController {

    @Autowired
    private FormsService formsService;

    /**
     * Obtiene todas las operaciones del sistema.
     * Si se proporcionan fechas, filtra las operaciones dentro del rango especificado.
     *
     * Ejemplos de uso en Postman:
     * GET http://localhost:8080/api/operations/all
     * GET http://localhost:8080/api/operations/all?startDate=2024-01-01&endDate=2024-01-31
     */
    @GetMapping("/all")
    public ResponseEntity<List<AllOperationsDTO>> getAllOperations(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        if (startDate != null && endDate != null) {
            return ResponseEntity.ok(formsService.getOperationsByDateRange(startDate, endDate));
        }
        return ResponseEntity.ok(formsService.getAllOperations());
    }

    /**
     * Actualiza una operación existente.
     * La URL debe incluir el tipo (CLOSING, SUPPLIER o SALARY) y el id de la operación.
     *
     * Ejemplo en Postman para una operación CLOSING:
     * PUT http://localhost:8080/api/operations/CLOSING/1
     *
     * Cuerpo (JSON) para CLOSING:
     * {
     *   "amount": 1000.0,
     *   "username": "user1",
     *   "closingsCount": 2,
     *   "periodStart": "2024-01-01",
     *   "periodEnd": "2024-01-31"
     * }
     *
     * Para SUPPLIER y SALARY se deben enviar los campos correspondientes (incluyendo "description" donde aplique).
     */
    @PutMapping("/{type}/{id}")
    public ResponseEntity<AllOperationsDTO> updateOperation(
            @PathVariable String type,
            @PathVariable Long id,
            @RequestBody AllOperationsDTO dto) {
        switch (type.toUpperCase()) {
            case "CLOSING":
                // Se actualiza sin descripción, ya que el modelo ClosingDeposit no la tiene.
                ClosingDeposit closingDeposit = new ClosingDeposit();
                closingDeposit.setAmount(dto.getAmount());
                closingDeposit.setUsername(dto.getUsername());
                closingDeposit.setClosingsCount(dto.getClosingsCount());
                closingDeposit.setPeriodStart(dto.getPeriodStart());
                closingDeposit.setPeriodEnd(dto.getPeriodEnd());
                ClosingDeposit updatedClosingDeposit = formsService.updateClosingDeposit(id, closingDeposit);
                return ResponseEntity.ok(AllOperationsDTO.fromClosingDeposit(updatedClosingDeposit));
            case "SUPPLIER":
                SupplierPayment supplierPayment = new SupplierPayment();
                supplierPayment.setAmount(dto.getAmount());
                //SsupplierPayment.setDescription(dto.getDescription());
                supplierPayment.setUsername(dto.getUsername());
                supplierPayment.setSupplier(dto.getSupplier());
                SupplierPayment updatedSupplierPayment = formsService.updateSupplierPayment(id, supplierPayment);
                return ResponseEntity.ok(AllOperationsDTO.fromSupplierPayment(updatedSupplierPayment));
            case "SALARY":
                SalaryPayment salaryPayment = new SalaryPayment();
                salaryPayment.setAmount(dto.getAmount());
                salaryPayment.setDescription(dto.getDescription());
                salaryPayment.setUsername(dto.getUsername());
                SalaryPayment updatedSalaryPayment = formsService.updateSalaryPayment(id, salaryPayment);
                return ResponseEntity.ok(AllOperationsDTO.fromSalaryPayment(updatedSalaryPayment));
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de operación inválido");
        }
    }

    /**
     * Elimina una operación existente.
     * La URL debe incluir el tipo (CLOSING, SUPPLIER o SALARY) y el id de la operación.
     *
     * Ejemplo en Postman:
     * DELETE http://localhost:8080/api/operations/SUPPLIER/3
     */
    @DeleteMapping("/{type}/{id}")
    public ResponseEntity<Void> deleteOperation(
            @PathVariable String type,
            @PathVariable Long id) {
        switch (type.toUpperCase()) {
            case "CLOSING":
                formsService.deleteClosingDeposit(id);
                break;
            case "SUPPLIER":
                formsService.deleteSupplierPayment(id);
                break;
            case "SALARY":
                formsService.deleteSalaryPayment(id);
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de operación inválido");
        }
        return ResponseEntity.noContent().build();
    }
}
