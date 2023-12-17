package edu.hw10.task1.testClasses;

import edu.hw10.task1.Max;
import edu.hw10.task1.Min;
import edu.hw10.task1.NotNull;

public class ObjectWithMinAndMaxAnnotation {

    @Min(100)
    @Max(8)
    @NotNull
    private final int maxMinValueWithWeirdLogic;

    @Max(100)
    @Min(8)
    private final int maxMinValueWithNormalLogic;

    public ObjectWithMinAndMaxAnnotation(
        Integer maxMinValueWithWeirdLogic,
        Integer maxMinValueWithNormalLogic
    ) {
        this.maxMinValueWithWeirdLogic = maxMinValueWithWeirdLogic;
        this.maxMinValueWithNormalLogic = maxMinValueWithNormalLogic;

    }

}
