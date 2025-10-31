import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
/**
 * JUnit 4 tests for the BattleshipGame class
 */
public class BattleshipGameTest {

    @Test
    public void testConstants() {
        assertEquals("Max misses should be 5", 5, BattleshipGame.MAX_MISSES);
        assertEquals("Max strikes should be 3", 3, BattleshipGame.MAX_STRIKES);
        assertEquals("Total ship cells should be 17", 17, BattleshipGame.TOTAL_SHIP_CELLS);
    }
}
