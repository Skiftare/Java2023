package edu.project4.color;


public class StandardColourGenerator {
    public Colour getBlackColour(){
        return new Colour(0,0,0);
    }
    public static Colour getWhiteColour(){
        return new Colour(1,1,1);
    }
    public static Colour getRedColour(){
        return new Colour(1,0,0);
    }

    public Colour getOrangeColour(){
        return new Colour(1,0.5,0);
    }

    public static Colour getYellowColour(){
        return new Colour(1,1,0);
    }

    public static Colour getGreenColour(){
        return new Colour(0,1,0);
    }

    public static Colour getBlueColour(){
        return new Colour(0,0,1);
    }

    public static Colour getIndigoColour(){
        return new Colour(0.29,0,0.51);
    }

    public Colour getVioletColour(){
        return new Colour(0.93,0.51,0.93);
    }
}
