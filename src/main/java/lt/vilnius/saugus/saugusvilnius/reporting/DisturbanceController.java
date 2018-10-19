package lt.vilnius.saugus.saugusvilnius.reporting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DisturbanceController {

    private DisturbanceRepository disturbanceRepository;

    @Autowired
    public DisturbanceController(DisturbanceRepository disturbanceRepository) {
        this.disturbanceRepository = disturbanceRepository;
    }

    @PostMapping(value = "/v1/disturbances")
    public Disturbance createDisturbance(@RequestBody Disturbance disturbance) {
        disturbanceRepository.save(disturbance);

        return disturbance;
    }

    @GetMapping(value = "/v1/disturbances/{disturbanceId}")
    public Disturbance getDisturbance(@RequestParam Long disturbanceId) {
        return disturbanceRepository.findById(disturbanceId)
                .orElseThrow(DisturbanceNotFoundException::new);
    }
}
