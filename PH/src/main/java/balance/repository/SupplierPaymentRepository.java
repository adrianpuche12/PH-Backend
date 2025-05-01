package balance.repository;

import balance.model.SupplierPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SupplierPaymentRepository extends JpaRepository<SupplierPayment, Long> {
    List<SupplierPayment> findByPaymentDateBetween(LocalDate startDate, LocalDate endDate);
    List<SupplierPayment> findByPaymentDateBetweenAndStoreId(LocalDate startDate, LocalDate endDate, Long storeId);
    List<SupplierPayment> findByStoreId(Long storeId); // 🔍 Para filtrar por local
}