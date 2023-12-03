package edu.project4.helperCore;

import edu.project4.render.ImageFormat;

public class ArgsState {
    private  boolean symmetry;
    private  boolean gamma;
    private ImageFormat format;

    public ArgsState(boolean symmetry, boolean gamma, ImageFormat format) {
        this.symmetry = symmetry;
        this.gamma = gamma;
        this.format = format;
    }
    public boolean getSymmertyState(){
        return symmetry;
    }
    public boolean getGammaState(){
        return gamma;
    }
    public ImageFormat getFormat(){
        return format;
    }
    public void setSymmetryState(boolean symmetry) {
        this.symmetry = symmetry;
    }

    public void setGammaState(boolean gamma) {
        this.gamma = gamma;
    }

    public void setFormat(ImageFormat format) {
        this.format = format;
    }

}
