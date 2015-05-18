package generation;

import mis_2.Settings;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Szymon Skrzyński<skrzynski.szymon@gmail.com>
 */
public class BoxTest
{

    @BeforeClass
    public static void setUpClass()
    {
        Settings.PALETTE_WIDTH = 1000;
        Settings.PALETTE_HEIGHT = 1000;
    }
    
    @Test
    public void testIsCrossing_1()
    {
        Box boxA = new Box(10, 10, 100, 50);
        Box boxB = new Box(10, 10, 100, 50);
        assertTrue(boxA.isCrossing(boxB));
    }
    
    @Test
    public void testIsCrossing_2()
    {
        Box boxA = new Box(10, 10, 100, 50);
        Box boxB = new Box(110, 10, 100, 50);
        assertFalse(boxA.isCrossing(boxB));
    }
    
    @Test
    public void testIsCrossing_3()
    {
        Box boxA = new Box(10, 10, 100, 50);
        Box boxB = new Box(10, 60, 100, 50);
        assertFalse(boxA.isCrossing(boxB));
    }
    
    @Test
    public void testIsCrossing_4()
    {   
        Settings.PALETTE_WIDTH = 12000;
        Settings.PALETTE_HEIGHT = 9000;
        
        Box boxA = new Box(10, 10, 100, 50);
        Box boxB = new Box(110, 60, 100, 50);
        assertFalse(boxA.isCrossing(boxB));
    }
    
    @Test
    public void testIsCrossing_5()
    {
        Box boxA = new Box(10, 10, 100, 50);
        Box boxB = new Box(109, 59, 100, 50);
        assertTrue(boxA.isCrossing(boxB));
    }
    
    @Test
    public void testIsCrossing_6()
    {
        Box boxA = new Box(0, 10, 100, 50);
        Box boxB = new Box(10, 0, 50, 100);
        assertTrue(boxA.isCrossing(boxB));
    }
    
    @Test
    public void testIsCrossing_7()
    {
        Settings.PALETTE_WIDTH = 120;
        Settings.PALETTE_HEIGHT = 90;
        
        Box boxA = new Box(0, 0, 90, 20);
        Box boxB = new Box(0, 0, 20, 90);
        assertTrue(boxA.isCrossing(boxB));
    }
    
}
