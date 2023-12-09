package edu.project4.transformation.afin;

import edu.project4.components.ColoredPoint;
import edu.project4.components.Point;
import java.security.SecureRandom;
import java.util.ArrayList;

public class AfinCompose {
    private final ArrayList<AfinTransformation> afinMas;
    private final int n;
    SecureRandom random = new SecureRandom();

    public AfinCompose(int n) {
        this.n = n;
        AfinGenerator gen = new AfinGenerator();
        ArrayList<AfinTransformation> bufAfinArray = new ArrayList<>();
        for(int id = 0;id<n;id++){
            bufAfinArray.add(gen.generateAfin());
        }
        this.afinMas = bufAfinArray;
    }

    public ColoredPoint apply(Point point, int it) {
        Point res = afinMas.get(it).apply(point);
        ColoredPoint result = new ColoredPoint(res, afinMas.get(it).getColor());
        return result;
    }
    public AfinTransformation getRandomAfin(){
        int randomNumber = random.nextInt(n);
        return afinMas.get(randomNumber);
    }



}
