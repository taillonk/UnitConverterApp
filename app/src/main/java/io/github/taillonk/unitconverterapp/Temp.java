package io.github.taillonk.unitconverterapp;

public class Temp {

    public static double celsiusToFahrenheit (double celsius) {
        return ((celsius * 1.8) + 32);
    }

    public static double celsiusToKelvin (double celsius) {
        return (celsius + 273.15);
    }

    public static double fahrenheitToCelsius (double fahrenheit) {
        return ((fahrenheit -32) / 1.8);
    }

    public static double fahrenheitToKelvin (double fahrenheit) {
        return (((fahrenheit - 32) * 5) / 9 + 273.15);
    }

    public static double kelvinToFahrenheit (double kelvin) {
        return ((kelvin - 273.15) * 1.8 + 32);
    }

    public static double kelvinToCelsius (double kelvin) {
        return (kelvin - 273.15);
    }

}
