package generation;

import java.util.Comparator;

/**
 *
 * @author Szymon Skrzyński<skrzynski.szymon@gmail.com>
 */
class BoxVerticalComparator implements Comparator<Box>
{

    public BoxVerticalComparator()
    {
    }
    
    public int compare(Box boxA, Box boxB)
    {
        if (boxA.getY() > boxA.getY()) {
            return 1;
        }
        if (boxA.getY() == boxA.getY()) {
            return 0;
        }
        return -1;
    }
    
}
