package lt.vilnius.saugus.saugusvilnius.exp;

import lt.vilnius.saugus.saugusvilnius.GoodCitizen;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ExpServiceImpl implements ExpService {
    @Override
    public UserLevel addExp(GoodCitizen goodCitizen, BigDecimal expAmount) {
        return null;
    }

    @Override
    public UserLevel getUserLevel(GoodCitizen goodCitizen) {
        return null;
    }
}
