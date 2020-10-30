package no.hiof.kjorbar.model;

import com.google.firebase.database.Exclude;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Calculation {
    @Exclude
    private String uid;
    private CalculationLimit calculationLimit;
    private ArrayList<AlcoholUnit> alcoholUnits = new ArrayList<>();

    public Calculation() {}

    public Calculation(String uid) {
        this.uid = uid;
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
        return new ArrayList<AlcoholUnit>(alcoholUnits);
    }

    public void addAlcoholUnit(AlcoholUnit alcoholUnit) {
        alcoholUnits.add(alcoholUnit);
    }
}

