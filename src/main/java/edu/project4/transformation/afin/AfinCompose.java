package edu.project4.transformation.afin;

import java.security.SecureRandom;
import java.util.ArrayList;

public class AfinCompose {
    private final ArrayList<AfinTransformation> afinMas;
    private final int n;
    final SecureRandom random = new SecureRandom();

    public AfinCompose(int n) {
        this.n = n;
        AfinGenerator gen = new AfinGenerator();
        ArrayList<AfinTransformation> bufAfinArray = new ArrayList<>();
        for (int id = 0; id < n; id++) {
            bufAfinArray.add(gen.generateAfin());
        }
        this.afinMas = bufAfinArray;
    }

    public AfinTransformation getRandomAfin() {
        int randomNumber = random.nextInt(n);
        return afinMas.get(randomNumber);
    }

}
