package lt.vilnius.saugus.saugusvilnius;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GoodCitizenRepository extends CrudRepository<GoodCitizen, Long> {
    List<GoodCitizen> findAll();
}
