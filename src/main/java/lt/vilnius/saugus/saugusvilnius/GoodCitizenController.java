package lt.vilnius.saugus.saugusvilnius;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoodCitizenController {

    private GoodCitizenRepository goodCitizenRepository;

    @Autowired
    public GoodCitizenController(GoodCitizenRepository goodCitizenRepository) {
        this.goodCitizenRepository = goodCitizenRepository;
    }

    @GetMapping("/v1/good-citizens/{goodCitizenId}")
    public GoodCitizen getGoodCitizen(@RequestParam Long goodCitizenId) {
        return goodCitizenRepository.findById(goodCitizenId)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("/v1/good-citizens")
    public List<GoodCitizen> getGoodCitizens() {
        return goodCitizenRepository.findAll();
    }

}
