package balance.service;
import balance.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import balance.model.Transaction;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> findAll() {
        return transactionRepository.findAllOrderByDateDesc();
    }

    public Optional<Transaction> findById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> findByStoreId(Long storeId) {
        return transactionRepository.findByStoreId(storeId);
    }

    public List<Transaction> findByDateBetweenAndStoreId(LocalDate startDate, LocalDate endDate, Long storeId) {
        return transactionRepository.findByDateBetweenAndStoreIdOrderByDateDesc(startDate, endDate, storeId);
    }
}
