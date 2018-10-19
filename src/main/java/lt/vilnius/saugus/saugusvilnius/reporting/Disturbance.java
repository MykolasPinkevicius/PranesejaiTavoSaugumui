package lt.vilnius.saugus.saugusvilnius.reporting;

import lt.vilnius.saugus.saugusvilnius.GoodCitizen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Disturbance {

    @Id
    @GeneratedValue
    private Long disturbanceId;
    private String location;
    private DisturbanceType disturbanceType;

    @ManyToOne
    private GoodCitizen goodCitizen;
    private String description;

    @OneToMany
    private List<ReportImage> reportImages;

    public Long getDisturbanceId() {
        return disturbanceId;
    }

    public void setDisturbanceId(Long disturbanceId) {
        this.disturbanceId = disturbanceId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public DisturbanceType getDisturbanceType() {
        return disturbanceType;
    }

    public void setDisturbanceType(DisturbanceType disturbanceType) {
        this.disturbanceType = disturbanceType;
    }

    public GoodCitizen getGoodCitizen() {
        return goodCitizen;
    }

    public void setGoodCitizen(GoodCitizen goodCitizen) {
        this.goodCitizen = goodCitizen;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ReportImage> getReportImages() {
        return reportImages;
    }

    public void setReportImages(List<ReportImage> reportImages) {
        this.reportImages = reportImages;
    }
}
