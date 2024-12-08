package io.github.taillonk.unitconverterapp;

public class Weight {

    public static double gramsToPounds (double grams) {
        return (grams * 0.00220462);
    }

    public static double gramsToOunces (double grams) {
        return (grams *  0.035274);
    }

    public static double poundsToGrams (double pounds) {
        return (pounds * 453.592);
    }

    public static double poundsToOunces (double pounds) {
        return (pounds * 16);
    }

    public static double ouncesToGrams (double ounces) {
        return (ounces * 28.34952);
    }

    public static double ouncesToPounds(double ounces) {
        return (ounces / 16);
    }
}
