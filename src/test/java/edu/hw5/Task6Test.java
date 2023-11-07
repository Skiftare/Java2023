package edu.hw5;

import org.junit.jupiter.api.Test;
import static edu.hw5.Task6.isSubsequence;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {
    @Test
    public void testThatGetTwoStringsForCheckIsSubsequenceAndReturnedTrue() {
        String s = "abc";
        String t = "xyzabcxyz";
        boolean isSubsequence = isSubsequence(s, t);
        assertTrue(isSubsequence);
    }

    @Test
    public void testThatGetTwoStringsForCheckIsSubsequenceAndReturnedFalse() {
        String s = "abc";
        String t = "xyzdefxyz";
        boolean isSubsequence = isSubsequence(s, t);
        assertFalse(isSubsequence);
    }

    @Test
    public void testThatGetTwoStringsEmptySearchForCheckIsSubsequenceAndReturnedTrue() {
        String s = "";
        String t = "xyzabcxyz";
        boolean isSubsequence = isSubsequence(s, t);
        assertTrue(isSubsequence);
    }

    @Test
    public void testThatGetTwoStringsEmptyBodyForCheckIsSubsequenceAndReturnedFalse() {
        String s = "abc";
        String t = "";
        boolean isSubsequence = isSubsequence(s, t);
        assertFalse(isSubsequence);
    }
}
