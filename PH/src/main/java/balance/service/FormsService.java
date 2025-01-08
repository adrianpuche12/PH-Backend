package balance.service;

import balance.model.*;
import balance.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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