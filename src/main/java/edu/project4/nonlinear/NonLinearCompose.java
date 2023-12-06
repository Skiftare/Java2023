package edu.project4.nonlinear;

import edu.project4.Transformation;

public class NonLinearCompose {
    private final Transformation [] variantsOfNonLinear = {
        new SinusoidalTransformation(),
        new SphericalTransformation()
    };

    public Transformation getNonLinearTransformationById(int id){
        if(id >= variantsOfNonLinear.length || id < 0){
            throw new RuntimeException("wrong id for geting NonLinearTransformationById");
        }
        return variantsOfNonLinear[id];
    }
}
