package generation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import mis_2.Settings;

/**
 *
 * @author Szymon Skrzyński<skrzynski.szymon@gmail.com>
 */
public class Generation
{
    private LinkedList<Solution> _solutions = new LinkedList<>();
    private int _id;
    
    public Generation()
    {
        this._id = 0;
        this._generateRandomSolutions();
        this.sortSolutions();
    }
    
    public Generation(Generation prevGeneration) 
    {
        this._id = prevGeneration.getId() + 1;
        this._solutions = prevGeneration.getSolutions();
        this._crossSolutions();
        this._makeMutation();
        this.sortSolutions();
    }

    private void _generateRandomSolutions()
    {
        for (int  i = 0; i < Settings.SOLUTIONS_PER_GENERATION; i++) {
            this._solutions.add(new Solution());
        }
    }
    
    public int getId()
    {
        return this._id;
    }

    public LinkedList<Solution> getSolutions()
    {
        return _solutions;
    }
    
    public void sortSolutions()
    {
        Collections.sort(this._solutions, new SolutionComparator());
    }

    private void _makeMutation()
    {
        int[] solutionIds = _randomSolutionsIdsForMutation();
        
        for (int i = 0; i < solutionIds.length; i++) {
            this.getSolutions().get(solutionIds[i]).makeMutation();
            this.getSolutions().get(solutionIds[i]).sortPalettes();
        }
    }
    
    private int[] _randomSolutionsIdsForMutation()
    {
        int[] solutionIds = new int[Settings.SOLUTIONS_FOR_MUTATION];
        int i = 0;
        int id;
        Random random = new Random();
        while (i < solutionIds.length) {
            id = random.nextInt(Settings.SOLUTIONS_PER_GENERATION);
            for (int k = 0; k < i; k++) {
                if (solutionIds[k] == id) {
                    id = -1;
                    break;
                }
            }
            
            if (id == -1) 
                continue;
            
            solutionIds[i++] = id;
        }
        
        return solutionIds;
    }
 
    private void _crossSolutions()
    {
        for (int i = 0; i < Settings.PAIR_OF_SOLUTIONS_FOR_CROSSING * 2; i += 2) {
            Solution[] crossedSolutions = this._solutions.get(i).crossSolutions(this._solutions.get(i + 1));
            this._solutions.set(i, crossedSolutions[0]);
            this._solutions.set(i + 1, crossedSolutions[1]);
        }
    }
    
}
