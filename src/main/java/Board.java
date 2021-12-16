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
        assignBombsAdjacent();
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

    private void assignBombsAdjacent() {
        int currentX,currentY;
        for(String coordinate : bombCoordinates){
            currentX = Integer.parseInt(coordinate.split(",")[0]);
            currentY = Integer.parseInt(coordinate.split(",")[1]);
            for(int i = -1; i < 2;i++){
                for(int j = -1; j < 2; j++){
                    try {
                        if (squareMatrix[currentX + i][currentY + j].isBomb) {
                            continue;
                        }
                        squareMatrix[currentX + i][currentY + j].incrementBombsAdjacent();
                    } catch(Exception ignored){}
                }
            }
        }
    }

//    public void printCoordinates(){
//        System.out.println(bombCoordinates);
//    }

    public void printBoard(){
        String dashes ="-----".repeat(boardSize);
        dashes = dashes.substring(0, dashes.length()-(int)(boardSize*.8));
        System.out.println("\n" + dashes);
        for(Square[] squareRow : squareMatrix){
            System.out.print("|");
            for (Square square : squareRow){
                System.out.print(" " + square.symbol + " |");
            }
            System.out.println("\n" + dashes);
        }
    }

    public Square[][] getSquareMatrix(){
        return squareMatrix;
    }

    public void reveal(int x, int y){
        if(squareMatrix[x][y].showing){
            return;
        }
        if(squareMatrix[x][y].bombsAdjacent != 0){
            squareMatrix[x][y].show();
            return;
        }
        if (squareMatrix[x][y].bombsAdjacent == 0){
            for(int i = -1; i < 2; i++){
                for (int j = -1; j < 2; j++){
                    try {
                        squareMatrix[x][y].show();
                        reveal(x + i, y + j);
                    } catch(Exception ignored){}
                }
            }
        }
    }

    public void printCheatSheet() {
        boolean showing;
        String dashes ="-----".repeat(boardSize);
        dashes = dashes.substring(0, dashes.length()-(int)(boardSize*.8));
        System.out.println("\n" + dashes);
        for(Square[] squareRow : squareMatrix){
            System.out.print("|");
            for (Square square : squareRow){
                showing = square.showing;
                square.show();
                if(square.isBomb){
                    System.out.print("\u001B[31m X "+ "\u001B[0m|");
                }else {
                    System.out.print(" " + square.symbol + " |");
                }
                if(!showing) {
                    square.hide();
                }
            }
            System.out.println("\n" + dashes);
        }
    }
}