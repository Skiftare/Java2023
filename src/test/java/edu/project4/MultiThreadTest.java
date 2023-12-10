package edu.project4;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiThreadTest {

    @Test
    public void testThatRunTwoFractalGeneratorsInDifferentThreadNumberAndReturnedApprovalOfMultitasking()
        throws IOException {
        //given: one-thread genedator
        String [] args = new String[1];
        args[0] = "src/test/java/edu/project4/singleThread/";

        //then: start one-thread generator
        long startTime = System.nanoTime();
        Main.main(args);
        long endTime = System.nanoTime();

        //get exec time for one-thread gen
        long execOneThreadTime  = endTime - startTime;

        //given: multi-thread gen
        // (only cnt of cores differs from one-thread properties)
        args[0] = "src/test/java/edu/project4/multiThread/";

        //get exec time for multi-thread gen
        startTime = System.nanoTime();
        Main.main(args);
        endTime = System.nanoTime();

        //then: start multi-thread generator
        long execMultiThreadTime  = endTime - startTime;

        assertTrue(execMultiThreadTime < execOneThreadTime);

    }
}
