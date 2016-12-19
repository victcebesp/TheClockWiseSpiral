import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TheClockwiseSpiralTest {

    TheClockwiseSpiral spiral = new TheClockwiseSpiral();

    @Test
    public void should_create_spiral_with_length_2 (){
        int[][] expected = new int[][]{
                {1, 2},
                {4, 3}};

        assertArrayEquals(expected, spiral.createSpiral(2));
    }

    @Test
    public void should_test_spiral() {
        int[][] expected = new int[][]{
                {1, 2, 3},
                {8, 9, 4},
                {7, 6, 5}};

        assertArrayEquals(expected, spiral.createSpiral(3));
    }

    @Test
    public void should_test_spiral_with_length_4() {
        int[][] expected = new int[][]{
                {1, 2, 3, 4},
                {12, 13, 14, 5},
                {11, 16, 15, 6},
                {10, 9, 8, 7}};

        assertArrayEquals(expected, spiral.createSpiral(4));
    }
}