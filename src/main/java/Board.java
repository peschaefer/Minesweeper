import java.util.ArrayList;
import java.util.Random;

public class Board {
    private final int numberOfBombs;
    Square[][] squareMatrix;
    int boardSize;
    int startX;
    int startY;
    Random random = new Random();
    ArrayList<String> bombCoordinates = new ArrayList<>();
    ArrayList<String> disallowedPlacements = new ArrayList<>();

    public Board(int boardSize,int numberOfBombs, String startPosition){
        this.boardSize = boardSize;
        this.numberOfBombs = numberOfBombs;
        startX = Integer.parseInt(startPosition.split(",")[0]);
        startY = Integer.parseInt(startPosition.split(",")[1]);
        generateDisallowedPlacements();
        initializeSquareMatrix();
        generateBombCoordinates();
        assignBombs();
    }

    private void generateDisallowedPlacements(){
        for(int i = -1; i < 2;i++){
            for(int j = -1; j < 2; j++){
                disallowedPlacements.add((startX + i) + "," + (startY + j));
            }
        }
    }


    private void initializeSquareMatrix() {
        squareMatrix = new Square[boardSize][boardSize];
        for(int i = 0; i < boardSize;i++){
            for(int j = 0; j < boardSize; j++){
                squareMatrix[i][j] = new Square();
            }
        }
    }

    private void assignBombs() {
        int currentX, currentY;
        for(String bombCoord : bombCoordinates){
                currentX = Integer.parseInt(bombCoord.split(",")[0]);
                currentY = Integer.parseInt(bombCoord.split(",")[1]);
                squareMatrix[currentX][currentY].isBomb = true;
        }
    }

    private String getRandomCoordinate() {
        int currentX,currentY;
        while (true) {
            currentX = random.nextInt(boardSize);
            currentY = random.nextInt(boardSize);
            if (disallowedPlacements.contains(currentX + "," + currentY)) {
                continue;
            }
            break;
        }
        return currentX + "," + currentY;
    }

    public void generateBombCoordinates(){
        int currentNumberOfBombs = 0;
        while(currentNumberOfBombs < numberOfBombs) {
            String coordinate = getRandomCoordinate();
            if (bombCoordinates.contains(coordinate)) {
                continue;
            }
            bombCoordinates.add(coordinate);
            currentNumberOfBombs++;
        }
    }

    public void printCoordinates(){
        System.out.println(bombCoordinates);
    }


    public void printBoard(){
        for(Square[] squareRow : squareMatrix){
            for (Square square : squareRow){
                if(square.isBomb){
                    System.out.print("1-");
                }
                else{
                    System.out.print("0-");
                }
            }
            System.out.println();
        }
    }

    public Square[][] getSquareMatrix(){
        return squareMatrix;
    }
}
