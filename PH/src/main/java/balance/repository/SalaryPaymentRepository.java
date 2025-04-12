package balance.repository;

import balance.model.SalaryPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalaryPaymentRepository extends JpaRepository<SalaryPayment, Long> {
    List<SalaryPayment> findByDepositDateBetween(LocalDate startDate, LocalDate endDate);

    List<SalaryPayment> findByStoreId(Long storeId);  // 🔍 Búsqueda por local
}