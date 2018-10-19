package lt.vilnius.saugus.saugusvilnius.reporting;

import lt.vilnius.saugus.saugusvilnius.GoodCitizen;
import lt.vilnius.saugus.saugusvilnius.Location;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ReportDisturbanceDto {

    private Location location;
    private DisturbanceType disturbanceType;
    private GoodCitizen goodCitizen;
    private String description;

}
