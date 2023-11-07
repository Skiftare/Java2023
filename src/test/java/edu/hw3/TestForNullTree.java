package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestForNullTree {
    @Test
    @DisplayName("Тест из условия работы с null")
    void testThatGetNullAsKeyAndReturnedValueInTheTree(){
        TreeMap<String, String> tree = new TreeMap<>(new NullComparator());
        tree.put(null, "test");
        tree.put("test", "test");
        tree.put("test1", null);
        tree.put(null, null);
        assertThat(tree.containsKey(null)).isTrue();
    }
}
