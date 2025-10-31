import java.util.*;

public class Board {
    private final int SIZE = 10;
    private Cell[][] grid;
    private List<Ship> ships;
    private Random random = new Random();

    public Board() {
        grid = new Cell[SIZE][SIZE];
        ships = new ArrayList<>();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                grid[r][c] = new Cell(r, c);
            }
        }
    }

    public void placeShips() {
        int[] shipSizes = {5, 4, 3, 3, 2};

        for (int size : shipSizes) {
            boolean placed = false;
            while (!placed) {
                boolean horizontal = random.nextBoolean();
                int row = random.nextInt(SIZE);
                int col = random.nextInt(SIZE);

                if (canPlaceShip(row, col, size, horizontal)) {
                    Ship ship = new Ship(size, horizontal);
                    List<int[]> coords = new ArrayList<>();

                    for (int i = 0; i < size; i++) {
                        int r = horizontal ? row : row + i;
                        int c = horizontal ? col + i : col;
                        grid[r][c].setShip(ship);
                        coords.add(new int[]{r, c});
                    }

                    ship.setCoordinates(coords);
                    ships.add(ship);
                    placed = true;
                }
            }
        }
    }

    private boolean canPlaceShip(int row, int col, int size, boolean horizontal) {
        if (horizontal) {
            if (col + size > SIZE) return false;
            for (int i = 0; i < size; i++) {
                if (grid[row][col + i].hasShip()) return false;
            }
        } else {
            if (row + size > SIZE) return false;
            for (int i = 0; i < size; i++) {
                if (grid[row + i][col].hasShip()) return false;
            }
        }
        return true;
    }

    public boolean isHit(int row, int col) {
        Cell cell = grid[row][col];
        cell.setClicked(true);
        if (cell.hasShip()) {
            cell.markHit();
            return true;
        } else {
            cell.markMiss();
            return false;
        }
    }

    public boolean allShipsSunk() {
        for (Ship s : ships) {
            if (!s.isSunk()) return false;
        }
        return true;
    }

    public Ship getShipAt(int row, int col) {
        return grid[row][col].getShip();
    }

    public Cell[][] getGrid() {
        return grid;
    }
}
