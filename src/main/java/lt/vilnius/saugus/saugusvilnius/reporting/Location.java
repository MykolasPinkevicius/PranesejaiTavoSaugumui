package lt.vilnius.saugus.saugusvilnius.reporting;

import java.math.BigDecimal;

public class Location {

    private BigDecimal longitude;
    private BigDecimal latitude;

    public Location(BigDecimal longitude, BigDecimal latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}
