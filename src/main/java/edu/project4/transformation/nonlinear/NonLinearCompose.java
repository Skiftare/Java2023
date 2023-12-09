package edu.project4.transformation.nonlinear;

import edu.project4.transformation.Transformation;
import java.security.SecureRandom;

public class NonLinearCompose {
    private final Transformation[] variantsOfNonLinear;

    public NonLinearCompose(Transformation[] vars) {
        variantsOfNonLinear = vars;
    }

    public Transformation getNonLinear() {
        SecureRandom rand = new SecureRandom();
        int r = rand.nextInt(variantsOfNonLinear.length);
        return variantsOfNonLinear[r];
    }
}
