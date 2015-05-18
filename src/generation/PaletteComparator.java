package generation;

import java.util.Comparator;

/**
 *
 * @author Szymon Skrzyński<skrzynski.szymon@gmail.com>
 */
class PaletteComparator implements Comparator<Palette>
{

    public PaletteComparator()
    {
    }
    
    public int compare(Palette paletteA, Palette paletteB)
    {
        if (paletteA.getPercentOfOccupiedPalette() > paletteB.getPercentOfOccupiedPalette()) {
            return 1;
        }
        if (paletteA.getPercentOfOccupiedPalette() == paletteB.getPercentOfOccupiedPalette()) {
            return 0;
        }
        return -1;
    }
    
}
