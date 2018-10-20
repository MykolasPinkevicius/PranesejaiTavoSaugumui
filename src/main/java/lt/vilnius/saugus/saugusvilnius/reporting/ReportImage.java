package lt.vilnius.saugus.saugusvilnius.reporting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class ReportImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_image_id")
    private Long reportImageId;

    @Lob
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