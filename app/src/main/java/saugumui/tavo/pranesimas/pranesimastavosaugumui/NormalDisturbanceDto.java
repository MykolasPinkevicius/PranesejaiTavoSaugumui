package saugumui.tavo.pranesimas.pranesimastavosaugumui;

public class NormalDisturbanceDto {

    private Long disturbanceId;
    private Location location;
    private String disturbanceType;
    private String description;
    private String status;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisturbanceType() {
        return disturbanceType;
    }

    public void setDisturbanceType(String disturbanceType) {
        this.disturbanceType = disturbanceType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
