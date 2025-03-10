package balance.repository;

import balance.model.SalaryPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryPaymentRepository extends JpaRepository<SalaryPayment, Long> {}