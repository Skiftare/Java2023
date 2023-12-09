package edu.project4.transformation.afin;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.security.SecureRandom;

class AfinGenerator {
    private SecureRandom secureRandom = new SecureRandom();
    AfinTransformation generateWithGoodCoeff(Color col){
        double a,b,c,d,e,f;
        do
        {
            do
            {
                a = secureRandom.nextDouble();
                d = secureRandom.nextDouble(a * a, 1);
                if (secureRandom.nextBoolean()) {
                    d = -d;
                }
            }
            while ((a * a + d * d) > 1);
            do
            {
                b = secureRandom.nextDouble();
                e = secureRandom.nextDouble(b * b, 1);
                if (secureRandom.nextBoolean()) {
                    e = -e;
                }
            }
            while ((b * b + e * e) > 1);
        }
        while ((a * a + b * b + d * d + e * e) >
            (1 + (a * e - d * b) * (a * e - d * b)));

        c = secureRandom.nextDouble(-2,2);
        f = secureRandom.nextDouble(-2,2);
        return new AfinTransformation(a,b,c,d,e,f,col);
    }
    AfinTransformation generateWithReallyRandomCoeff(Color col){
        double a,b,c,d,e,f;
        a = secureRandom.nextDouble(-1.5,1.5);
        b = secureRandom.nextDouble(-1.5,1.5);
        c = secureRandom.nextDouble(-1.5,1.5);
        d = secureRandom.nextDouble(-1.5,1.5);
        e = secureRandom.nextDouble(-1.5,1.5);
        f = secureRandom.nextDouble(-1.5,1.5);
        return new AfinTransformation(a,b,c,d,e,f,col);
    }
    AfinTransformation generateAfin() {
        /**
         * init
         **/
        double a, b, c, d, e, f;
        Color randomColor;

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
        if (secureRandom.nextBoolean()) {
            return generateWithGoodCoeff(randomColor);
        } else{
            return generateWithReallyRandomCoeff(randomColor);
        }

    }
}
