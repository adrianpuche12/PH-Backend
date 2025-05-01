package balance.service;

import balance.model.SupplierPayment;
import balance.repository.SupplierPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierPaymentService {

    @Autowired
    private SupplierPaymentRepository supplierPaymentRepository;

    public List<SupplierPayment> findAll() {
        return supplierPaymentRepository.findAll();
    }

    public Optional<SupplierPayment> findById(Long id) {
        return supplierPaymentRepository.findById(id);
    }

    public SupplierPayment save(SupplierPayment supplierPayment) {
        return supplierPaymentRepository.save(supplierPayment);
    }

    public void deleteById(Long id) {
        supplierPaymentRepository.deleteById(id);
    }

    // ✅ Filtro por store
    public List<SupplierPayment> findByStoreId(Long storeId) {
        return supplierPaymentRepository.findByStoreId(storeId);
    }
}
