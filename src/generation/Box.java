package generation;

import mis_2.Settings;

/**
 *
 * @author Szymon Skrzyński<skrzynski.szymon@gmail.com>
 */
public class Box
{
    private int _id;
    private int _x;
    private int _y;
    private int _width;
    private int _height;

    public Box(int x, int y, int width, int height)
    {
        this._x = x;
        this._y = y;
        this._width = width;
        this._height = height;
    }

    public int getId()
    {
        return _id;
    }

    public void setId(int _id)
    {
        this._id = _id;
    }
    
    public int getX()
    {
        return _x;
    }

    public void setX(int _x)
    {
        this._x = _x;
    }

    public int getY()
    {
        return _y;
    }

    public void setY(int _y)
    {
        this._y = _y;
    }

    public int getWidth()
    {
        return _width;
    }

    public void setWidth(int _width)
    {
        this._width = _width;
    }

    public int getHeight()
    {
        return _height;
    }

    public void setHeight(int _height)
    {
        this._height = _height;
    }
    
    public void rotate()
    {
        int buffor = this._width;
        this._width = this._height;
        this._height = buffor;
    }
    
    public boolean canRotate()
    {
        return  Math.max(this._width, this._height) < Math.min(Settings.PALETTE_WIDTH, Settings.PALETTE_HEIGHT);
    }
    
    public boolean isCrossing(Box box)
    {
        boolean[][] tab = new boolean[Settings.PALETTE_WIDTH][Settings.PALETTE_WIDTH];
        
        for (int k = 0; k < Settings.PALETTE_WIDTH; k++) 
            for (int w = 0; w < Settings.PALETTE_WIDTH; w++) 
                tab[k][w] = false;
        
        for (int k = this.getX(); k < this.getX() + this.getWidth(); k++) 
            for (int w = this.getY(); w < this.getY() + this.getHeight(); w++) 
                tab[k][w] = true;
        
        for (int k = box.getX(); k < box.getX() + box.getWidth(); k++)
            for (int w = box.getY(); w < box.getY() + box.getHeight(); w++)
                if (tab[k][w]) 
                    return true;
        
        return false;
    }
}
