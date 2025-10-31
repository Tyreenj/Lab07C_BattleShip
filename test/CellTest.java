import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * JUnit 4 tests for the Cell class
 */
public class CellTest {
    private Cell cell;

    @Before
    public void setUp() {
        cell = new Cell(5, 7);
    }

    @Test
    public void testCellInitialization() {
        assertFalse("New cell should not be clicked", cell.isClicked());
        assertFalse("New cell should not have a ship", cell.hasShip());
    }

    @Test
    public void testSetAndHasShip() {
        Ship ship = new Ship(3, true);
        cell.setShip(ship);
        assertTrue("Cell should have a ship after setShip", cell.hasShip());
        assertEquals("getShip should return the set ship", ship, cell.getShip());
    }

    @Test
    public void testClickedState() {
        assertFalse("Cell should start unclicked", cell.isClicked());
        cell.setClicked(true);
        assertTrue("Cell should be clicked after setClicked(true)", cell.isClicked());
    }

    @Test
    public void testMarkHit() {
        Ship ship = new Ship(2, false);
        cell.setShip(ship);
        cell.markHit();
        assertNotNull("Cell should still have ship after hit", cell.getShip());
    }

    @Test
    public void testMarkMiss() {
        cell.markMiss();
        assertFalse("Cell should not have ship after miss", cell.hasShip());
    }
}
