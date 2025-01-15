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
        // Obtener las transacciones dentro del rango de fechas
        List<Transaction> transactions = transactionRepository.findByDateBetween(startDate, endDate);

        // Si no hay transacciones, retornar cero
        if (transactions == null || transactions.isEmpty()) {
            return BigDecimal.ZERO;
        }

        // Calcular el ingreso total (income)
        BigDecimal income = transactions.stream()
                .filter(t -> "income".equalsIgnoreCase(t.getType()))
                .map(t -> new BigDecimal(t.getAmount().toString())) // Asegurarse de que Amount sea BigDecimal
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Calcular el gasto total (expense)
        BigDecimal expense = transactions.stream()
                .filter(t -> "expense".equalsIgnoreCase(t.getType()))
                .map(t -> new BigDecimal(t.getAmount().toString())) // Asegurarse de que Amount sea BigDecimal
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Retornar el balance: ingreso - gasto
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
