public interface AnomalyRuleRepository
        extends JpaRepository<AnomalyRule, Long> {
    Optional<AnomalyRule> findByRuleCode(String ruleCode);
    List<AnomalyRule> findByActiveTrue();
}
