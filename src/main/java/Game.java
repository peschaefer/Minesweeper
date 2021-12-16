import java.util.Scanner;

public class Game {

    Scanner scanner = new Scanner(System.in);
    Board board;
    int numberOfBombs;

    public void play(){
        System.out.println("Enter the size of the board:");
        int boardSize = Integer.parseInt(scanner.next());
        System.out.println("Enter the number of bombs:");
        numberOfBombs = Integer.parseInt(scanner.next());
        printFakeBoard(boardSize);
        System.out.println("Enter your starting position in the style of 0,0:");
        String position = scanner.next();
        board = new Board(boardSize,numberOfBombs,position);
        int x = Integer.parseInt(position.split(",")[0]);
        int y = Integer.parseInt(position.split(",")[1]);
        board.reveal(x,y);
        //board.printCheatSheet();
        board.printBoard();
        while(true){
            position = scanner.next();
            if(position.contains("!")){
                position = position.substring(1);
                x = Integer.parseInt(position.split(",")[0]);
                y = Integer.parseInt(position.split(",")[1]);
                board.getSquareMatrix()[x][y].flag();
                board.printBoard();
                checkForComplete();
                continue;
            }
            x = Integer.parseInt(position.split(",")[0]);
            y = Integer.parseInt(position.split(",")[1]);
            if(board.getSquareMatrix()[x][y].isBomb){
                System.err.println("Boom!");
                board.printCheatSheet();
                break;
            }
            board.reveal(x,y);
            board.printBoard();
            checkForComplete();
        }
    }

    private void checkForComplete() {
        Square square = new Square();
        if(square.getSpacesFlagged() == numberOfBombs && square.isCorrect() && board.allClear()){
            System.out.println("You cleared all mines!");
            System.exit(0);
        }
    }

    private void printFakeBoard(int boardSize) {
        printColumnNumbers(boardSize);
        String dashes ="  " + "+---".repeat(boardSize) + "+";
        System.out.println(dashes);
        for(int i = 0; i < boardSize; i++){
            System.out.print(i + " |");
            for (int j = 0; j < boardSize; j++){
                System.out.print(" ? |");
            }
            System.out.println("\n" + dashes);
        }
    }

    public void printColumnNumbers(int boardSize){
        System.out.print("    ");
        for(int i = 0; i < boardSize; i++){
            if(String.valueOf(i+1).length() == 1) {
                System.out.print(i + "   ");
            } else{
                System.out.print(i + "  ");
            }
        }
        System.out.println();
    }
}
