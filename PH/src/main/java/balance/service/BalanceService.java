package balance.service;

import balance.model.Transaction;
import balance.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BalanceService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Método para guardar una transacción (crear o actualizar)
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Método para obtener todas las transacciones
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Método para calcular el balance entre dos fechas
    public BigDecimal calculateBalance(LocalDate startDate, LocalDate endDate) {
        List<Transaction> transactions = transactionRepository.findByDateBetween(startDate, endDate);

        BigDecimal income = transactions.stream()
                .filter(t -> "income".equalsIgnoreCase(t.getType()))
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal expense = transactions.stream()
                .filter(t -> "expense".equalsIgnoreCase(t.getType()))
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return income.subtract(expense);
    }

    // Método para obtener una transacción por ID
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    // Método para eliminar una transacción por ID
    public boolean deleteTransaction(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            transactionRepository.delete(transaction.get());
            return true;
        }
        return false;
    }
}
