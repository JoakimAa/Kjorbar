package no.hiof.kjorbar.model;

import com.google.firebase.database.Exclude;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Calculation {
    UUID uuid = UUID.randomUUID();
    @Exclude
    private String uid = uuid.toString();
    private CalculationLimit calculationLimit;
    private List<AlcoholUnit> alcoholUnits = new ArrayList<>();
    private double totalPerMill;
    private int userWeight;
    private String userGender;

    public Calculation() {}

    public Calculation(int userWeight, String userGender) {
        this.userWeight = userWeight;
        this.userGender = userGender;
    }

    public Calculation(String uid, CalculationLimit calculationLimit, ArrayList<AlcoholUnit> alcoholUnits, int userWeight, String userGender) {
        this.uid = uid;
        this.calculationLimit = calculationLimit;
        this.alcoholUnits = alcoholUnits;
        this.userWeight = userWeight;
        this.userGender = userGender;
    }

    public double getTotalPerMill(double time) {
        double totalGramOfAlcohol = 0;
        for (AlcoholUnit alcoholUnit : alcoholUnits) {
            totalGramOfAlcohol += alcoholUnit.getGramAlcoholPerUnit();
        }

        if (userGender.equals("Mann")) {
           totalPerMill = totalGramOfAlcohol / ((userWeight * 0.7) - (0.15 * time));

        }
        if (userGender.equals("Kvinne")) {
            totalPerMill = totalGramOfAlcohol / ((userWeight * 0.6) - (0.15 * time));
        }

        return totalPerMill;
    }

    @Exclude
    public String getUid() {
        return uid;
    }

    public CalculationLimit getCalculationLimit() {
        return calculationLimit;
    }

    public void setCalculationLimit(CalculationLimit calculationLimit) {
        this.calculationLimit = calculationLimit;
    }

    public ArrayList<AlcoholUnit> getAlcoholUnits() {
        return new ArrayList<>(alcoholUnits);
    }

    public void addAlcoholUnit(AlcoholUnit alcoholUnit) {
        alcoholUnits.add(alcoholUnit);
    }

    public void removeAlcoholUnit(AlcoholUnit alcoholUnit) {
        alcoholUnits.remove(alcoholUnit);
    }

    public int getUserWeight() {
        return userWeight;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserWeight(int userWeight) {
        this.userWeight = userWeight;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }
}

