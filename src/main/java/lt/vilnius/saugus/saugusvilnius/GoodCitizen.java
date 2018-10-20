package lt.vilnius.saugus.saugusvilnius;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "good_citizen")
public class GoodCitizen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long goodCitizenId;
    private String name;
    private BigDecimal exp;

    public GoodCitizen(String name) {
        this.name = name;
        this.exp = BigDecimal.ZERO;
    }

    protected GoodCitizen() {
    }

    public Long getGoodCitizenId() {
        return goodCitizenId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getExp() {
        return exp;
    }

    public void addExp(BigDecimal exp) {
        this.exp = this.exp.add(exp);
    }
}
