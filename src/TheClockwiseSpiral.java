import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TheClockwiseSpiral {

    private static int matrixLength;
    private int[][] matrix;
    private int index = 0;
    private List<Integer> allElements = new ArrayList<>();

    public int[][] createSpiral(int N) {

        type direction;
        matrixLength = N;
        matrix = new int[N][N];

        if (N < 1) return new int[][]{};
        if (N == 1) return new int[][]{{1}};

        for (int i = 1; i <= matrixLength * matrixLength; i++) allElements.add(i);

        List<Coordinates> allInitialCoordinates = orderCoordinates();

        for (int i = 0; i < allInitialCoordinates.size(); i++) {

            Coordinates coordinate = allInitialCoordinates.get(i);

            if (coordinate.isInMainDiagonal()) direction = type.UP;
            else direction = type.DOWN;

            placeSegment(allInitialCoordinates.get(i), direction, N - i);
        }
        return matrix;
    }

    private void placeSegment(Coordinates coordinate, type direction, int length) {

        int xPos = coordinate.xPos;
        int yPos = coordinate.yPos;

        for (int i = 0; i < length; i++) {
            matrix[xPos][yPos] = allElements.get(index);
            index++;
            if (direction == type.UP)
                yPos++;
            else
                yPos--;
        }

        if (direction == type.UP) {
            xPos++;
            yPos--;
        }else{
            xPos--;
            yPos++;
        }

        for (int i = 0; i < length - 1; i++) {
            matrix[xPos][yPos] = allElements.get(index);
            index++;
            if (direction == type.UP)
                xPos++;
            else
                xPos--;
        }

    }

    private static List<Coordinates> orderCoordinates(){
        List<Coordinates> mainDiagonalCoordinates = getAllCoordinates().stream()
                                                                       .filter(Coordinates::isInMainDiagonal)
                                                                       .collect(Collectors.toList());

        List<Coordinates> secondDiagonalCoordinates = getAllCoordinates().stream()
                                            .filter(coordinate -> !mainDiagonalCoordinates.contains(coordinate))
                                            .collect(Collectors.toList());

        List<Coordinates> allCoordinatesOrdered = new ArrayList<>();

        for (int i = 0; i < secondDiagonalCoordinates.size(); i++) {
            allCoordinatesOrdered.add(mainDiagonalCoordinates.get(i));
            allCoordinatesOrdered.add(secondDiagonalCoordinates.get(i));
        }

        if (!(mainDiagonalCoordinates.size() == secondDiagonalCoordinates.size())){
            allCoordinatesOrdered.add(mainDiagonalCoordinates.get(mainDiagonalCoordinates.size() - 1));
        }
        return allCoordinatesOrdered;
    }

    private static List<Coordinates> getAllCoordinates(){
        List<Coordinates> allCoordinates = new ArrayList<>();
        int mainDiagonalCoordinates = matrixLength % 2 == 0 ? matrixLength / 2 : matrixLength /2 + 1;
        int secondDiagonalCoordinates = matrixLength - mainDiagonalCoordinates;

        for (int i = 0; i < mainDiagonalCoordinates; i++) {
            allCoordinates.add(new Coordinates(i, i));
        }

        for (int i = 0; i < secondDiagonalCoordinates; i++) {
            allCoordinates.add(new Coordinates(matrixLength - 1 - i, matrixLength - 2 - i));
        }
        return allCoordinates;
    }

    private static class Coordinates {

        private final int xPos;
        private final int yPos;

        public Coordinates(int xPos, int yPos){
            this.xPos = xPos;
            this.yPos = yPos;
        }

        @Override
        public boolean equals(Object o){
            Coordinates coordinate = (Coordinates) o;
            return this.xPos == coordinate.xPos && this.yPos == coordinate.yPos;
        }

        public boolean isInMainDiagonal() {
            return xPos == yPos;
        }
    }

    private enum type{
        UP, DOWN
    }
}