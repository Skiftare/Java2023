package edu.project4;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiThreadTest {

    @Test
    public void testThatRunTwoFractalGeneratorsInDifferentThreadNumberAndReturnedApprovalOfMultitasking()
        throws IOException {
        Main singleThreadGen = new Main();
        String [] args = new String[1];
        args[0] = "src/test/java/edu/project4/singleThread/";

        long startTime = System.nanoTime();
        singleThreadGen.main(args);
        long endTime = System.nanoTime();

        long execOneThreadTime  = endTime - startTime;

        Main multiThreadGen = new Main();
        args[0] = "src/test/java/edu/project4/multiThread/";

        startTime = System.nanoTime();
        multiThreadGen.main(args);
        endTime = System.nanoTime();

        long execMultiThreadTime  = endTime - startTime;

        System.out.println(execMultiThreadTime);
        System.out.println(execOneThreadTime);
        assertTrue(execMultiThreadTime < execOneThreadTime);

    }
}
