package lt.vilnius.saugus.saugusvilnius.images;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import lt.vilnius.saugus.saugusvilnius.reporting.ReportImage;

public interface ImagesRepository extends CrudRepository<ReportImage, Long> {
	List<ReportImage> findAll();

}
