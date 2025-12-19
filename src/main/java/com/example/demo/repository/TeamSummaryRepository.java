public interface TeamSummaryRepository
        extends JpaRepository<TeamSummaryRecord, Long> {
    Optional<TeamSummaryRecord> findByTeamNameAndSummaryDate(String teamName, LocalDate date);
}
