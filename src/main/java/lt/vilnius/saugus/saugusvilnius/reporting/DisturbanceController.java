package lt.vilnius.saugus.saugusvilnius.reporting;

import lt.vilnius.saugus.saugusvilnius.GoodCitizen;
import lt.vilnius.saugus.saugusvilnius.GoodCitizenRepository;
import lt.vilnius.saugus.saugusvilnius.ResourceNotFoundException;
import lt.vilnius.saugus.saugusvilnius.images.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DisturbanceController {

    private DisturbanceRepository disturbanceRepository;
    private GoodCitizenRepository goodCitizenRepository;
    private ImagesRepository imagesRepository;

    @Autowired
    public DisturbanceController(DisturbanceRepository disturbanceRepository,
                                 GoodCitizenRepository goodCitizenRepository,
                                 ImagesRepository imagesRepository) {
        this.disturbanceRepository = disturbanceRepository;
        this.goodCitizenRepository = goodCitizenRepository;
        this.imagesRepository = imagesRepository;

    }

    @PostMapping(value = "/v1/disturbances")
    public Disturbance createDisturbance(@RequestBody ReportDisturbanceDto reportDisturbanceDto) {
        return disturbanceRepository.save(toEntity(reportDisturbanceDto));
    }

    @CrossOrigin(origins="*")
    @GetMapping(value = "/v1/disturbances")
    public List<DisturbanceDto> getDisturbances() {
        List<DisturbanceDto> disturbanceDtos = disturbanceRepository
                .findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        disturbanceDtos.forEach(disturbanceDto -> disturbanceDto.getReportImages().forEach(reportImage -> reportImage.setContent(null)));
        return disturbanceDtos;
    }

    @CrossOrigin(origins="*")
    @GetMapping(value = "/v1/disturbances/{disturbanceId}")
    public DisturbanceDto getDisturbance(@PathVariable Long disturbanceId) {
        return this.toDto(disturbanceRepository.findById(disturbanceId)
                                  .orElseThrow(DisturbanceNotFoundException::new));
    }

    private Disturbance toEntity(ReportDisturbanceDto reportDisturbanceDto) {
        GoodCitizen goodCitizen = goodCitizenRepository.findById(reportDisturbanceDto.getGoodCitizenId())
                .orElseThrow(ResourceNotFoundException::new);

        List<ReportImage> reportImages = reportDisturbanceDto.getReportImages()
                .stream()
                .map(imageId ->
                             imagesRepository.findById(imageId)
                                     .orElseThrow(ResourceNotFoundException::new)
                )
                .collect(Collectors.toList());

        return new Disturbance(
                reportDisturbanceDto.getLocation(),
                DisturbanceType.valueOf(reportDisturbanceDto.getDisturbanceType()),
                goodCitizen,
                reportDisturbanceDto.getDescription(),
                reportImages
        );
    }

    private DisturbanceDto toDto(Disturbance disturbance) {
        DisturbanceDto disturbanceDto = new DisturbanceDto();
        disturbanceDto.setDisturbanceId(disturbance.getDisturbanceId());

        String[] posArray = disturbance.getLocation().split(",");
        disturbanceDto.setLocation(new Location(new BigDecimal(posArray[0]), new BigDecimal(posArray[1])));

        disturbanceDto.setDisturbanceType(disturbance.getDisturbanceType());
        disturbanceDto.setGoodCitizen(disturbance.getGoodCitizen());
        disturbanceDto.setDescription(disturbance.getDescription());
        disturbanceDto.setReportImages(disturbance.getReportImages());

        return disturbanceDto;
    }
}