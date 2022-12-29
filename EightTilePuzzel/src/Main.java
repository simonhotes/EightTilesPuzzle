import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.lang.Math;

public class Main {
    /*
    Documentation
    */
        /*
        Todos
            - Generate Puzzles: generate X random arrays with values from 0-8 and split them into array
            - Save Pointer to Array in List
            - Calculate f(n) for
        */
        /*
        Check Solubility:
            - for even grid width the number of inversions has to be odd
            - for odd grid width the number of inversions has to be even
            - Inversion: a[i] > b[j] for i < j (convert grid into 1-dimensional array; ignore empty tile (0))
            - functions implemented:
                - countInversions -> converts grid into 1-dimensional array & counts inversions
                - isEven -> checks if a given number is even
                - isSolvable -> checks if two boolean parameters are equal
        */
        /*
        Print:
            - basic print function for grid with grid width of N
        */

    public static void main(String[] args) {
        int[][] goalGrid = {{0,1,2},{3,4,5},{6,7,8}};
        boolean hamming = true;

        int[][] dummyGrid = {{0,1,2},{3,4,5},{6,7,8}};

        Node node1 = new Node(generateRandomGrid(), hamming, goalGrid, 0);
        Node node2 = new Node(dummyGrid, hamming, goalGrid, 0);
        Node node3 = new Node(generateRandomGrid(), hamming, goalGrid, 0);
        Node node4 = new Node(generateRandomGrid(), hamming, goalGrid, 0);

        ArrayList<Node> openNodesList = new ArrayList<>();
        openNodesList.add(node1);
        openNodesList.add(node2);
        openNodesList.add(node3);
        openNodesList.add(node4);

        for(Node n: openNodesList) {
            System.out.println(""+n.costPrediction);
        }


/*
        int[][] goalGrid = {{0,1,2},{3,4,5},{6,7,8}};
        int gridWidth = 3;
        int[][] grid = generateRandomGrid();

        if(isSolvable(isEven(gridWidth),isEven(countInversions(gridWidth,grid)))) {
            print(gridWidth,grid);
            System.out.println(""+countMisplacedTilesHamming(grid,goalGrid));
            System.out.println(""+countMisplacedByPositionManhattan(grid));
        }

*/

    }

    public static int[][] generateRandomGrid() {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random randomGenerator = new Random();
        while (numbers.size() < 9) {
            int random = randomGenerator .nextInt(9);
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        Integer[] arr = new Integer[numbers.size()];
        arr = numbers.toArray(arr);

        int[][] array = {{arr[0],arr[1],arr[2]},{arr[3],arr[4],arr[5]},{arr[6],arr[7],arr[8]}};
        return array;
    }
    public static int countInversions(int N, int[][] grid) {
        // Convert Grid into one-dimensional-Array (odA)
        int[] odA = Stream.of(grid)
                .flatMapToInt(IntStream::of)
                .toArray();

        // Inversion Counter (IC)
        int IC = 0;

        // Brute-Force to count Inversions
        // I assume that N (width of grid) does not exceed 5
        // otherwise solving the puzzle will be very intensive
        // N = 5 results in a 1-dimensional Array with 25 entries
        // I assume checking inversions within this max size of an array is reasonable
        for (int i = 0; i < odA.length; i++) {
            for (int j = i+1; j < odA.length; j++) {
                if (odA[i] != 0 && odA[i] > odA[j] && odA[j] != 0) {
                    IC++;
                }
            }
        }
        return IC;
    }
    public static boolean isEven(int N) {
        if (N % 2 == 0 ) { return true; }
        return false;
    }
    public static boolean isSolvable(boolean gridWidthEven, boolean inversionsEven) {
        if (gridWidthEven != inversionsEven) {
            return true;
        }
        return false;
    }
    public static void print(int gridWidth, int [][] grid) {
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridWidth; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("");
        }
    }
    public static int countMisplacedTilesHamming(int[][] grid, int[][] goalGrid) {
        int numberOfMisplacedTiles = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] != 0 && grid[i][j] != goalGrid[i][j]) {
                    numberOfMisplacedTiles++;
                }
            }
        }
        return numberOfMisplacedTiles;
    }
    public static int countMisplacedByPositionManhattan(int[][] grid) {
        int numberMisplacedByPosition = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(grid[i][j] == 1) {numberMisplacedByPosition += Math.abs(1-j);numberMisplacedByPosition += Math.abs(0-i);}
                if(grid[i][j] == 2) {numberMisplacedByPosition += Math.abs(2-j);numberMisplacedByPosition += Math.abs(0-i);}
                if(grid[i][j] == 3) {numberMisplacedByPosition += Math.abs(0-j);numberMisplacedByPosition += Math.abs(1-i);}
                if(grid[i][j] == 4) {numberMisplacedByPosition += Math.abs(1-j);numberMisplacedByPosition += Math.abs(1-i);}
                if(grid[i][j] == 5) {numberMisplacedByPosition += Math.abs(2-j);numberMisplacedByPosition += Math.abs(1-i);}
                if(grid[i][j] == 6) {numberMisplacedByPosition += Math.abs(0-j);numberMisplacedByPosition += Math.abs(2-i);}
                if(grid[i][j] == 7) {numberMisplacedByPosition += Math.abs(1-j);numberMisplacedByPosition += Math.abs(2-i);}
                if(grid[i][j] == 8) {numberMisplacedByPosition += Math.abs(2-j);numberMisplacedByPosition += Math.abs(2-i);}
                if(grid[i][j] == 0) {numberMisplacedByPosition += 0;}
            }
        }
        return numberMisplacedByPosition;
    }
}
