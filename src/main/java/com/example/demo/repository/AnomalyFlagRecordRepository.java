public interface AnomalyFlagRepository
        extends JpaRepository<AnomalyFlagRecord, Long> {
    List<AnomalyFlagRecord> findByEmployeeId(Long employeeId);
    List<AnomalyFlagRecord> findByResolvedFalse();
}
