package com.example.currencyconverter;

public class Converter {

    public static final int EURO = 0;
    public static final int USDOLLAR = 1;
    public static final int YEN = 2;
    public static final int RUBEL = 3;
    public static final int SCHWEIZERFRANKEN = 4;
    public static final int ÖSTERREICHISCHERSCHILLING = 5;

    private static double otherToEuro(int currency, double value){
        double retVal;
        switch(currency){
            case EURO: retVal = value; break;
            case USDOLLAR: retVal = value*0.886357; break;
            case YEN: retVal = value*0.0077748395; break;
            case RUBEL: retVal = value*0.012; break;
            case SCHWEIZERFRANKEN: retVal = value*0.95; break;
            case ÖSTERREICHISCHERSCHILLING: retVal = value*0.0726728; break;
            default: retVal = Double.NEGATIVE_INFINITY;
        }
        return retVal;
    }

    private static double euroToOther(int currency, double value){
        double retVal;
        switch(currency){
            case EURO: retVal = value; break;
            case USDOLLAR: retVal = value/0.886357; break;
            case YEN: retVal = value/0.0077748395; break;
            case RUBEL: retVal = value/0.012; break;
            case SCHWEIZERFRANKEN: retVal = value/0.95; break;
            case ÖSTERREICHISCHERSCHILLING: retVal = value/0.0726728; break;
            default: retVal = Double.NEGATIVE_INFINITY;
        }
        return retVal;
    }

    public static double convert(int currencyInput, int currencyOutput, double inputValue){
        double euro = otherToEuro(currencyInput, inputValue);
        double retVal = euroToOther(currencyOutput, euro);
        return retVal;
    }

}
