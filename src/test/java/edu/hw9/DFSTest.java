package edu.hw9;

import java.util.Map;
import edu.hw9.task3.DFS;
import edu.hw9.task3.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DFSTest {
    private Node[] nodes;

    @BeforeEach
    void setUp() {
        // Инициализация узлов
        nodes = new Node[10];
        for (int i = 0; i < 10; i++) {
            nodes[i] = new Node(i + 1);
        }
    }

    @Test
    @DisplayName("Тест, где граф - это дерево")
    void testThatGetTreeGraphAndReturnedMapOfVisited() {
        /*
         * Дерево:
         *        1
         *      /   \
         *     2     3
         *    / \   / \
         *   4   5 6   7
         */

        //given: tree
        nodes[0].addNeighbor(nodes[1]);
        nodes[0].addNeighbor(nodes[2]);
        nodes[1].addNeighbor(nodes[3]);
        nodes[1].addNeighbor(nodes[4]);
        nodes[2].addNeighbor(nodes[5]);
        nodes[2].addNeighbor(nodes[6]);

        //when: we run DFS on our grapf
        DFS dfs = new DFS(nodes, 1);
        dfs.dfs();

        Map<Integer, Boolean> result = dfs.getResult();

        //then: all vertexes are colored (true-false) as we expected
        assertTrue(result.get(1));
        assertTrue(result.get(2));
        assertTrue(result.get(3));
        assertTrue(result.get(4));
        assertTrue(result.get(5));
        assertTrue(result.get(6));
        assertTrue(result.get(7));
        assertFalse(result.get(8));
        assertFalse(result.get(9));
        assertFalse(result.get(10));
    }

    @Test
    @DisplayName("Тест, где доступна лишь одна вершина")
    void testThatGetGraphWithOnlyOneAvailableVertexAndReturnedMapOfVisited() {

        //given: graph with no ways


        //when: we run DFS on our grapf
        DFS dfs = new DFS(nodes, 1);
        dfs.dfs();

        Map<Integer, Boolean> result = dfs.getResult();

        //then: all vertexes are colored (true-false) as we expected
        assertTrue(result.get(1));
        assertFalse(result.get(2));
        assertFalse(result.get(3));
        assertFalse(result.get(4));
        assertFalse(result.get(5));
        assertFalse(result.get(6));
        assertFalse(result.get(7));
        assertFalse(result.get(8));
        assertFalse(result.get(9));
        assertFalse(result.get(10));
    }

    @Test
    @DisplayName("Тест, где доступны не все вершины")
    void testThatGetIncompleteGraphAndReturnedMapOfVisited() {
        /*
         * Граф:
         *        1 -- 2 -- 3
         *             |
         *             5
         *
         *   6 -- 7 -- 8
         */

        //given: incomplete graph (actually, two graphs in one array)
        nodes[0].addNeighbor(nodes[1]);
        nodes[1].addNeighbor(nodes[2]);
        nodes[1].addNeighbor(nodes[4]);

        nodes[5].addNeighbor(nodes[6]);
        nodes[6].addNeighbor(nodes[7]);

        //when: we run DFS on our grapf

        DFS dfs = new DFS(nodes, 1);
        dfs.dfs();

        Map<Integer, Boolean> result = dfs.getResult();

        //then: all vertexes are colored (true-false) as we expected
        assertTrue(result.get(1));
        assertTrue(result.get(2));
        assertTrue(result.get(3));
        assertFalse(result.get(4));
        assertTrue(result.get(5));
        assertFalse(result.get(6));
        assertFalse(result.get(7));
        assertFalse(result.get(8));
        assertFalse(result.get(9));
        assertFalse(result.get(10));
    }

    @Test
    @DisplayName("Тест, где в графе есть циклы")
    void testThatGetGraphWithCyclesAndReturnedMapOfVisited() {
        /*
         * Граф:
         *         1 -- 2 -- 4 -- 3
         *              |    |
         *              5 -- 6
         */

        //given: graph with cycles

        //Первый "этаж" графа
        nodes[0].addNeighbor(nodes[1]);
        nodes[1].addNeighbor(nodes[3]);
        nodes[3].addNeighbor(nodes[2]);

        //Второй этаж
        nodes[4].addNeighbor(nodes[5]);

        //Связи (Вериткальные)
        nodes[1].addNeighbor(nodes[4]);
        nodes[3].addNeighbor(nodes[5]);

        //when: we run DFS on our grapf
        DFS dfs = new DFS(nodes, 1);
        dfs.dfs();

        Map<Integer, Boolean> result = dfs.getResult();

        //then: all vertexes are colored (true-false) as we expected
        assertTrue(result.get(1));
        assertTrue(result.get(2));
        assertTrue(result.get(3));
        assertTrue(result.get(4));

        assertTrue(result.get(5));
        assertTrue(result.get(6));

        assertFalse(result.get(7));
        assertFalse(result.get(8));
        assertFalse(result.get(9));
        assertFalse(result.get(10));
    }
}
