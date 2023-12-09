package edu.project4.transformation.nonlinear;

import edu.project4.transformation.Transformation;
import edu.project4.transformation.nonlinear.variations.DiskTransformation;
import edu.project4.transformation.nonlinear.variations.HeartTransformation;
import edu.project4.transformation.nonlinear.variations.PolarTransformation;
import edu.project4.transformation.nonlinear.variations.SinusoidalTransformation;
import edu.project4.transformation.nonlinear.variations.SphericalTransformation;
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
