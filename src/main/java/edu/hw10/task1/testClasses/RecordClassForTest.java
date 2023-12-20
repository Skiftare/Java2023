package edu.hw10.task1.testClasses;

import edu.hw10.task1.Max;
import edu.hw10.task1.Min;
import edu.hw10.task1.NotNull;

public record RecordClassForTest(@NotNull @Max(10) @Min(0) Long maxMinRecordLong) {
}
