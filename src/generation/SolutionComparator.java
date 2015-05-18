package generation;

import java.util.Comparator;

/**
 *
 * @author Szymon Skrzyński<skrzynski.szymon@gmail.com>
 */
class SolutionComparator implements Comparator<Solution>
{

    public SolutionComparator()
    {
    }
    
    public int compare(Solution solutionA, Solution solutionB)
    {
        if (solutionA.getPalettes().size() > solutionB.getPalettes().size()) {
            return 1;
        }
        if (solutionA.getPalettes().size() == solutionB.getPalettes().size()) {
            return 0;
        }
        return -1;
    }
    
}
