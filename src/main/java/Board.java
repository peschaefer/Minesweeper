import java.util.ArrayList;
import java.util.Random;

public class Board {
    private final int numberOfBombs;
    Square[][] squareMatrix;
    int boardSize;
    Random random = new Random();
    ArrayList<String> bombCoordinates = new ArrayList<>();

    public Board(int boardSize,int numberOfBombs){
        this.boardSize = boardSize;
        this.numberOfBombs = numberOfBombs;
        initializeSquareMatrix();
        generateBombCoordinates();
        assignBombs();
    }

    private void assignBombs() {
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                squareMatrix[i][j].setBomb(bombCoordinates.contains(i + "," + j));
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

    public void printBoard(){
        for(Square[] squareRow : squareMatrix){
            for (Square square : squareRow){
                if(square.isBomb){
                    System.out.print(1);
                }
                else{
                    System.out.print(0);
                }
            }
            System.out.println();
        }
    }

    public void generateBombCoordinates(){
        int currentNumberOfBombs = 0;
        while(currentNumberOfBombs < numberOfBombs) {
            int currentX = random.nextInt(boardSize);
            int currentY = random.nextInt(boardSize);
            if (bombCoordinates.contains(currentX + "," + currentY)) {
                continue;
            }
            bombCoordinates.add(currentX + "," + currentY);
            currentNumberOfBombs++;
        }
    }

    public void printCoordinates(){
        System.out.println(bombCoordinates);
    }
}
