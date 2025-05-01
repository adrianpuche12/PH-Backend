package balance.service;

import balance.model.SalaryPayment;
import balance.repository.SalaryPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryPaymentService {

    @Autowired
    private SalaryPaymentRepository salaryPaymentRepository;

    public List<SalaryPayment> findAll() {
        return salaryPaymentRepository.findAll();
    }

    public Optional<SalaryPayment> findById(Long id) {
        return salaryPaymentRepository.findById(id);
    }

    public SalaryPayment save(SalaryPayment salaryPayment) {
        return salaryPaymentRepository.save(salaryPayment);
    }

    public void deleteById(Long id) {
        salaryPaymentRepository.deleteById(id);
    }

    // 🔍 Filtro por store ID
    public List<SalaryPayment> findByStoreId(Long storeId) {
        return salaryPaymentRepository.findByStoreId(storeId);
    }
}
