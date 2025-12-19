@Service
@Transactional
public class ProductivityServiceImpl implements ProductivityService {

    private final ProductivityMetricRepository repo;

    public ProductivityServiceImpl(ProductivityMetricRepository repo) {
        this.repo = repo;
    }

    @Override
    public ProductivityMetricRecord submitMetrics(ProductivityMetricRecord record) {
        if (repo.findByEmployeeIdAndDate(record.getEmployeeId(), record.getDate()).isPresent()) {
            throw new IllegalStateException("duplicate entry");
        }
        record.setProductivityScore(calculateScore(record));
        return repo.save(record);
    }

    @Override
    public Double calculateScore(ProductivityMetricRecord r) {
        return (r.getHoursLogged() * 0.4)
                + (r.getTasksCompleted() * 0.4)
                + (r.getMeetingsAttended() * 0.2);
    }
}
