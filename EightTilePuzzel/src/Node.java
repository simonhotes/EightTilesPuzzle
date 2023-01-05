import java.util.ArrayList;

public class Node {
    private int[] grid;
    private int[] goalGrid;
    private int gridWidth;
    private int misplacedTiles;
    private int previousCost;
    private boolean hamming;
    private int costPrediction;

    public Node(int[] grid, boolean hamming, int[] goalGrid, int previousCost) {
        this.grid = grid;
        this.goalGrid = goalGrid;
        this.hamming = hamming;
        this.gridWidth = this.grid.length;
        this.previousCost = (previousCost+1);
        this.misplacedTiles = this.hamming ? countMisplacedTilesHamming(this.grid, this.goalGrid) : countMisplacedByPositionManhattan(this.grid);
        this.costPrediction = (this.misplacedTiles+this.previousCost);
    }

    public int[] getGrid() {
        return grid;
    }

    public int[] getGoalGrid() {
        return goalGrid;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getMisplacedTiles() {
        return misplacedTiles;
    }

    public int getPreviousCost() {
        return previousCost;
    }

    public boolean isHamming() {
        return hamming;
    }

    public int getCostPrediction() {
        return costPrediction;
    }

    public static int countMisplacedTilesHamming(int[] grid, int[] goalGrid) {
        int numberOfMisplacedTiles = 0;
        for (int i = 0; i < grid.length; i++) {
            if (grid[i] != 0 && grid[i] != goalGrid[i]) {
                numberOfMisplacedTiles++;
            }
        }
        return numberOfMisplacedTiles;
    }
    public static int countMisplacedByPositionManhattan(int[] grid) {
        int numberMisplacedByPosition = 0;
        int tempTwoDimensionalGrid[][] = {{grid[0],grid[1],grid[2]},{grid[3],grid[4],grid[5]},{grid[6],grid[7],grid[8]}};

        // Manhatten Misplaced Tiles Calculation with Solution-Grid where empty tile is at 0,0
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(tempTwoDimensionalGrid[i][j] == 1) {numberMisplacedByPosition += Math.abs(1-j);numberMisplacedByPosition += Math.abs(0-i);}
                if(tempTwoDimensionalGrid[i][j] == 2) {numberMisplacedByPosition += Math.abs(2-j);numberMisplacedByPosition += Math.abs(0-i);}
                if(tempTwoDimensionalGrid[i][j] == 3) {numberMisplacedByPosition += Math.abs(0-j);numberMisplacedByPosition += Math.abs(1-i);}
                if(tempTwoDimensionalGrid[i][j] == 4) {numberMisplacedByPosition += Math.abs(1-j);numberMisplacedByPosition += Math.abs(1-i);}
                if(tempTwoDimensionalGrid[i][j] == 5) {numberMisplacedByPosition += Math.abs(2-j);numberMisplacedByPosition += Math.abs(1-i);}
                if(tempTwoDimensionalGrid[i][j] == 6) {numberMisplacedByPosition += Math.abs(0-j);numberMisplacedByPosition += Math.abs(2-i);}
                if(tempTwoDimensionalGrid[i][j] == 7) {numberMisplacedByPosition += Math.abs(1-j);numberMisplacedByPosition += Math.abs(2-i);}
                if(tempTwoDimensionalGrid[i][j] == 8) {numberMisplacedByPosition += Math.abs(2-j);numberMisplacedByPosition += Math.abs(2-i);}
                if(tempTwoDimensionalGrid[i][j] == 0) {numberMisplacedByPosition += 0;}
            }
        }

        // Manhatten Misplaced Tiles Calculation with Solution-Grid where empty tile is at 2,2
        /*
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(tempTwoDimensionalGrid[i][j] == 1) {numberMisplacedByPosition += Math.abs(0-j);numberMisplacedByPosition += Math.abs(0-i);}
                if(tempTwoDimensionalGrid[i][j] == 2) {numberMisplacedByPosition += Math.abs(1-j);numberMisplacedByPosition += Math.abs(0-i);}
                if(tempTwoDimensionalGrid[i][j] == 3) {numberMisplacedByPosition += Math.abs(2-j);numberMisplacedByPosition += Math.abs(0-i);}
                if(tempTwoDimensionalGrid[i][j] == 4) {numberMisplacedByPosition += Math.abs(0-j);numberMisplacedByPosition += Math.abs(1-i);}
                if(tempTwoDimensionalGrid[i][j] == 5) {numberMisplacedByPosition += Math.abs(1-j);numberMisplacedByPosition += Math.abs(1-i);}
                if(tempTwoDimensionalGrid[i][j] == 6) {numberMisplacedByPosition += Math.abs(2-j);numberMisplacedByPosition += Math.abs(1-i);}
                if(tempTwoDimensionalGrid[i][j] == 7) {numberMisplacedByPosition += Math.abs(0-j);numberMisplacedByPosition += Math.abs(2-i);}
                if(tempTwoDimensionalGrid[i][j] == 8) {numberMisplacedByPosition += Math.abs(1-j);numberMisplacedByPosition += Math.abs(2-i);}
                if(tempTwoDimensionalGrid[i][j] == 0) {numberMisplacedByPosition += 0;}
            }
        }
        */
        return numberMisplacedByPosition;
    }
}

