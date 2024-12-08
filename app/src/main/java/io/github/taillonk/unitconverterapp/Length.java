package io.github.taillonk.unitconverterapp;

public class Length {

    public static double inchesToMeters (double inches) {
        return (inches * 0.0254);
    }

    public static double inchesToFeet (double inches) {
        return (inches / 12);
    }

    public static double metersToInches (double meters) {
        return (meters * 39.37);
    }

    public static double metersToFeet (double meters) {
        return (meters * 3.28084);
    }

    public static double feetToInches (double feet) {
        return (feet * 12);
    }

    public static double feetToMeters (double feet) {
        return (feet * 0.3048);
    }



}
