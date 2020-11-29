package no.hiof.kjorbar.model;

public class AlcoholUnit {
    private String name, producer, category, amountType;
    private int amount;
    private double percent, gramAlcoholPerUnit;
    public AlcoholUnit() {
    }

    public AlcoholUnit(String name, String producer, String category, double percent, int amount, String amountType, double gramAlcoholPerUnit) {
        this.name = name;
        this.producer = producer;
        this.category = category;
        this.percent = percent;
        this.amount = amount;
        this.amountType = amountType;
        this.gramAlcoholPerUnit = gramAlcoholPerUnit;
    }

    public String getName() {
        return name;
    }

    public String getProducer() {
        return producer;
    }

    public String getCategory() {
        return category;
    }

    public double getPercent() {
        return percent;
    }

    public int getAmount() {
        return amount;
    }

    public String getAmountType() {
        return amountType;
    }

    public double getGramAlcoholPerUnit() {
        return gramAlcoholPerUnit;
    }
}
