public class Square {

    int bombsAdjacent;
    boolean isBomb;
    boolean showing = false;
    String symbol = "?";

    public Square(){
        isBomb = false;
        bombsAdjacent = 0;
    }

    public void incrementBombsAdjacent(){
        bombsAdjacent++;
    }

    public void show(){
        showing = true;
        symbol = String.valueOf(bombsAdjacent);
    }

    @Override
    public String toString(){
        return String.valueOf(bombsAdjacent);
    }
}
