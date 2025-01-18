package balance.controller;

import balance.dto.AllOperationsDTO;
import balance.service.FormsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/operations")
@CrossOrigin(origins = "*")
public class OperationsController {
    
    @Autowired
    private FormsService formsService;

    /**
     * Obtiene todas las operaciones del sistema.
     * Si se proporcionan fechas, filtra las operaciones dentro del rango especificado.
     *
     * @param startDate Fecha inicial del rango en formato YYYY-MM-DD (opcional)
     * @param endDate Fecha final del rango en formato YYYY-MM-DD (opcional)
     * @return Lista de todas las operaciones en formato DTO
     *
     * @apiNote Este endpoint devuelve un array de operaciones con la siguiente estructura:
     * {
     *   "id": number,
     *   "type": string ("CLOSING" | "SUPPLIER" | "SALARY"),
     *   "amount": number,
     *   "date": string (YYYY-MM-DD),
     *   "description": string,
     *   "username": string,
     *   "closingsCount"?: number,          // Solo para type="CLOSING"
     *   "periodStart"?: string,            // Solo para type="CLOSING"
     *   "periodEnd"?: string,              // Solo para type="CLOSING"
     *   "supplier"?: string                // Solo para type="SUPPLIER"
     * }
     * 
     * Para filtrar por fechas, usar los parámetros opcionales:
     * GET /api/operations/all?startDate=2024-01-01&endDate=2024-01-31
     * Si no se proporcionan fechas, devuelve todas las operaciones.
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
}