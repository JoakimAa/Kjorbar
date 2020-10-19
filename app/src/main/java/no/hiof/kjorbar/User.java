package no.hiof.kjorbar;

import java.time.LocalDateTime;

public class User {
    private int weight, height, age, maxPerMill, maxPerUnit;
    private String gender;
    private LocalDateTime date;

    public User(int weight, int height, int age, int maxPerMill, int maxPerUnit, String gender, LocalDateTime date) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.maxPerMill = maxPerMill;
        this.maxPerUnit = maxPerUnit;
        this.gender = gender;
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMaxPerMill() {
        return maxPerMill;
    }

    public void setMaxPerMill(int maxPerMill) {
        this.maxPerMill = maxPerMill;
    }

    public int getMaxPerUnit() {
        return maxPerUnit;
    }

    public void setMaxPerUnit(int maxPerUnit) {
        this.maxPerUnit = maxPerUnit;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
