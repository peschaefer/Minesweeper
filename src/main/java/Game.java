import java.util.Scanner;

public class Game {

    Scanner scanner = new Scanner(System.in);

    public void play(){
        System.out.println("Enter the size of the board:");
        int boardSize = Integer.parseInt(scanner.next());
        System.out.println("Enter the number of bombs:");
        int numberOfBombs = Integer.parseInt(scanner.next());
        printFakeBoard(boardSize);
        System.out.println("Enter your starting position in the style of 0,0:");
        String position = scanner.next();
        Board board = new Board(boardSize,numberOfBombs,position);
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
                continue;
            }
            x = Integer.parseInt(position.split(",")[0]);
            y = Integer.parseInt(position.split(",")[1]);
            if(board.getSquareMatrix()[x][y].isBomb){
                System.out.println("Boom!");
                board.printCheatSheet();
                break;
            }
            board.reveal(x,y);
            board.printBoard();
        }
    }

    private void printFakeBoard(int boardSize) {
        String dashes ="-----".repeat(boardSize);
        dashes = dashes.substring(0, dashes.length()-(int)(boardSize*.8));
        System.out.println(dashes);
        for(int i = 0; i < boardSize; i++){
            System.out.print("|");
            for (int j = 0; j < boardSize; j++){
                System.out.print(" ? |");
            }
            System.out.println("\n" + dashes);
        }
    }
}
