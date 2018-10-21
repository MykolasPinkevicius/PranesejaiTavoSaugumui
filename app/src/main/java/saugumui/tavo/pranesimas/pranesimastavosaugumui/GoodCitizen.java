package saugumui.tavo.pranesimas.pranesimastavosaugumui;

import java.math.BigDecimal;

public class GoodCitizen {
    private  Long goodCitizenId;
    private String name;
    private BigDecimal exp;

    public Long getGoodCitizenId() {
        return goodCitizenId;
    }

    public void setGoodCitizenId(Long goodCitizenId) {
        this.goodCitizenId = goodCitizenId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getExp() {
        return exp;
    }

    public void setExp(BigDecimal exp) {
        this.exp = exp;
    }
}
