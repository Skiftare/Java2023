package edu.project4;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiThreadTest {

    @Test
    public void testThatRunTwoFractalGeneratorsInDifferentThreadNumberAndReturnedApprovalOfMultitasking()
        throws IOException {
        String [] args = new String[1];
        args[0] = "src/test/java/edu/project4/singleThread/";

        long startTime = System.nanoTime();
        Main.main(args);
        long endTime = System.nanoTime();

        long execOneThreadTime  = endTime - startTime;

        args[0] = "src/test/java/edu/project4/multiThread/";

        startTime = System.nanoTime();
        Main.main(args);
        endTime = System.nanoTime();

        long execMultiThreadTime  = endTime - startTime;

        assertTrue(execMultiThreadTime < execOneThreadTime);

    }
}
