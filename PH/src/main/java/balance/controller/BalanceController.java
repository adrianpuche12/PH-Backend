package balance.controller;

import balance.model.Transaction;
import balance.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/transactions")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @PostMapping
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        System.out.println("Datos recibidos: " + transaction); // Verifica si el objeto tiene los datos correctos
        Transaction savedTransaction = balanceService.saveTransaction(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        BigDecimal balance = balanceService.calculateBalance(startDate, endDate);
        return ResponseEntity.ok(balance);
    }

    // Método que maneja las Solicitudes GET para obtener todas la lista de transacciones.
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = balanceService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

}
