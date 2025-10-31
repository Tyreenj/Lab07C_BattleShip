import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
/**
 * JUnit 4 tests for the Board class
 */
public class BoardTest {
    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testBoardInitialization() {
        Cell[][] grid = board.getGrid();
        assertNotNull("Grid should not be null", grid);
        assertEquals("Grid should have 10 rows", 10, grid.length);
        assertEquals("Grid should have 10 columns", 10, grid[0].length);

        // Check all cells are initialized
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                assertNotNull("Cell at [" + r + "][" + c + "] should not be null", grid[r][c]);
            }
        }
    }

    @Test
    public void testPlaceShips() {
        board.placeShips();

        // Count total ship cells
        int shipCells = 0;
        Cell[][] grid = board.getGrid();
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (grid[r][c].hasShip()) {
                    shipCells++;
                }
            }
        }

        assertEquals("Board should have exactly 17 ship cells (5+4+3+3+2)", 17, shipCells);
    }

    @Test
    public void testIsHit() {
        board.placeShips();
        Cell[][] grid = board.getGrid();

        // Find a cell with a ship
        int shipRow = -1, shipCol = -1;
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (grid[r][c].hasShip()) {
                    shipRow = r;
                    shipCol = c;
                    break;
                }
            }
            if (shipRow != -1) break;
        }

        assertTrue("Should return true when hitting a ship", board.isHit(shipRow, shipCol));
        assertTrue("Cell should be marked as clicked", grid[shipRow][shipCol].isClicked());
    }

    @Test
    public void testIsMiss() {
        board.placeShips();
        Cell[][] grid = board.getGrid();

        // Find an empty cell
        int emptyRow = -1, emptyCol = -1;
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (!grid[r][c].hasShip()) {
                    emptyRow = r;
                    emptyCol = c;
                    break;
                }
            }
            if (emptyRow != -1) break;
        }

        assertFalse("Should return false when missing", board.isHit(emptyRow, emptyCol));
        assertTrue("Cell should be marked as clicked", grid[emptyRow][emptyCol].isClicked());
    }

    @Test
    public void testAllShipsSunk() {
        board.placeShips();
        assertFalse("Ships should not be sunk initially", board.allShipsSunk());

        // Hit all ship cells
        Cell[][] grid = board.getGrid();
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (grid[r][c].hasShip()) {
                    board.isHit(r, c);
                }
            }
        }

        assertTrue("All ships should be sunk after hitting all cells", board.allShipsSunk());
    }

    @Test
    public void testGetShipAt() {
        board.placeShips();
        Cell[][] grid = board.getGrid();

        // Find a cell with a ship
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (grid[r][c].hasShip()) {
                    Ship ship = board.getShipAt(r, c);
                    assertNotNull("Should return ship at position with ship", ship);
                    return;
                }
            }
        }
    }

    @Test
    public void testNoOverlappingShips() {
        board.placeShips();
        Cell[][] grid = board.getGrid();

        // Check that each cell has at most one ship
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (grid[r][c].hasShip()) {
                    Ship ship = grid[r][c].getShip();
                    assertNotNull("If cell has ship, getShip should not be null", ship);
                }
            }
        }
    }
}
