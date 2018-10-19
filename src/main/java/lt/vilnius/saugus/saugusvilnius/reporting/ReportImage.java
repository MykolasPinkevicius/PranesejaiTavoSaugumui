package lt.vilnius.saugus.saugusvilnius.reporting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReportImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "report_image_id")
    private Long reportImageId;

    private String content;

    public Long getReportImageId() {
        return reportImageId;
    }

    public void setReportImageId(Long reportImageId) {
        this.reportImageId = reportImageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
