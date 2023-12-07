package edu.project4.nonlinear;

import edu.project4.Transformation;
import java.security.SecureRandom;

public class NonLinearCompose {
    private final Transformation [] variantsOfNonLinear = {
        new SinusoidalTransformation(),
        new SphericalTransformation(),
        new DiskTransformation(),
        new PolarTransformation(),
        new HeartTransformation()
    };

    public Transformation getNonLinearTransformationById(int id){
        if(id >= variantsOfNonLinear.length || id < 0){
            throw new RuntimeException("wrong id for geting NonLinearTransformationById");
        }
        return variantsOfNonLinear[id];
    }
    public Transformation getRangomNonLinear(){
        SecureRandom rand = new SecureRandom();
        int r = rand.nextInt(variantsOfNonLinear.length);
        return variantsOfNonLinear[r];
    }
}
