package lt.vilnius.saugus.saugusvilnius.images;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
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

//	@GetMapping(produces = "image/jpg")
//	public @ResponseBody byte[] getImages() throws EncoderException {
//
//		List<ReportImage> images = repository.findAll();
//
//		try {
//
//			Base64 base64 = new Base64();
//			byte[] imageByte = base64.;
//
//			// Retrieve image from the classpath.
//			InputStream is = this.getClass().getResourceAsStream(images.get(0).getContent());
//
//			// Prepare buffered image.
//			BufferedImage img = ImageIO.read(is);
//
//			// Create a byte array output stream.
//			ByteArrayOutputStream bao = new ByteArrayOutputStream();
//
//			// Write to output stream
//			ImageIO.write(img, "jpg", bao);
//
//			return bao.toByteArray();
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//	}

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
