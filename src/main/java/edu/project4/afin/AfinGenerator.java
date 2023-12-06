package edu.project4.afin;

import java.awt.Color;
import java.security.SecureRandom;

public class AfinGenerator {
    private boolean checkForCorrectParametres(double a, double b, double c, double d, double e, double f) {
        boolean param1 = Math.pow(a, 2) + Math.pow(d, 2) < 1;
        boolean param2 = Math.pow(b, 2) + Math.pow(e, 2) < 1;
        boolean param3 = Math.pow(a, 2) +
            Math.pow(b, 2) +
            Math.pow(d, 2) +
            Math.pow(e, 2) < 1 + (Math.pow(a * e - b * d, 2));
        return param1 && param2 && param3;
    }

    public AfinTransformation generateAfin() {
        /**
         * init
         **/
        double a, b, c, d, e, f;
        Color randomColor;
        SecureRandom secureRandom = new SecureRandom();
        /**
         * randomColor
         */
        int red = secureRandom.nextInt(256);
        int green = secureRandom.nextInt(256);
        int blue = secureRandom.nextInt(256);
        randomColor = new Color(red, green, blue);

        /**
         * randomCoeff
         */
        do {
            a = secureRandom.nextDouble();
            b = secureRandom.nextDouble();
            c = secureRandom.nextDouble();
            d = secureRandom.nextDouble();
            e = secureRandom.nextDouble();
            f = secureRandom.nextDouble();
        } while (!checkForCorrectParametres(a, b, c, d, e, f));
        return new AfinTransformation(a,b,c,d,e,f,randomColor);
    }
}
