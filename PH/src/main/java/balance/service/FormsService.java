package balance.service;

import balance.dto.AllOperationsDTO;
import balance.model.*;
import balance.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * Obtiene todas las operaciones del sistema, incluyendo depósitos de cierres,
     * pagos a proveedores y pagos de salarios.
     *
     * @return Lista de todas las operaciones convertidas a DTO
     */
    public List<AllOperationsDTO> getAllOperations() {
        List<AllOperationsDTO> allOperations = new ArrayList<>();

        // Agregar depósitos de cierres
        allOperations.addAll(
            closingDepositRepository.findAll().stream()
                .map(AllOperationsDTO::fromClosingDeposit)
                .collect(Collectors.toList())
        );

        // Agregar pagos a proveedores
        allOperations.addAll(
            supplierPaymentRepository.findAll().stream()
                .map(AllOperationsDTO::fromSupplierPayment)
                .collect(Collectors.toList())
        );

        // Agregar pagos de salarios
        allOperations.addAll(
            salaryPaymentRepository.findAll().stream()
                .map(AllOperationsDTO::fromSalaryPayment)
                .collect(Collectors.toList())
        );

        // Ordenar por fecha descendente (más reciente primero)
        allOperations.sort((t1, t2) -> {
            if (t1.getDate() == null) return 1;
            if (t2.getDate() == null) return -1;
            return t2.getDate().compareTo(t1.getDate());
        });

        return allOperations;
    }

    /**
     * Obtiene todas las operaciones filtradas por rango de fechas.
     *
     * @param startDate fecha inicial del rango
     * @param endDate fecha final del rango
     * @return Lista de operaciones dentro del rango especificado
     */
    public List<AllOperationsDTO> getOperationsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<AllOperationsDTO> allOperations = new ArrayList<>();

        // Obtener y filtrar depósitos de cierres
        closingDepositRepository.findByDepositDateBetween(startDate, endDate)
            .stream()
            .map(AllOperationsDTO::fromClosingDeposit)
            .forEach(allOperations::add);

        // Obtener y filtrar pagos a proveedores
        supplierPaymentRepository.findByPaymentDateBetween(startDate, endDate)
            .stream()
            .map(AllOperationsDTO::fromSupplierPayment)
            .forEach(allOperations::add);

        // Ordenar por fecha descendente
        allOperations.sort((o1, o2) -> {
            if (o1.getDate() == null) return 1;
            if (o2.getDate() == null) return -1;
            return o2.getDate().compareTo(o1.getDate());
        });

        return allOperations;
    }

    /**
     * Guarda un nuevo depósito de cierre
     * @param deposit el depósito a guardar
     * @return el depósito guardado con su ID asignado
     */
    public ClosingDeposit saveClosingDeposit(ClosingDeposit deposit) {
        deposit.setDepositDate(LocalDate.now());
        return closingDepositRepository.save(deposit);
    }

    /**
     * Obtiene los depósitos de cierre en un rango de fechas
     * @param startDate fecha inicial
     * @param endDate fecha final
     * @return lista de depósitos en el rango especificado
     */
    public List<ClosingDeposit> getClosingDeposits(LocalDate startDate, LocalDate endDate) {
        return closingDepositRepository.findByDepositDateBetween(startDate, endDate);
    }

    /**
     * Obtiene todos los depósitos de cierre registrados.
     *
     * @return lista de todos los depósitos de cierre
     */
    public List<ClosingDeposit> getAllClosingDeposits() {
        return closingDepositRepository.findAll();
    }

    /**
     * Guarda un nuevo pago a proveedor
     * @param payment el pago a guardar
     * @return el pago guardado con su ID asignado
     */
    public SupplierPayment saveSupplierPayment(SupplierPayment payment) {
        payment.setPaymentDate(LocalDate.now());
        return supplierPaymentRepository.save(payment);
    }

    /**
     * Obtiene los pagos a proveedores en un rango de fechas
     * @param startDate fecha inicial
     * @param endDate fecha final
     * @return lista de pagos en el rango especificado
     */
    public List<SupplierPayment> getSupplierPayments(LocalDate startDate, LocalDate endDate) {
        return supplierPaymentRepository.findByPaymentDateBetween(startDate, endDate);
    }

    /**
     * Obtiene todos los pagos a proveedores registrados.
     *
     * @return lista de todos los pagos a proveedores
     */
    public List<SupplierPayment> getAllSupplierPayments() {
        return supplierPaymentRepository.findAll();
    }

    /**
     * Guarda un nuevo pago de salario
     * @param payment el pago a guardar
     * @return el pago guardado con su ID asignado
     */
    public SalaryPayment saveSalaryPayment(SalaryPayment payment) {
        return salaryPaymentRepository.save(payment);
    }

    /**
     * Obtiene todos los pagos de salarios registrados.
     *
     * @return lista de todos los pagos de salarios
     */
    public List<SalaryPayment> getAllSalaryPayments() {
        return salaryPaymentRepository.findAll();
    }

    
}