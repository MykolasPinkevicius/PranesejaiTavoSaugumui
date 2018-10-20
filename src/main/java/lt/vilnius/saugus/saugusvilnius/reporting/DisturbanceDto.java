package lt.vilnius.saugus.saugusvilnius.reporting;

import lt.vilnius.saugus.saugusvilnius.GoodCitizen;

import java.util.List;

public class DisturbanceDto {

    private Long disturbanceId;
    private Location location;
    private DisturbanceType disturbanceType;
    private GoodCitizen goodCitizen;
    private String description;
    private List<ReportImage> reportImages;

    public Long getDisturbanceId() {
        return disturbanceId;
    }

    public void setDisturbanceId(Long disturbanceId) {
        this.disturbanceId = disturbanceId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
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
