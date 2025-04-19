package balance.service;

import balance.dto.AllOperationsDTO;
import balance.model.ClosingDeposit;
import balance.model.SupplierPayment;
import balance.model.SalaryPayment;
import balance.repository.ClosingDepositRepository;
import balance.repository.SupplierPaymentRepository;
import balance.repository.SalaryPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FormsService {

    @Autowired
    private ClosingDepositRepository closingDepositRepository;

    @Autowired
    private SupplierPaymentRepository supplierPaymentRepository;

    @Autowired
    private SalaryPaymentRepository salaryPaymentRepository;

    // Métodos para obtener operaciones
    public List<AllOperationsDTO> getAllOperations() {
        List<AllOperationsDTO> allOperations = new ArrayList<>();

        allOperations.addAll(
                closingDepositRepository.findAll().stream()
                        .map(AllOperationsDTO::fromClosingDeposit)
                        .collect(Collectors.toList())
        );

        allOperations.addAll(
                supplierPaymentRepository.findAll().stream()
                        .map(AllOperationsDTO::fromSupplierPayment)
                        .collect(Collectors.toList())
        );

        allOperations.addAll(
                salaryPaymentRepository.findAll().stream()
                        .map(AllOperationsDTO::fromSalaryPayment)
                        .collect(Collectors.toList())
        );

        allOperations.sort((t1, t2) -> {
            if (t1.getDate() == null) return 1;
            if (t2.getDate() == null) return -1;
            return t2.getDate().compareTo(t1.getDate());
        });

        return allOperations;
    }

    public List<AllOperationsDTO> getOperationsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<AllOperationsDTO> allOperations = new ArrayList<>();

        closingDepositRepository.findByDepositDateBetween(startDate, endDate)
                .stream()
                .map(AllOperationsDTO::fromClosingDeposit)
                .forEach(allOperations::add);

        supplierPaymentRepository.findByPaymentDateBetween(startDate, endDate)
                .stream()
                .map(AllOperationsDTO::fromSupplierPayment)
                .forEach(allOperations::add);

        salaryPaymentRepository.findByDepositDateBetween(startDate, endDate)
                .stream()
                .map(AllOperationsDTO::fromSalaryPayment)
                .forEach(allOperations::add);

        allOperations.sort((o1, o2) -> {
            if (o1.getDate() == null) return 1;
            if (o2.getDate() == null) return -1;
            return o2.getDate().compareTo(o1.getDate());
        });

        return allOperations;
    }

    // Método para filtrar por store
    public List<AllOperationsDTO> getOperationsByStore(Long storeId) {
        List<AllOperationsDTO> allOperations = new ArrayList<>();

        closingDepositRepository.findByStoreId(storeId)
                .stream()
                .map(AllOperationsDTO::fromClosingDeposit)
                .forEach(allOperations::add);

        supplierPaymentRepository.findByStoreId(storeId)
                .stream()
                .map(AllOperationsDTO::fromSupplierPayment)
                .forEach(allOperations::add);

        salaryPaymentRepository.findByStoreId(storeId)
                .stream()
                .map(AllOperationsDTO::fromSalaryPayment)
                .forEach(allOperations::add);

        allOperations.sort((o1, o2) -> {
            if (o1.getDate() == null) return 1;
            if (o2.getDate() == null) return -1;
            return o2.getDate().compareTo(o1.getDate());
        });

        return allOperations;
    }

    // Método para filtrar por fecha y store
    public List<AllOperationsDTO> getOperationsByDateRangeAndStore(LocalDate startDate, LocalDate endDate, Long storeId) {
        List<AllOperationsDTO> allOperations = new ArrayList<>();

        closingDepositRepository.findByDepositDateBetweenAndStoreId(startDate, endDate, storeId)
                .stream()
                .map(AllOperationsDTO::fromClosingDeposit)
                .forEach(allOperations::add);

        supplierPaymentRepository.findByPaymentDateBetweenAndStoreId(startDate, endDate, storeId)
                .stream()
                .map(AllOperationsDTO::fromSupplierPayment)
                .forEach(allOperations::add);

        salaryPaymentRepository.findByDepositDateBetweenAndStoreId(startDate, endDate, storeId)
                .stream()
                .map(AllOperationsDTO::fromSalaryPayment)
                .forEach(allOperations::add);

        allOperations.sort((o1, o2) -> {
            if (o1.getDate() == null) return 1;
            if (o2.getDate() == null) return -1;
            return o2.getDate().compareTo(o1.getDate());
        });

        return allOperations;
    }

    // Métodos para guardar operaciones
    public ClosingDeposit saveClosingDeposit(ClosingDeposit deposit) {
        deposit.setDepositDate(LocalDate.now());
        return closingDepositRepository.save(deposit);
    }

    public List<ClosingDeposit> getClosingDeposits(LocalDate startDate, LocalDate endDate) {
        return closingDepositRepository.findByDepositDateBetween(startDate, endDate);
    }

    public List<ClosingDeposit> getAllClosingDeposits() {
        return closingDepositRepository.findAll();
    }



    public SupplierPayment saveSupplierPayment(SupplierPayment payment) {
        if (payment.getPaymentDate() == null) {
            payment.setPaymentDate(LocalDate.now());
        }
        return supplierPaymentRepository.save(payment);
    }

    public List<SupplierPayment> getSupplierPayments(LocalDate startDate, LocalDate endDate) {
        return supplierPaymentRepository.findByPaymentDateBetween(startDate, endDate);
    }

    public List<SupplierPayment> getAllSupplierPayments() {
        return supplierPaymentRepository.findAll();
    }

    public SalaryPayment saveSalaryPayment(SalaryPayment payment) {
        if (payment.getDepositDate() == null) {
            payment.setDepositDate(LocalDate.now());
        }
        return salaryPaymentRepository.save(payment);
    }

    public List<SalaryPayment> getAllSalaryPayments() {
        return salaryPaymentRepository.findAll();
    }

    //Metodos de filtros por local

    // 🔍 Filtro por store ID
    public List<ClosingDeposit> findByStoreId(Long storeId) {
        return closingDepositRepository.findByStoreId(storeId);
    }



    // Métodos de actualización (PUT)
    public ClosingDeposit updateClosingDeposit(Long id, ClosingDeposit updatedDeposit) {
        ClosingDeposit existingDeposit = closingDepositRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ClosingDeposit no encontrado con id " + id));
        existingDeposit.setAmount(updatedDeposit.getAmount());
        existingDeposit.setUsername(updatedDeposit.getUsername());
        existingDeposit.setClosingsCount(updatedDeposit.getClosingsCount());
        existingDeposit.setPeriodStart(updatedDeposit.getPeriodStart());
        existingDeposit.setPeriodEnd(updatedDeposit.getPeriodEnd());
        if (updatedDeposit.getStore() != null) {
            existingDeposit.setStore(updatedDeposit.getStore());
        }

        return closingDepositRepository.save(existingDeposit);
    }

    public SupplierPayment updateSupplierPayment(Long id, SupplierPayment updatedPayment) {
        SupplierPayment existingPayment = supplierPaymentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SupplierPayment no encontrado con id " + id));
        existingPayment.setAmount(updatedPayment.getAmount());
        //SexistingPayment.setDescription(updatedPayment.getDescription());
        existingPayment.setUsername(updatedPayment.getUsername());
        existingPayment.setSupplier(updatedPayment.getSupplier());
        if (updatedPayment.getStore() != null) {
            existingPayment.setStore(updatedPayment.getStore());
        }

        return supplierPaymentRepository.save(existingPayment);
    }

    public SalaryPayment updateSalaryPayment(Long id, SalaryPayment updatedPayment) {
        SalaryPayment existingPayment = salaryPaymentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SalaryPayment no encontrado con id " + id));
        existingPayment.setAmount(updatedPayment.getAmount());
        existingPayment.setDescription(updatedPayment.getDescription());
        existingPayment.setUsername(updatedPayment.getUsername());
        if (updatedPayment.getDepositDate() != null) {
            existingPayment.setDepositDate(updatedPayment.getDepositDate());
        }
        if (updatedPayment.getStore() != null) {
            existingPayment.setStore(updatedPayment.getStore());
        }

        return salaryPaymentRepository.save(existingPayment);
    }

    // Métodos de eliminación (DELETE)
    public void deleteClosingDeposit(Long id) {
        if (!closingDepositRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ClosingDeposit no encontrado con id " + id);
        }
        closingDepositRepository.deleteById(id);
    }

    public void deleteSupplierPayment(Long id) {
        if (!supplierPaymentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SupplierPayment no encontrado con id " + id);
        }
        supplierPaymentRepository.deleteById(id);
    }

    public void deleteSalaryPayment(Long id) {
        if (!salaryPaymentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SalaryPayment no encontrado con id " + id);
        }
        salaryPaymentRepository.deleteById(id);
    }
}