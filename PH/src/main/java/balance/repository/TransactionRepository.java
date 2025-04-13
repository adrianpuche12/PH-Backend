package balance.repository;

import balance.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Transaction> findByType(String type);
    List<Transaction> findByDateBetweenAndStoreId(LocalDate startDate, LocalDate endDate, Long storeId);
    List<Transaction> findByStoreId(Long storeId); // Filtrado por local
}
