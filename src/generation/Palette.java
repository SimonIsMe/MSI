package generation;

import java.util.Collections;
import java.util.LinkedList;
import mis_2.Settings;

/**
 *
 * @author Szymon Skrzyński<skrzynski.szymon@gmail.com>
 */
public class Palette
{
    private LinkedList<Box> _boxes = new LinkedList<>();
    
    public Palette()
    {
    }
    
    public Palette(LinkedList<Box> boxes) 
    {
        this._boxes = boxes;
    }
    
    public LinkedList<Box> getBoxes()
    {
        return _boxes;
    }
    
    public float getPercentOfOccupiedPalette()
    {
        int space = 0;
        for (Box box : this._boxes)
            space += box.getWidth() * box.getHeight();
        
        return space / (Settings.PALETTE_WIDTH * Settings.PALETTE_HEIGHT);
    }
    
    public void setBoxes(LinkedList<Box> _boxes)
    {
        this._boxes = _boxes;
    }
    
    public int countBoxes()
    {
        return this._boxes.size();
    }
    
    public boolean addBoxSomewhere(Box newBox) 
    {
        newBox.setX(0);
        newBox.setY(0);
        
        for (int w = 0; w <= Settings.PALETTE_HEIGHT - newBox.getHeight(); w++) 
            for (int k = 0; k <= Settings.PALETTE_WIDTH - newBox.getWidth(); k++) {
                newBox.setX(k);
                newBox.setY(w);
                if (this.canBeHere(newBox)) {
                    this.addBox(newBox);
                    return true;
                }
                if (newBox.canRotate()) {
                    newBox.rotate();
                    if (this.canBeHere(newBox)) {
                        this.addBox(newBox);
                        return true;
                    }
                    newBox.rotate();
                }
            }
        
        return false;
    }
    
    public boolean addBox(Box newBox)
    {
        if (this.canBeHere(newBox) == false)
            return false;
        
        this._boxes.add(newBox);
        return true;
    }
    
    public boolean addBoxAndPush(Box newBox)
    {
        if (this.addBox(newBox) == false)
            return false;
        
        this._pushBoxToTop(newBox);
        this._pushBoxToLeft(newBox);
        
        return true;
    }
    
    public void pushBoxesToTop()
    {
        this.sortVerticalBoxes();
        
        for (Box box : this.getBoxes())
            _pushBoxToTop(box);
    }
    
    public void pushBoxesToLeft()
    {
        this.sortHorizontalBoxes();
        
        for (Box box : this.getBoxes())
            _pushBoxToLeft(box);
    }
    
    public void sortHorizontalBoxes()
    {
        Collections.sort(this._boxes, new BoxHorizontalComparator());
    }
    
    public void sortVerticalBoxes()
    {
        Collections.sort(this._boxes, new BoxVerticalComparator());
    }

    private void _pushBoxToTop(Box newBox)
    {
        int prevY = newBox.getY();
        
        for (int y = newBox.getY(); y >= 0; y--) {
            
            newBox.setY(y);
            if (this.canBeHere(newBox) == false) {
                newBox.setY(prevY);
                break;
            }
            prevY = y;
        }
        
        newBox.setY(prevY);
    }
    
    private void _pushBoxToLeft(Box newBox)
    {
        int prevX = newBox.getX();
        
        for (int x = newBox.getX(); x >= 0; x--) {
            
            newBox.setX(x);
            if (this.canBeHere(newBox) == false) {
                newBox.setX(prevX);
                break;
            }
            prevX = x;
        }
        
        newBox.setX(prevX);
    }
    
    public boolean canBeHere(Box newBox)
    {
        for (Box box : this.getBoxes()) {
            if (box == newBox) 
                continue;
            if (box.isCrossing(newBox)) 
                return false;
        }
        return true;
    }
}
