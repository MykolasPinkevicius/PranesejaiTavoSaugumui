package lt.vilnius.saugus.saugusvilnius.images;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.vilnius.saugus.saugusvilnius.ResourceNotFoundException;
import lt.vilnius.saugus.saugusvilnius.reporting.ReportImage;

@RestController
@RequestMapping("/images")
public class ImagesController {

	@Autowired
	ImagesRepository repository;

	@GetMapping(produces="application/json")
	public List<String> getImages() {

		List<ReportImage> images = repository.findAll();

		return images.stream().map(i -> i.getContent()).collect(Collectors.toList());
	}

	@CrossOrigin(origins="*")
	@GetMapping(value="/{imageId}")
	public String getImageById(@PathVariable Long imageId) {

		return repository.findById(imageId)
				.orElseThrow(ResourceNotFoundException::new)
				.getContent();
	}

	@PostMapping
	public Long saveImage(@RequestBody ImageDto imageDto, HttpServletRequest request) {
//			System.out.println(imageDto.getContent());

		ReportImage reportImage = new ReportImage();

		reportImage.setContent(imageDto.getContent());

		repository.save(reportImage);

		return reportImage.getReportImageId();
	}
}