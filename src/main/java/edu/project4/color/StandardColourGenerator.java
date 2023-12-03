package edu.project4.color;


public class StandardColourGenerator {
    public Color getBlackColour(){
        return new Color(0,0,0);
    }
    public static Color getWhiteColour(){
        return new Color(1,1,1);
    }
    public static Color getRedColour(){
        return new Color(1,0,0);
    }

    public Color getOrangeColour(){
        return new Color(1,0.5,0);
    }

    public static Color getYellowColour(){
        return new Color(1,1,0);
    }

    public static Color getGreenColour(){
        return new Color(0,1,0);
    }

    public static Color getBlueColour(){
        return new Color(0,0,1);
    }

    public static Color getIndigoColour(){
        return new Color(0.29,0,0.51);
    }

    public Color getVioletColour(){
        return new Color(0.93,0.51,0.93);
    }
}
