public class Node {
    int[][] grid;
    int costPrediction;
    int previousCost;

    public Node(int[][] grid, boolean hamming, int[][] goalGrid, int previousCost) {
        this.grid = grid;
        this.costPrediction = hamming ? countMisplacedTilesHamming(grid, goalGrid) : countMisplacedByPositionManhattan(grid);
        this.previousCost = previousCost;
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
