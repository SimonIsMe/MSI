package generation;

import java.util.Comparator;

/**
 *
 * @author Szymon Skrzyński<skrzynski.szymon@gmail.com>
 */
class BoxHorizontalComparator implements Comparator<Box>
{

    public BoxHorizontalComparator()
    {
    }
    
    public int compare(Box boxA, Box boxB)
    {
        if (boxA.getX() > boxA.getX()) {
            return 1;
        }
        if (boxA.getX() == boxA.getX()) {
            return 0;
        }
        return -1;
    }
    
}
