package no.hiof.kjorbar.model;

public class AlcoholUnit {
    private String name, producer, category;
    private int percent, amount;

    public AlcoholUnit(String name, String producer, String category, int percent, int amount) {
        this.name = name;
        this.producer = producer;
        this.category = category;
        this.percent = percent;
        this.amount = amount;
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

    public int getPercent() {
        return percent;
    }

    public int getAmount() {
        return amount;
    }
}