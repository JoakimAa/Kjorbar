package no.hiof.kjorbar.model;

import java.time.LocalDateTime;

public class CalculationLimit {
    private int maxPerMill, maxAlcoholUnits;
    private LocalDateTime drivableAt;

    public CalculationLimit(int maxPerMill, int maxAlcoholUnits, LocalDateTime drivableAt) {
        this.maxPerMill = maxPerMill;
        this.maxAlcoholUnits = maxAlcoholUnits;
        this.drivableAt = drivableAt;
    }

    public int getMaxPerMill() {
        return maxPerMill;
    }

    public void setMaxPerMill(int maxPerMill) {
        this.maxPerMill = maxPerMill;
    }

    public int getMaxAlcoholUnits() {
        return maxAlcoholUnits;
    }

    public void setMaxAlcoholUnits(int maxAlcoholUnits) {
        this.maxAlcoholUnits = maxAlcoholUnits;
    }

    public LocalDateTime getDrivableAt() {
        return drivableAt;
    }

    public void setDrivableAt(LocalDateTime drivableAt) {
        this.drivableAt = drivableAt;
    }
}
