package lt.vilnius.saugus.saugusvilnius.reporting;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lt.vilnius.saugus.saugusvilnius.GoodCitizen;
import lt.vilnius.saugus.saugusvilnius.GoodCitizenRepository;
import lt.vilnius.saugus.saugusvilnius.ResourceNotFoundException;
import lt.vilnius.saugus.saugusvilnius.images.ImagesRepository;

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
    public List<Disturbance> getDisturbances() {
        return disturbanceRepository.findAll();
    }

    @GetMapping(value = "/v1/disturbances/{disturbanceId}")
    public Disturbance getDisturbance(@PathVariable Long disturbanceId) {
        return disturbanceRepository.findById(disturbanceId)
                .orElseThrow(DisturbanceNotFoundException::new);
    }

    private Disturbance toEntity(ReportDisturbanceDto reportDisturbanceDto) {
        GoodCitizen goodCitizen = goodCitizenRepository.findById(reportDisturbanceDto.getGoodCitizenId())
                .orElseThrow(() -> new ResourceNotFoundException());

        List<ReportImage> reportImages = reportDisturbanceDto.getReportImages()
                .stream()
                .map(imageId -> {
                    return imagesRepository.findById(imageId)
                            .orElseThrow(() -> new ResourceNotFoundException());

                }).collect(Collectors.toList());

        return new Disturbance(
                reportDisturbanceDto.getLocation(),
                DisturbanceType.valueOf(reportDisturbanceDto.getDisturbanceType()),
                goodCitizen,
                reportDisturbanceDto.getDescription(),
                reportImages
        );
    }
}