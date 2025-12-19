public interface EmployeeProfileRepository
        extends JpaRepository<EmployeeProfile, Long> {
    Optional<EmployeeProfile> findByEmployeeId(String employeeId);
    List<EmployeeProfile> findByTeamName(String teamName);
}
