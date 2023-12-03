package edu.project4.color;

public class Colour {
    private static final int COLOR_MAX_VAL = 255;

    private static final double SRGB_C_LINEAR_THRESHOLD = 0.0031308;
    private static final double SRGB_PHI = 12.92;
    private static final double SRGB_EXPONENT = 2.4;

    private final double red, green, blue;

    /**
    initialization & checks for correct data
     **/
    private boolean checkForCorrectIncomeParameters(double... a){
        for(double each: a){
            if(each < 0 || each > 1){
                return false;
            }
        }
        return true;
    }

    public Colour(final double r, final double g, final double b) {
        if (!checkForCorrectIncomeParameters(r,g,b)) {
            throw new IllegalArgumentException(
                "The parameters must be in [0, 1]"
            );
        }

        red = r;
        green = g;
        blue = b;
    }

    /**
    Convinient output&debug
     **/
    @Override
    public String toString() {
        return "RGB: (" + red + ", " + green + ", " + blue + ")";
    }

    /**
     Getters
     **/
    public double getRedComponent() {
        return red;
    }

    public double getGreenComponent() {
        return green;
    }

    public double getBlueComponent() {
        return blue;
    }




}
