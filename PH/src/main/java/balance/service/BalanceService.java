package balance.service;

import balance.model.Transaction;
import balance.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class BalanceService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Método para obtener todas las transacciones
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

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
}

