package generation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import mis_2.Settings;

/**
 *
 * @author Szymon Skrzyński<skrzynski.szymon@gmail.com>
 */
public class Solution
{
    private LinkedList<Palette> _palettes = new LinkedList<>();
    
    
    public Solution()
    {
        this._generateRandomSolution();
    }
    
    public Solution(boolean empty)
    {
        if (empty == false)
            this._generateRandomSolution();
    }
    
    public Solution(LinkedList<Palette> palettes) 
    {
        this._palettes = palettes;
    }
    
    public void addPalette(Palette palette)
    {
        this._palettes.add(palette);
    }
    
    public boolean isContainsBox(Box boxA)
    {
        for (Box box : this.getBoxes()) 
            if (box.getId() == boxA.getId()) 
                return true;
        
        return false;
    }
    
    public LinkedList<Box> getBoxes()
    {
        LinkedList<Box> boxes = new LinkedList<>();
        
        for(Palette palette : this._palettes) 
            boxes.addAll(palette.getBoxes());
        
        return boxes;
    }

    public LinkedList<Palette> getPalettes()
    {
        return _palettes;
    }
    
    private void _generateRandomSolution()
    {
        int x;
        int y;
        Random random = new Random();
        
        for (int i = 0; i < Settings.boxes.length; i++) {
            Box box = null;
            
            if (this._canBeHorizontal(Settings.boxes[i][0], Settings.boxes[i][1]) && 
                this._canBeVertical(Settings.boxes[i][0], Settings.boxes[i][1])) {
                if (random.nextBoolean())
                    box = this._getHorizontal(Settings.boxes[i][0], Settings.boxes[i][1]);
                else
                    box = this._getVertical(Settings.boxes[i][0], Settings.boxes[i][1]);
            } else if (this._canBeHorizontal(Settings.boxes[i][0], Settings.boxes[i][1])) {
                box = this._getHorizontal(Settings.boxes[i][0], Settings.boxes[i][1]);
            } else {
                box = this._getVertical(Settings.boxes[i][0], Settings.boxes[i][1]);
            }
            
            box.setId(i);
            
            this.addBox(box);
        }
        
        this.sortPalettes();
    }
    
    private boolean _canBeHorizontal(int width, int height)
    {
        return width <= Settings.PALETTE_WIDTH && height <= Settings.PALETTE_HEIGHT;
    }
    
    private boolean _canBeVertical(int width, int height)
    {
        return height <= Settings.PALETTE_WIDTH && width <= Settings.PALETTE_HEIGHT;
    }
    
    private Box _getHorizontal(int width, int height)
    {
        int x = 0;
        int y = 0;
        Random random = new Random();
        
        if (Settings.PALETTE_WIDTH - width > 0)
            x = random.nextInt(Settings.PALETTE_WIDTH - width);

        if (Settings.PALETTE_HEIGHT - height > 0)
            y = random.nextInt(Settings.PALETTE_HEIGHT - height);

        return new Box(x, y, width, height);
    }
    
    private Box _getVertical(int width, int height)
    {
        int x = 0;
        int y = 0;
        Random random = new Random();
        
        if (Settings.PALETTE_WIDTH - height > 0)
            x = random.nextInt(Settings.PALETTE_WIDTH - height);

        if (Settings.PALETTE_HEIGHT - width > 0)
            y = random.nextInt(Settings.PALETTE_HEIGHT - width);

        return new Box(x, y, height, width);
    }
    
    public void addBoxSomewhere(Box box)
    {
        for (Palette palette : this.getPalettes())
            if (palette.addBoxSomewhere(box))
                return; 
        
        Palette newPalette = new Palette();
        newPalette.addBoxAndPush(box);
        this._palettes.add(newPalette);
    }
    
    public void addBox(Box box)
    {
        this.addBox(box, 0);
    }
    
    public void addBox(Box box, int firstPaletteId)
    {
        //  dodaję do pierwszej wolnej palety
        for (int i = firstPaletteId; i < this._palettes.size(); i++)
            if (this._palettes.get(i).addBoxAndPush(box)) 
                return;
        
        //  jeżeli nie dodano do żadnej palety, to tworzę nową
        Palette newPalette = new Palette();
        newPalette.addBoxAndPush(box);
        this._palettes.add(newPalette);
    }

    public void makeMutation()
    {
        Random random = new Random();
        int x;
        int y;
        
        for (int i = 0; i < Settings.BOXES_FOR_MUTATION; i++) {
            Palette palette = this.getPalettes().get(random.nextInt(this.getPalettes().size()));
            LinkedList<Box> boxes = palette.getBoxes();
            if (boxes.isEmpty()) {
                return;
            }
            Box box = boxes.removeFirst();
            
            if (random.nextBoolean() && box.canRotate())
                box.rotate();
            
            x = 0;
            y = 0;
            
            if (Settings.PALETTE_WIDTH - box.getWidth() > 0)
                x = random.nextInt(Settings.PALETTE_WIDTH - box.getWidth());
            
            if (Settings.PALETTE_HEIGHT - box.getHeight() > 0)
                y = random.nextInt(Settings.PALETTE_HEIGHT - box.getHeight());
            
            box.setX(x);
            box.setY(y);
            
            palette.pushBoxesToTop();
            palette.pushBoxesToLeft();
            
            if (palette.countBoxes() == 0) {
                this._palettes.remove(palette);
            }
            
            this.addBox(box);
        }
    }
    
    public Solution[] crossSolutions(Solution solution)
    {
        SolutionCrosser solutionCrosser = new SolutionCrosser(this, solution);
        return solutionCrosser.cross();
    }
    
    public void sortPalettes()
    {
        Collections.sort(this._palettes, new PaletteComparator());
    }
}
