package generation;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Szymon Skrzyński<skrzynski.szymon@gmail.com>
 */
public class PaletteTest
{
    @Test
    public void test() {
    }
    
    @Test
    public void testAddBoxAndPush_1()
    {
        Box box = new Box(10, 10, 10, 10);
        
        Palette palette = new Palette();
        palette.addBoxAndPush(box);
        
        assertEquals(0, box.getX());
        assertEquals(0, box.getY());
    }
    
    @Test
    public void testAddBoxAndPush_2()
    {
        Palette palette = new Palette();
        palette.addBoxAndPush(new Box(10, 10, 15, 10));
        
        Box box = new Box(10, 15, 10, 10);
        palette.addBoxAndPush(box);
        
        assertEquals(0, box.getX());
        assertEquals(10, box.getY());
    }
}
