package no.hiof.kjorbar.datahandler;

import java.util.ArrayList;
import java.util.List;

import no.hiof.kjorbar.model.AlcoholUnit;

public class UnitsDataHandler {
    private static List<AlcoholUnit> units = new ArrayList<>();

    public static List<AlcoholUnit> getUnits() {
        return units;
    }

    public static void addUnit(AlcoholUnit alcoholUnit) {
        units.add(alcoholUnit);
    }

    public static void setUnits(List<AlcoholUnit> units) {
        UnitsDataHandler.units = units;
    }
}
