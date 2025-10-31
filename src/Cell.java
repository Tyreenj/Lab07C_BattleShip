public class Cell {
    private int row, col;
    private boolean clicked;
    private boolean hit;
    private Ship ship;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.clicked = false;
        this.hit = false;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public boolean hasShip() {
        return ship != null;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void markHit() {
        hit = true;
        if (ship != null) {
            ship.registerHit(row, col);
        }
    }

    public void markMiss() {
        hit = false;
    }

    public Ship getShip() {
        return ship;
    }
}
