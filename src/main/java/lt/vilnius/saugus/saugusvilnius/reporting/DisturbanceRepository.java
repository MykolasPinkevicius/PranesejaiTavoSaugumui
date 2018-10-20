package lt.vilnius.saugus.saugusvilnius.reporting;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DisturbanceRepository extends CrudRepository<Disturbance, Long> {
    List<Disturbance> findAll();
}
