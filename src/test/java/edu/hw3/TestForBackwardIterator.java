package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestForBackwardIterator {
    @Test
    @DisplayName("Тест для идущего назад итератора")
    void testThatGetArrayAndReturnedBackwardIterating() {

        List<Integer> numbers = new java.util.ArrayList<>(List.of(1, 2, 3, 6, 9, 74, 1));
        BackwardIterator<Integer> iterator = new BackwardIterator<>(numbers);
        int [] expected = new int[numbers.size()];
        int i = 0;
        while (iterator.hasNext()) {
            expected[i] = iterator.next();
            i++;
        }
        Collections.reverse(numbers);

        assertArrayEquals(
            expected,
            numbers.stream().mapToInt(Integer::intValue).toArray()
        );

    }
}
