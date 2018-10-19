package lt.vilnius.saugus.saugusvilnius;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "good_citizen")
public class GoodCitizen {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long goodCitizenId;
    private String name;

    public GoodCitizen(String name) {
        this.name = name;
    }

    protected GoodCitizen() {
        this.goodCitizenId = goodCitizenId;
        this.name = name;
    }

    public Long getGoodCitizenId() {
        return goodCitizenId;
    }

    public String getName() {
        return name;
    }
}
