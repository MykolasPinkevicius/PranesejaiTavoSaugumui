package lt.vilnius.saugus.saugusvilnius.exp;

import lt.vilnius.saugus.saugusvilnius.GoodCitizen;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UserLevel {

    private GoodCitizen goodCitizen;
    private BigDecimal level;

    public UserLevel(GoodCitizen goodCitizen) {
        this.goodCitizen = goodCitizen;
        this.level = getExp().divide(new BigDecimal("100"), 0, RoundingMode.FLOOR);
    }

    public GoodCitizen getGoodCitizen() {
        return goodCitizen;
    }

    public BigDecimal getLevel() {
        return level;
    }

    public BigDecimal getExp() {
        return goodCitizen.getExp();
    }
}
