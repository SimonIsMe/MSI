package generation;

import java.util.LinkedList;
import java.util.Random;
import mis_2.Settings;

/**
 *
 * @author Szymon Skrzyński<skrzynski.szymon@gmail.com>
 */
public class SolutionCrosser
{
    private final Solution _solutionA;
    private final Solution _solutionB;
    
    public SolutionCrosser(Solution solutionA, Solution solutionB)
    {
        this._solutionA = solutionA;
        this._solutionB = solutionB;
    }
    
    public Solution[] cross()
    {
        Solution[] crossedColutions = new Solution[2];
        
        Solution[] splitSolutionsA = this._splitSolution(this._solutionA);
        Solution[] splitSolutionsB = this._splitSolution(this._solutionB);
        
        crossedColutions[0] = this._joinSolutions(splitSolutionsA[0], splitSolutionsB[1]);
        crossedColutions[1] = this._joinSolutions(splitSolutionsB[0], splitSolutionsA[1]);
        
        return crossedColutions;
    }
    
    private Solution[] _splitSolution(Solution solution)
    {
        Solution[] toReturn = new Solution[2];
        toReturn[0] = new Solution(true);
        toReturn[1] = new Solution(true);
        
        int half = (int) solution.getPalettes().size() / 2;
        for (int i = 0; i < half; i++)
            toReturn[0].addPalette(solution.getPalettes().get(i));
        
        for (int i = half; i < solution.getPalettes().size(); i++)
            toReturn[1].addPalette(solution.getPalettes().get(i));
        
        return toReturn;
    }
    
    private Solution _joinSolutions(Solution splitedSolutionA, Solution splitedSolutionB)
    {
        int countPalettesInSolutionA = splitedSolutionA.getPalettes().size();
        
        Solution solution = splitedSolutionA;
        for (Palette palette : splitedSolutionB.getPalettes()) {
            for (Box box : palette.getBoxes())
                if (solution.isContainsBox(box) == false)
                    solution.addBoxSomewhere(box);
        }
        
        LinkedList<Box> unusedBoxes = new LinkedList<>();
        for (Box box : this._solutionA.getBoxes())
            if (solution.isContainsBox(box) == false)
                unusedBoxes.add(box);
        
        this._putBoxesInRandomPlaces(solution, unusedBoxes, countPalettesInSolutionA);
        
        return solution;
    }

    private void _putBoxesInRandomPlaces(Solution solution, LinkedList<Box> unusedBoxes, int countPalettesInSolutionA)
    {
        int x;
        int y;
        Random random = new Random();
        
        for (Box box : unusedBoxes) {
            Box newBox = new Box(0,0, box.getWidth(), box.getHeight());
            newBox.setId(box.getId());
            
            if (random.nextBoolean() && box.canRotate())
                newBox.rotate();
            
//            solution.addBox(newBox, countPalettesInSolutionA);
            solution.addBoxSomewhere(newBox);
        }
        
        for (Palette palette : solution.getPalettes()) {
            palette.pushBoxesToTop();
            palette.pushBoxesToLeft();
        }
    }
}
