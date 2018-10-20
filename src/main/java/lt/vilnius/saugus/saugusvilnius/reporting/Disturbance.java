package lt.vilnius.saugus.saugusvilnius.reporting;

import lt.vilnius.saugus.saugusvilnius.GoodCitizen;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Disturbance {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long disturbanceId;
    private String location;

    @Enumerated(EnumType.STRING)
    private DisturbanceType disturbanceType;

    @ManyToOne
    private GoodCitizen goodCitizen;
    private String description;

    @OneToMany
    private List<ReportImage> reportImages;    

    public Disturbance(String location, DisturbanceType disturbanceType, GoodCitizen goodCitizen, String description,
			List<ReportImage> reportImages) {
		super();
		this.location = location;
		this.disturbanceType = disturbanceType;
		this.goodCitizen = goodCitizen;
		this.description = description;
		this.reportImages = reportImages;
	}
    
    protected Disturbance() {
    	
    }

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
