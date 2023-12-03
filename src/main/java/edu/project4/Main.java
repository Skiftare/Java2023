package edu.project4;

import edu.project4.geometry.Rectangle;
import edu.project4.helperCore.ArgsParser;
import edu.project4.helperCore.ArgsState;
import edu.project4.helperCore.FileAndPathManager;

public class Main {

    public static void main(String[] args) {
        ArgsParser parser = new ArgsParser();
        ArgsState incomeArguments = parser.parse(args);

        //Готово. У нас есть параметры.
        Rectangle display = new Rectangle(2160,1440); //TODO: в константы
        //Готово. У нас есть пустой экран.
        FileAndPathManager.setFileFormat(incomeArguments.getFormat().toString());
        FileAndPathManager.initFileForFlameOutput();

    }

}
