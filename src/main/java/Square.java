public class Square {

    int bombsAdjacent;
    boolean isBomb;

    public Square(){}

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public void setBombsAdjacent(int bombsAdjacent){
        this.bombsAdjacent = bombsAdjacent;
    }

    public int getBombsAdjacent() {
        return bombsAdjacent;
    }


    @Override
    public String toString(){
        return String.valueOf(bombsAdjacent);
    }
}
