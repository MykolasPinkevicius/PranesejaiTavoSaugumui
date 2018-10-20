package lt.vilnius.saugus.saugusvilnius.exp;

import lt.vilnius.saugus.saugusvilnius.GoodCitizen;

import java.math.BigDecimal;

public interface UserLevelService {

    UserLevel addExp(GoodCitizen goodCitizen, BigDecimal expAmount);

    UserLevel getUserLevel(GoodCitizen goodCitizen);

}
