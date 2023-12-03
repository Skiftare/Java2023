package edu.project4.math;


import edu.project4.helperCore.ErrorLogger;
import java.awt.Color;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import static java.lang.Math.abs;

public class AfinnianGenerator {
    private static final Random rand = new Random();
    private static final double MIN_COEF_VALUE = 0.0;
    private static final double MAX_COEF_VALUE = 100.0;
    private static final Integer CNT_OF_COEFF = 6;

    private double generateRandomCoefficient(){
        double coeffAbs = abs(rand.nextDouble()) * (MAX_COEF_VALUE - MIN_COEF_VALUE) + MIN_COEF_VALUE;
        int sign = (rand.nextBoolean()?1:-1);
        return  coeffAbs*sign;
    }
    private ArrayList<Double> generateListOfCoeff(){
        ArrayList<Double> coeff = new ArrayList<Double>();
        for(int i = 0;i<CNT_OF_COEFF;i++){
            coeff.add(generateRandomCoefficient());
        }
        return coeff;
    }
    private edu.project4.color.Color generateColour(){
        SecureRandom random = new SecureRandom();
        double r = random.nextFloat();
        double g = random.nextFloat();
        double b = random.nextFloat();
        edu.project4.color.Color col = new edu.project4.color.Color(r,g,b);
        return col;

    }

    public ArrayList<AfinnianTrasformations> generate(int countOfAfinnianTransformations) {
        ArrayList<AfinnianTrasformations> resultList = new ArrayList<>();
        while(countOfAfinnianTransformations > 0){
            ArrayList<Double> coeff = generateListOfCoeff();
            edu.project4.color.Color col = generateColour();

            try{
                AfinnianTrasformations afin = new AfinnianTrasformations(
                    coeff.get(0),
                    coeff.get(1),
                    coeff.get(2),
                    coeff.get(3),
                    coeff.get(4),
                    coeff.get(5),
                    col
                );
                resultList.add(afin);
                countOfAfinnianTransformations--;
            } catch (RuntimeException e){
                ErrorLogger.createLogError(e.getMessage());
            }
        }

        return resultList;
    }
}
