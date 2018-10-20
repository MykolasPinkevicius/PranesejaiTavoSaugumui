package lt.vilnius.saugus.saugusvilnius.images;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping("")
	public String saveImage(@RequestParam("imageValue") String imageValue, HttpServletRequest request) {
		try {
			System.out.println(imageValue);

			byte[] imageByte = Base64.decodeBase64(imageValue);

			ReportImage reportImage = new ReportImage();
			
			reportImage.setContent(imageByte.toString());
			
			repository.save(reportImage);
			
			return "success ";
		} catch (Exception e) {
			return "error = " + e;
		}
	}
}
