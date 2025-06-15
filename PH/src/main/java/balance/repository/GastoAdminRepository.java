package balance.repository;

import balance.model.GastoAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GastoAdminRepository extends JpaRepository<GastoAdmin, Long> {
    
    // ================================================================
    // CONSULTAS BÁSICAS POR FECHA
    // ================================================================
    
    /**
     * Buscar gastos administrativos por fecha específica
     */
    List<GastoAdmin> findByFecha(LocalDate fecha);
    
    /**
     * Buscar gastos administrativos en un rango de fechas
     */
    List<GastoAdmin> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
    
    /**
     * Buscar gastos administrativos después de una fecha
     */
    List<GastoAdmin> findByFechaAfter(LocalDate fecha);
    
    /**
     * Buscar gastos administrativos antes de una fecha
     */
    List<GastoAdmin> findByFechaBefore(LocalDate fecha);
    
    // ================================================================
    // CONSULTAS POR MONTO
    // ================================================================
    
    /**
     * Buscar gastos administrativos con monto mayor al especificado
     */
    List<GastoAdmin> findByMontoGreaterThan(BigDecimal monto);
    
    /**
     * Buscar gastos administrativos con monto menor al especificado
     */
    List<GastoAdmin> findByMontoLessThan(BigDecimal monto);
    
    /**
     * Buscar gastos administrativos con monto en un rango
     */
    List<GastoAdmin> findByMontoBetween(BigDecimal montoMinimo, BigDecimal montoMaximo);
    
    // ================================================================
    // CONSULTAS POR DESCRIPCIÓN
    // ================================================================
    
    /**
     * Buscar gastos administrativos que contengan el texto en la descripción
     */
    List<GastoAdmin> findByDescripcionContainingIgnoreCase(String descripcion);
    
    /**
     * Buscar gasto administrativo por descripción exacta y fecha
     */
    Optional<GastoAdmin> findByDescripcionAndFecha(String descripcion, LocalDate fecha);
    
    // ================================================================
    // CONSULTAS POR USUARIO
    // ================================================================
    
    /**
     * Buscar gastos administrativos por usuario
     */
    List<GastoAdmin> findByUsername(String username);
    
    // ================================================================
    // CONSULTAS POR PORCENTAJES
    // ================================================================
    
    /**
     * Buscar gastos administrativos por porcentaje de Danli
     */
    List<GastoAdmin> findByPorcentajeDanli(Integer porcentaje);
    
    /**
     * Buscar gastos administrativos por porcentaje de El Paraíso
     */
    List<GastoAdmin> findByPorcentajeParaiso(Integer porcentaje);
    
    /**
     * Buscar gastos administrativos por ambos porcentajes
     */
    List<GastoAdmin> findByPorcentajeDanliAndPorcentajeParaiso(Integer porcentajeDanli, Integer porcentajeParaiso);
    
    // ================================================================
    // CONSULTAS ESTADÍSTICAS PERSONALIZADAS
    // ================================================================
    
    /**
     * Contar gastos administrativos por fecha
     */
    @Query("SELECT COUNT(g) FROM GastoAdmin g WHERE g.fecha = :fecha")
    Long countByFecha(@Param("fecha") LocalDate fecha);
    
    /**
     * Sumar monto total en un rango de fechas
     */
    @Query("SELECT SUM(g.monto) FROM GastoAdmin g WHERE g.fecha BETWEEN :fechaInicio AND :fechaFin")
    BigDecimal sumMontoByFechaBetween(@Param("fechaInicio") LocalDate fechaInicio, 
                                     @Param("fechaFin") LocalDate fechaFin);
    
    /**
     * Sumar monto de Danli en un rango de fechas
     */
    @Query("SELECT SUM(g.montoDanli) FROM GastoAdmin g WHERE g.fecha BETWEEN :fechaInicio AND :fechaFin")
    BigDecimal sumMontoDanliByFechaBetween(@Param("fechaInicio") LocalDate fechaInicio, 
                                          @Param("fechaFin") LocalDate fechaFin);
    
    /**
     * Sumar monto de El Paraíso en un rango de fechas
     */
    @Query("SELECT SUM(g.montoParaiso) FROM GastoAdmin g WHERE g.fecha BETWEEN :fechaInicio AND :fechaFin")
    BigDecimal sumMontoParaisoByFechaBetween(@Param("fechaInicio") LocalDate fechaInicio, 
                                            @Param("fechaFin") LocalDate fechaFin);
    
    /**
     * Promedio de monto en un rango de fechas
     */
    @Query("SELECT AVG(g.monto) FROM GastoAdmin g WHERE g.fecha BETWEEN :fechaInicio AND :fechaFin")
    BigDecimal avgMontoByFechaBetween(@Param("fechaInicio") LocalDate fechaInicio, 
                                     @Param("fechaFin") LocalDate fechaFin);
    
    // ================================================================
    // CONSULTAS PARA REPORTES
    // ================================================================
    
    /**
     * Obtener gastos administrativos para reporte en rango de fechas
     */
    @Query("SELECT g FROM GastoAdmin g WHERE g.fecha BETWEEN :fechaInicio AND :fechaFin ORDER BY g.fecha DESC")
    List<GastoAdmin> findReportByFechaBetween(@Param("fechaInicio") LocalDate fechaInicio, 
                                             @Param("fechaFin") LocalDate fechaFin);
    
    /**
     * Obtener gastos administrativos altos (mayor o igual a monto mínimo)
     */
    @Query("SELECT g FROM GastoAdmin g WHERE g.monto >= :montoMinimo ORDER BY g.monto DESC")
    List<GastoAdmin> findGastosAltos(@Param("montoMinimo") BigDecimal montoMinimo);
    
    // ================================================================
    // CONSULTAS DEL MES ACTUAL
    // ================================================================
    
    /**
     * Obtener gastos administrativos del mes actual
     */
    @Query("SELECT g FROM GastoAdmin g WHERE YEAR(g.fecha) = YEAR(CURRENT_DATE) AND MONTH(g.fecha) = MONTH(CURRENT_DATE)")
    List<GastoAdmin> findGastosDelMesActual();
    
    /**
     * Sumar gastos administrativos del mes actual
     */
    @Query("SELECT SUM(g.monto) FROM GastoAdmin g WHERE YEAR(g.fecha) = YEAR(CURRENT_DATE) AND MONTH(g.fecha) = MONTH(CURRENT_DATE)")
    BigDecimal sumGastosDelMesActual();
    
    // ================================================================
    // CONSULTAS DEL AÑO ACTUAL
    // ================================================================
    
    /**
     * Obtener gastos administrativos del año actual
     */
    @Query("SELECT g FROM GastoAdmin g WHERE YEAR(g.fecha) = YEAR(CURRENT_DATE) ORDER BY g.fecha DESC")
    List<GastoAdmin> findGastosDelAnioActual();
    
    /**
     * Sumar gastos administrativos del año actual
     */
    @Query("SELECT SUM(g.monto) FROM GastoAdmin g WHERE YEAR(g.fecha) = YEAR(CURRENT_DATE)")
    BigDecimal sumGastosDelAnioActual();
    
    // ================================================================
    // VERIFICACIONES DE EXISTENCIA
    // ================================================================
    
    /**
     * Verificar si existe un gasto con descripción y fecha específicas
     */
    boolean existsByDescripcionAndFecha(String descripcion, LocalDate fecha);
    
    /**
     * Verificar si existen gastos en una fecha específica
     */
    boolean existsByFecha(LocalDate fecha);
    
    // ================================================================
    // TOP GASTOS Y ÚLTIMOS CREADOS
    // ================================================================
    
    /**
     * Obtener gastos administrativos ordenados por monto (mayor a menor)
     */
    @Query("SELECT g FROM GastoAdmin g ORDER BY g.monto DESC")
    List<GastoAdmin> findTopGastosByMonto();
    
    /**
     * Obtener últimos gastos administrativos creados
     */
    @Query("SELECT g FROM GastoAdmin g ORDER BY g.createdAt DESC")
    List<GastoAdmin> findUltimosGastosCreados();
}