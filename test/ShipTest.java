import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
/**
 * JUnit 4 tests for the Ship class
 */
public class ShipTest {
    private Ship ship;

    @Before
    public void setUp() {
        ship = new Ship(3, true);
    }

    @Test
    public void testShipInitialization() {
        assertFalse("New ship should not be sunk", ship.isSunk());
        assertNotNull("Coordinates list should not be null", ship.getCoordinates());
    }

    @Test
    public void testRegisterHit() {
        ship.registerHit(2, 3);
        assertFalse("Ship with 1 hit out of 3 should not be sunk", ship.isSunk());
    }

    @Test
    public void testShipSinking() {
        ship.registerHit(0, 0);
        assertFalse("Ship should not be sunk with 1/3 hits", ship.isSunk());

        ship.registerHit(0, 1);
        assertFalse("Ship should not be sunk with 2/3 hits", ship.isSunk());

        ship.registerHit(0, 2);
        assertTrue("Ship should be sunk with 3/3 hits", ship.isSunk());
    }

    @Test
    public void testDuplicateHits() {
        ship.registerHit(1, 1);
        ship.registerHit(1, 1); // Same location
        ship.registerHit(1, 2);
        ship.registerHit(1, 3);

        assertTrue("Ship should be sunk even with duplicate hits", ship.isSunk());
    }

    @Test
    public void testSetCoordinates() {
        java.util.List<int[]> coords = new java.util.ArrayList<>();
        coords.add(new int[]{0, 0});
        coords.add(new int[]{0, 1});
        coords.add(new int[]{0, 2});

        ship.setCoordinates(coords);
        assertEquals("Ship should have 3 coordinates", 3, ship.getCoordinates().size());
    }
}