package edu.project4.flame;

import edu.hw2.Rectangle;
import edu.project4.math.AfinnianGenerator;
import edu.project4.math.AfinnianTrasformations;
import java.util.ArrayList;

public class FlameGenerator {
    private static final int CNT_OF_AFIN = 20;
    //Надо делать этот модуль так, чтобы он мог работать в многопоточке.
    public Rectangle generate(Rectangle incomeCanvas){
        AfinnianGenerator afinGen = new AfinnianGenerator();
        ArrayList<AfinnianTrasformations> transforms = afinGen.generate(CNT_OF_AFIN);
        //TODO: в случае, если есть флаг симметрии, каждая точка должна быть отражена.


        return incomeCanvas;
    }
}
