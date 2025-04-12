package balance.repository;

import balance.model.ClosingDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClosingDepositRepository extends JpaRepository<ClosingDeposit, Long> {
    List<ClosingDeposit> findByDepositDateBetween(LocalDate startDate, LocalDate endDate);
    List<ClosingDeposit> findByStoreId(Long storeId);  // <<-- Filtro por ID de store
}