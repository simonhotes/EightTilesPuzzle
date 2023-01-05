import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Puzzle {
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
        String calculationModeOfMisplacedTiles = "h";
        long startTime = System.nanoTime();
        int numberOfPuzzlesToSolve = 100;
        for (int i = 0; i < numberOfPuzzlesToSolve; i++) {
            System.out.println("Puzzle No.: "+i);
            execute(calculationModeOfMisplacedTiles);
        }
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Done - Total execution time to create "+numberOfPuzzlesToSolve+" random Puzzle and solving them in Milliseconds: "
                + elapsedTime/1000000);
    }

    public static int[] generateRandomGrid() {
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

        int[] array = {arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6],arr[7],arr[8]};
        return array;
    }
    public static int countInversions(int N, int[] grid) {
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
    public static void print(Node node) {
        /*
        System.out.println("");
        System.out.println(""+node.getGrid()[0]+" "+node.getGrid()[1]+" "+node.getGrid()[2]);
        System.out.println(""+node.getGrid()[3]+" "+node.getGrid()[4]+" "+node.getGrid()[5]);
        System.out.println(""+node.getGrid()[6]+" "+node.getGrid()[7]+" "+node.getGrid()[8]);
        */

        System.out.println("");
        System.out.print(node.isHamming() ? "Costprediction Hamming: " : "Costprediction Manhattan: ");
        System.out.println(""+ node.getMisplacedTiles());
        System.out.println("Depth/PreviousCosts: "+ node.getPreviousCost());

    }
    public static boolean checkIfSolved(ArrayList<Node> list) {
        for(Node n : list) {
            if(n.getMisplacedTiles() == 0) {
                return true;
            }
        }
        return false;
    }
    public static int[] createInitialGrid() {
        int[] initialGrid = null;
        boolean initialGridGenerationSuccess = false;
        while (!initialGridGenerationSuccess) {
            initialGrid = generateRandomGrid();
            if (isSolvable(isEven(initialGrid.length), isEven(countInversions(initialGrid.length,initialGrid)))) {
                initialGridGenerationSuccess = true;
            }
        }
        return initialGrid;
    }
    public static ArrayList<Node> openNode(Node nodeToOpen) {
        ArrayList<Node> tempList = new ArrayList<Node>();
        Node tempNode;
        int[] tempGrid = {0,0,0,0,0,0,0,0,0};
        int temp=9;

        if (nodeToOpen.getGrid()[0] == 0) {
            tempGrid = copyArray(nodeToOpen);
            tempGrid[0] = nodeToOpen.getGrid()[1];
            tempGrid[1] = nodeToOpen.getGrid()[0];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);

            tempGrid = copyArray(nodeToOpen);
            tempGrid[0] = nodeToOpen.getGrid()[3];
            tempGrid[3] = nodeToOpen.getGrid()[0];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);
        }
        if (nodeToOpen.getGrid()[1] == 0) {
            tempGrid = copyArray(nodeToOpen);
            tempGrid[1] = nodeToOpen.getGrid()[0];
            tempGrid[0] = nodeToOpen.getGrid()[1];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);

            tempGrid = copyArray(nodeToOpen);
            tempGrid[1] = nodeToOpen.getGrid()[2];
            tempGrid[2] = nodeToOpen.getGrid()[1];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);

            tempGrid = copyArray(nodeToOpen);
            tempGrid[1] = nodeToOpen.getGrid()[4];
            tempGrid[4] = nodeToOpen.getGrid()[1];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);
        }
        if (nodeToOpen.getGrid()[2] == 0) {
            tempGrid = copyArray(nodeToOpen);
            tempGrid[2] = nodeToOpen.getGrid()[1];
            tempGrid[1] = nodeToOpen.getGrid()[2];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);

            tempGrid = copyArray(nodeToOpen);
            tempGrid[2] = nodeToOpen.getGrid()[5];
            tempGrid[5] = nodeToOpen.getGrid()[2];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);
        }
        if (nodeToOpen.getGrid()[3] == 0) {
            tempGrid = copyArray(nodeToOpen);
            tempGrid[3] = nodeToOpen.getGrid()[0];
            tempGrid[0] = nodeToOpen.getGrid()[3];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);

            tempGrid = copyArray(nodeToOpen);
            tempGrid[3] = nodeToOpen.getGrid()[4];
            tempGrid[4] = nodeToOpen.getGrid()[3];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);

            tempGrid = copyArray(nodeToOpen);
            tempGrid[3] = nodeToOpen.getGrid()[6];
            tempGrid[6] = nodeToOpen.getGrid()[3];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);
        }
        if (nodeToOpen.getGrid()[4] == 0) {
            tempGrid = copyArray(nodeToOpen);
            tempGrid[4] = nodeToOpen.getGrid()[1];
            tempGrid[1] = nodeToOpen.getGrid()[4];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);

            tempGrid = copyArray(nodeToOpen);
            tempGrid[4] = nodeToOpen.getGrid()[3];
            tempGrid[3] = nodeToOpen.getGrid()[4];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);

            tempGrid = copyArray(nodeToOpen);
            tempGrid[4] = nodeToOpen.getGrid()[5];
            tempGrid[5] = nodeToOpen.getGrid()[4];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);

            tempGrid = copyArray(nodeToOpen);
            tempGrid[4] = nodeToOpen.getGrid()[7];
            tempGrid[7] = nodeToOpen.getGrid()[4];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);
        }
        if (nodeToOpen.getGrid()[5] == 0) {
            tempGrid = copyArray(nodeToOpen);
            tempGrid[5] = nodeToOpen.getGrid()[2];
            tempGrid[2] = nodeToOpen.getGrid()[5];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);

            tempGrid = copyArray(nodeToOpen);
            tempGrid[5] = nodeToOpen.getGrid()[4];
            tempGrid[4] = nodeToOpen.getGrid()[5];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);

            tempGrid = copyArray(nodeToOpen);
            tempGrid[5] = nodeToOpen.getGrid()[8];
            tempGrid[8] = nodeToOpen.getGrid()[5];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);
        }
        if (nodeToOpen.getGrid()[6] == 0) {
            tempGrid = copyArray(nodeToOpen);
            tempGrid[6] = nodeToOpen.getGrid()[3];
            tempGrid[3] = nodeToOpen.getGrid()[6];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);

            tempGrid = copyArray(nodeToOpen);
            tempGrid[6] = nodeToOpen.getGrid()[7];
            tempGrid[7] = nodeToOpen.getGrid()[6];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);
        }
        if (nodeToOpen.getGrid()[7] == 0) {
            tempGrid = copyArray(nodeToOpen);
            tempGrid[7] = nodeToOpen.getGrid()[4];
            tempGrid[4] = nodeToOpen.getGrid()[7];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);

            tempGrid = copyArray(nodeToOpen);
            tempGrid[7] = nodeToOpen.getGrid()[6];
            tempGrid[6] = nodeToOpen.getGrid()[7];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);

            tempGrid = copyArray(nodeToOpen);
            tempGrid[7] = nodeToOpen.getGrid()[8];
            tempGrid[8] = nodeToOpen.getGrid()[7];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);
        }
        if (nodeToOpen.getGrid()[8] == 0) {
            tempGrid = copyArray(nodeToOpen);
            tempGrid[8] = nodeToOpen.getGrid()[5];
            tempGrid[5] = nodeToOpen.getGrid()[8];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);

            tempGrid = copyArray(nodeToOpen);
            tempGrid[8] = nodeToOpen.getGrid()[7];
            tempGrid[7] = nodeToOpen.getGrid()[8];
            tempNode = new Node(tempGrid, nodeToOpen.isHamming(), nodeToOpen.getGoalGrid(), nodeToOpen.getPreviousCost());
            tempList.add(tempNode);
        }
        return (tempList);
    }
    public static int[] copyArray(Node node) {
        int[] tempGrid = {0,0,0,0,0,0,0,0,0};
        int temp=9;
        for(int i = 0; i < node.getGridWidth(); i++) {
            temp = node.getGrid()[i];
            tempGrid[i] = temp;

        }
        return tempGrid;
    }
    public static boolean checkIfGridsAreEqual(int[] gridA, int[] gridB) {
        for (int i = 0; i < gridA.length; i++) {
            if (gridA[i] != gridB[i]) {
                return false;
            }
        }
        return true;
    }
    public static ArrayList<Node> removeDuplicateNodes(ArrayList<Node> listToCheckWith_1, ArrayList<Node> listToCheckWith_2, ArrayList<Node> listToRemoveNodesFrom) {
        ArrayList<Node> cleanedList = (ArrayList<Node>) listToRemoveNodesFrom.stream().collect(Collectors.toList());
        for (Node listToRemoveNodesFromNode : listToRemoveNodesFrom) {
            for (Node listToCheckWithNode_1 : listToCheckWith_1) {
                if (checkIfGridsAreEqual(listToCheckWithNode_1.getGrid(), listToRemoveNodesFromNode.getGrid())) {
                    cleanedList.remove(listToRemoveNodesFromNode);
                }
            }
            for (Node listToCheckWithNode_2 : listToCheckWith_2) {
                if (checkIfGridsAreEqual(listToCheckWithNode_2.getGrid(), listToRemoveNodesFromNode.getGrid())) {
                    cleanedList.remove(listToRemoveNodesFromNode);
                }
            }
        }

        return cleanedList;
    }
    public static void execute(String calculationModeOfMisplacedTiles) {
        int[] goalGrid = {1,2,3,4,5,6,7,8,0};
        boolean hamming = calculationModeOfMisplacedTiles == "h" ? true : false;
        ArrayList<Node> openNodesList = new ArrayList<>();
        ArrayList<Node> deadNodesList = new ArrayList<>();


        // Create initialGrid via function
        int[] initialGrid = createInitialGrid();


        Node initialNode = new Node(initialGrid, hamming, goalGrid,-1);
        Node focusNode;
        openNodesList.add(initialNode);
        int numberOfIterationsToSolvePuzzle = 0;
        while (!(checkIfSolved(openNodesList))) {
            numberOfIterationsToSolvePuzzle++;
            focusNode = openNodesList
                    .stream()
                    .min(Comparator.comparing(Node::getCostPrediction))
                    .get();

            ArrayList<Node> tempNodesList;
            tempNodesList = openNode(focusNode);
            ArrayList<Node> helper = new ArrayList<>();

            if (deadNodesList.size() > 0 || openNodesList.size() > 0) {
                helper = removeDuplicateNodes(deadNodesList,openNodesList, tempNodesList);
            }


            if (helper.size() > 0) {
                for (Node node : helper) {
                    openNodesList.add(node);
                }
            }

            deadNodesList.add(focusNode);
            openNodesList.remove(focusNode);

            if(checkIfSolved(openNodesList)) {
                System.out.print("--------------------------");
                System.out.println("");
                System.out.println("OpenNodeListSize: "+openNodesList.size());
                System.out.println("DeadNodeListSize: "+deadNodesList.size());
                System.out.print("    Initial Grid: ");
                for(int i=0;i<9;i++) {System.out.print(""+initialGrid[i]);}
                System.out.println("");
                for(Node n : openNodesList) {
                    if(n.getMisplacedTiles() == 0) {
                        Node solvedNode = n;
                        print(solvedNode);
                    }
                }
                System.out.println("--------------------------");
            }
        }
    }
}
