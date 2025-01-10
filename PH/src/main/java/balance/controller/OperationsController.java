package balance.controller;

import balance.dto.AllOperationsDTO;
import balance.service.FormsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/operations")
@CrossOrigin(origins = "*")
public class OperationsController {
    
    @Autowired
    private FormsService formsService;

    /**
     * Obtiene todas las operaciones del sistema.
     * 
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
     */
    @GetMapping("/all")
    public ResponseEntity<List<AllOperationsDTO>> getAllOperations() {
        return ResponseEntity.ok(formsService.getAllOperations());
    }
}